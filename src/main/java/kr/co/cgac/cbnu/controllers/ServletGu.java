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

@WebServlet(name = "ServletGu", urlPatterns = "/gu")
public class ServletGu extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("application/x-www-form-urlencoded; charset=UTF-8 application/json");
        response.setCharacterEncoding("UTF-8");
        /* Request with one parameters cityId */
        long cityId = Long.parseLong(request.getParameter("cityId"));
            List<Apartment> guList = new ApartmentDAO().getGu(cityId);
            Map<String, Object> map = new HashMap<String, Object>();
            if (guList != null) {
                map.put("DATA", guList);
                map.put("STATUS", StatusCode.SUCCESS);
                map.put("PAGINATION", null);
            } else {
                map.put("STATUS", StatusCode.NOT_SUCCESS);
            }
            response.getWriter().write(new Gson().toJson(map));
    }
}
