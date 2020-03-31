package ru.tusur.udo.ejbs.controllers;

import ru.tusur.udo.ejbs.servises.SensorsMonitoring;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;

public class ApiController extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Inject
    SensorsMonitoring sensorsMonitoring;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        sensorsMonitoring.fireSensors(parseRequest(request));
        response.setStatus(HttpServletResponse.SC_OK);

    }

    private String parseRequest(HttpServletRequest request) throws IOException {
        Scanner s = new Scanner(request.getInputStream(), "UTF-8")
                .useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("HELLO FROM SUPER SERVLET");
        response.getWriter().flush();
        response.getWriter().close();

    }

}
