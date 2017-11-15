package kr.co.cgac.cbnu.controllers;

import com.google.gson.Gson;
import kr.co.cgac.cbnu.models.Apartment;
import kr.co.cgac.cbnu.repositories.ApartmentDAO;
import kr.co.cgac.cbnu.utilities.StatusCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "ServletDong" , urlPatterns = {"/dong"})
public class ServletDong extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/x-www-form-urlencoded; charset=UTF-8 application/json");
        response.setCharacterEncoding("UTF-8");
        /* Request with two parameters guId and cityId */
        long guId = Long.parseLong(request.getParameter("guId"));
        long cityId = Long.parseLong(request.getParameter("cityId"));
            List<Apartment> dongList = new ApartmentDAO().getDong(guId, cityId);
            Map<String, Object> map = new HashMap<String, Object>();
            if (dongList != null) {
                map.put("DATA", dongList);
            } else {
                map.put("STATUS", StatusCode.NOT_SUCCESS);
            }
            response.getWriter().write(new Gson().toJson(map));
    }
}




