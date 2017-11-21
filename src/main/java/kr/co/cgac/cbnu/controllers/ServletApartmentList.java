package kr.co.cgac.cbnu.controllers;

import com.google.gson.Gson;
import kr.co.cgac.cbnu.models.Apartment;
import kr.co.cgac.cbnu.models.MachineFail;
import kr.co.cgac.cbnu.repositories.ApartmentDAO;
import kr.co.cgac.cbnu.repositories.MachineFailDAO;
import kr.co.cgac.cbnu.utilities.Pagination;
import kr.co.cgac.cbnu.utilities.StatusCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "ServletApartmentList" , urlPatterns = {"/all-apartments", "/apartmentId"})
public class ServletApartmentList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/x-www-form-urlencoded; charset=UTF-8 application/json");
        response.setCharacterEncoding("UTF-8");
        String servletPath = request.getServletPath();
        if (servletPath.equals("/all-apartments")) {
            Pagination pagination = new Pagination();
            pagination.setLimit(Integer.parseInt(request.getParameter("limit")));
            pagination.setPage(Integer.parseInt(request.getParameter("page")));
            String name = request.getParameter("name");
            List<Apartment> apartmentList = new ApartmentDAO().getAllApartment(pagination, name);
            Map<String, Object> map = new HashMap<String, Object>();
            if (apartmentList != null) {
                map.put("DATA", apartmentList);
                map.put("STATUS", StatusCode.SUCCESS);
                map.put("PAGINATION", pagination);
            } else {
                map.put("STATUS", StatusCode.NOT_SUCCESS);
            }
            response.getWriter().write(new Gson().toJson(map));
        }
        else if (servletPath.equals("/apartmentId")) {
            long dongId = Long.parseLong(request.getParameter("dongId"));
            long guId = Long.parseLong(request.getParameter("guId"));
            long cityId = Long.parseLong(request.getParameter("cityId"));
            List<Apartment> apartmentListById = new ApartmentDAO().getApartmentById(dongId, guId, cityId);
            Map<String, Object> map = new HashMap<String, Object>();
            if (apartmentListById != null) {
                map.put("DATA", apartmentListById);/*
                map.put("STATUS", StatusCode.SUCCESS);
                map.put("PAGINATION", null);*/
            } else {
                map.put("STATUS", StatusCode.NOT_SUCCESS);
            }
            response.getWriter().write(new Gson().toJson(map));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/x-www-form-urlencoded; charset=UTF-8 application/json");
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        Gson gson = new Gson();
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1 = (Map<String,Object>) gson.fromJson(json, map1.getClass());
        Apartment apartment = new Apartment(map1.get("apart_id").toString(),
                map1.get("apart_name").toString(), Long.parseLong(map1.get("dong_id").toString()),Long.parseLong(map1.get("gu_id").toString()),
                Long.parseLong(map1.get("city_id").toString()),map1.get("url_image").toString());
        boolean result = new ApartmentDAO().updateApartmentInfo(apartment);
        Map<String, Object> map = new HashMap<String, Object>();
        if (result) {
            map.put("STATUS", StatusCode.SUCCESS);
        } else {
            map.put("STATUS", StatusCode.NOT_SUCCESS);
        }
        resp.getWriter().write(new Gson().toJson(map));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/x-www-form-urlencoded; charset=UTF-8 application/json");
        boolean result = new ApartmentDAO().deleteApartmentInfo(req.getParameter("apart_id"));
        Map<String, Object> map = new HashMap<String, Object>();
        if (result) {
            map.put("STATUS", StatusCode.SUCCESS);
        } else {
            map.put("STATUS", StatusCode.NOT_SUCCESS);
        }
        resp.getWriter().write(new Gson().toJson(map));
    }
}
