package com.github.druyaned.booklib.data;

import jakarta.servlet.http.HttpServletRequest;

public class BookUtil {
    
    public static String baseURL(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        int endIndex = requestURL.indexOf("/booklib");
        return requestURL.substring(0, endIndex) + "/booklib";
    }
    
    public static String displayDate(Book book) {
        return
                book.date().getMonthValue() == 1
                && book.date().getDayOfMonth() == 1
                ? Integer.toString(book.date().getYear())
                : Integer.toString(book.date().getYear())
                + "-" + book.date().getMonthValue()
                + "-" + book.date().getDayOfMonth();
    }
    
}
