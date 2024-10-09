package com.github.druyaned.booklib.listeners;

import com.github.druyaned.booklib.data.BookRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookRepositoryListener implements ServletContextListener {
    
    public static final Logger LOGGER = Logger
            .getLogger(BookRepositoryListener.class.getName());
    
    private Connection connection;
    
    @Override public void contextInitialized(ServletContextEvent event) {
        try {
            org.h2.Driver.load();
            // creating connection
            ServletContext servletContext = event.getServletContext();
            String url = "jdbc:h2:mem:booklib"
                    + ";INIT=RUNSCRIPT FROM"
                    + " '~/java-libs/apache-tomcat-10.1.28/webapps/booklib"
                    + "/WEB-INF/classes/books/init.sql'";
            connection = DriverManager.getConnection(url, "sa", "sa");
            // creating book repository
            BookRepository bookRepository = new BookRepository(connection);
            // setting context attributes
            servletContext.setAttribute("connection", connection);
            servletContext.setAttribute("bookRepository", bookRepository);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Get the connection.
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }
    
    @Override public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // shutdown the db
        try (Statement statement = connection.createStatement()) {
            statement.execute("SHUTDOWN");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        // close the connetion
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
