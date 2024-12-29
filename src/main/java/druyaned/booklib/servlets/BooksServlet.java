package druyaned.booklib.servlets;

import druyaned.booklib.data.Book;
import druyaned.booklib.data.BookRepository;
import druyaned.booklib.data.BookUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BooksServlet extends HttpServlet {
    
    @Override protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws
            ServletException,
            IOException
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.addHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        var out = response.getWriter();
        out.print(
                """
                <html>
                <head>
                  <title>Books</title>
                  <link 
                      rel="stylesheet"
                      type="text/css"
                      href="stylesheet.css"
                      title="BooksStyle">
                </head>
                <body>
                  <h1>Книги</h1>
                  <hr>
                """);
        out.print("  <p>\n");
        out.print("    uri: " + request.getRequestURI() + "<br>\n");
        out.print("    url: " + request.getRequestURL().toString() + "\n");
        out.print("  <p>\n");
        out.print(
                """
                  <table>
                    <tr>
                      <th>Номер</th>
                      <th>Картинка</th>
                      <th>Название</th>
                      <th>Автор</th>
                      <th>Дата</th>
                    </tr>
                """
        );
        var bookRepository = (BookRepository)getServletContext()
                .getAttribute("bookRepository");
        var books = bookRepository.getAllBooks();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            out.print("    <tr>\n");
            out.print("      <td>" + (i + 1) + "</td>\n");
            out.print("      <td><img\n");
            out.print("              src=\"" + book.imageURL() + "\"\n");
            out.print("              alt=\"image" + book.id() + "\"/>\n");
            out.print("      </td>\n");
            out.print("      <td class=\"linked\">\n");
            out.print("        <a href=\"books/book/" + book.id() + "\">"
                    + book.name() + "</a>\n");
            out.print("      </td>\n");
            out.print("      <td>" + book.author() + "</td>\n");
            out.print("      <td>" + BookUtil.displayDate(book) + "</td>\n");
            out.print("    </tr>\n");
        }
        out.print(
                """
                  </table>
                </body>
                </html>
                """
        );
    }
    
}
