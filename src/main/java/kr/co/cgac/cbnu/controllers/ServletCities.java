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


@WebServlet(name = "ServletCities" , urlPatterns = {"/cities"})
public class ServletCities extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/x-www-form-urlencoded; charset=UTF-8 application/json");
        response.setCharacterEncoding("UTF-8");
            List<Apartment> cityList = new ApartmentDAO().getCities();
            Map<String, Object> map = new HashMap<String, Object>();
            if (cityList != null) {
                map.put("DATA", cityList);
            } else {
                map.put("STATUS", StatusCode.NOT_SUCCESS);
            }
            response.getWriter().write(new Gson().toJson(map));
    }
}

