package com.github.druyaned.booklib.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookRepository {
    
    public static final Logger LOGGER = Logger
            .getLogger(BookRepository.class.getName());
    
    private final Connection connection;
    private final BookBuilder builder = new BookBuilder();
    
    public BookRepository(Connection connection) {
        this.connection = connection;
    }
    
    public Book getBookById(Long id) {
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT * FROM Books WHERE id=" + id + ";"
                );
        ) {
            if (resultSet.next()) {
                setBuilder(resultSet);
                return builder.result();
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Book> getAllBooks() {
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT * FROM Books;"
                );
        ) {
            List<Book> books = new ArrayList<>(resultSet.getFetchSize());
            while (resultSet.next()) {
                setBuilder(resultSet);
                books.add(builder.result());
            }
            return books;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void setBuilder(ResultSet resultSet) throws SQLException {
        builder.reset();
        builder.setId(resultSet.getLong(1));
        builder.setName(resultSet.getString(2));
        builder.setAuthor(resultSet.getString(3));
        builder.setImageURL(resultSet.getString(4));
        builder.setDescription(resultSet.getString(5));
        builder.setDate(resultSet.getDate(6).toLocalDate());
    }
    
}
