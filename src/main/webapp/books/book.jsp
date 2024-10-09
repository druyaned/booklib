<%-- 
    Document   : book
    Author     : druyaned
--%>

<jsp:directive.page language="java" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.github.druyaned.booklib.data.Book" %>
<%@page import="com.github.druyaned.booklib.data.BookRepository" %>
<%@page import="com.github.druyaned.booklib.data.BookUtil" %>

<jsp:scriptlet>
    String[] reqUriParts = request.getRequestURI().split("/");
    Long id = Long.valueOf(reqUriParts[reqUriParts.length - 1]);
    var bookRepository = (BookRepository)getServletContext()
            .getAttribute("bookRepository");
    Book book = bookRepository.getBookById(id);
    response.addHeader("Cache-Control", "no-cache,no-store,must-revalidate");
</jsp:scriptlet>

<!DOCTYPE html>
<html>
  <head>
    <title><jsp:expression>book.name()</jsp:expression></title>
    <link
        rel="stylesheet"
        type="text/css"
        href="../../stylesheet.css"
        tytle="BookStyle">
  </head>
  <body>
    <h1>Книга #<%=id%></h1>
    <hr>
    <p>
      uri: <%=request.getRequestURI()%><br>
      url: <%=request.getRequestURL().toString()%>
    </p>
    <table>
      <tr> <td>Идентификатор</td> <td><%=id%></td> </tr>
      <tr>
        <td>Картинка</td>
        <td><img
              src="../../<%=book.imageURL()%>"
              alt="image<%=id%>"
              class="onlyone"/>
        </td>
      </tr>
      <tr> <td>Название</td> <td><%=book.name()%></td> </tr>
      <tr> <td>Автор</td> <td><%=book.author()%></td> </tr>
      <tr> <td>Дата</td> <td><%=BookUtil.displayDate(book)%></td> </tr>
      <tr> <td>Описание</td> <td><%=book.description()%></td> </tr>
    </table>
  </body>
</html>
