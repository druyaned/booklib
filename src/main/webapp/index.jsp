<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BookLib</title>
    <link
        rel="stylesheet"
        type="text/css"
        href="stylesheet.css"
        tytle="BookStyle">
  </head>
<body>
  <h1>Добро пожаловать в "BookLib"!</h1>
  <hr>
  <p>
    uri: <%=request.getRequestURI()%><br>
    url: <%=request.getRequestURL().toString()%>
  </p>
  <p>
    <a href="books">Пройти к списку книг</a>.
  </p>
</body>
</html>
