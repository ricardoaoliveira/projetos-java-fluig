package com.fluig;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DatatablesServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("application/json; charset=UTF-8");

        System.out.println("DatatablesServlet - teste");
        
        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("DatatablesServlet - teste");
    }
    
}
