package com.espark.adarsh.apiai;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "MyServletSample"
        , description = "A sample annotated servlet"
        , urlPatterns = {"/welcome","/wel"}
)
public class MyServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProocess(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProocess(request, response);
    }

    public void doProocess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write("welcome to the espark ");
    }
}
