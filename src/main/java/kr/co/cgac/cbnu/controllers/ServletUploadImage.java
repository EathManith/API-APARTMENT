package kr.co.cgac.cbnu.controllers;

import kr.co.cgac.cbnu.models.Apartment;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "ServletUploadImage" , urlPatterns = {"/upload", "/up"})
public class ServletUploadImage extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    public  void  doProcess (HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{

            Apartment image = new Apartment();

            String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/file/");

            System.out.println("Path: " + savePath);



        }catch(Exception e){e.printStackTrace();}
        response.getWriter().write("DateDuplicated");
    }

}
