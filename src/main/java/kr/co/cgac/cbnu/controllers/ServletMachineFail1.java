//package kr.co.cgac.cbnu.controllers;
//
//import com.google.gson.Gson;
//import kr.co.cgac.cbnu.models.Apartment;
//import kr.co.cgac.cbnu.models.MachineFail;
//import kr.co.cgac.cbnu.repositories.ApartmentDAO;
//import kr.co.cgac.cbnu.repositories.MachineFailDAO;
//import kr.co.cgac.cbnu.utilities.Pagination;
//import kr.co.cgac.cbnu.utilities.StatusCode;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by cgac_315 on 6/16/2017.
// */
//@WebServlet(name = "ServletApartment" , urlPatterns = {"/apartments", "/apartment"})
//public class ServletMachineFail1 extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/x-www-form-urlencoded; charset=UTF-8 application/json");
//        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String json = "";
//        if(br != null){
//            json = br.readLine();
//        }
//
//        Gson gson = new Gson();
//        Map<String,Object> map1 = new HashMap<String,Object>();
//        map1 = (Map<String,Object>) gson.fromJson(json, map1.getClass());
//        MachineFail machineFail = new MachineFail(map1.get("fId").toString(),
//                map1.get("fLine").toString(),map1.get("fMachine").toString(),map1.get("downTime").toString(),
//                map1.get("restartTime").toString(),map1.get("fCode").toString(),map1.get("fSubCode").toString(),
//                map1.get("fDetail").toString(),map1.get("fPhen").toString(),map1.get("repairDetail").toString(),
//                map1.get("worker").toString(),map1.get("workStart").toString(),map1.get("workEnd").toString(),
//                Double.parseDouble(map1.get("workDuration").toString()));
//        boolean result = new MachineFailDAO().addMachineFail(machineFail);
//        Map<String, Object> map = new HashMap<String, Object>();
//        if (result) {
//            map.put("STATUS", StatusCode.SUCCESS);
//        } else {
//            map.put("STATUS", StatusCode.NOT_SUCCESS);
//        }
//        response.getWriter().write(new Gson().toJson(map));
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/x-www-form-urlencoded; charset=UTF-8 application/json");
//        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        String json = "";
//        if(br != null){
//            json = br.readLine();
//        }
//        Gson gson = new Gson();
//        Map<String,Object> map1 = new HashMap<String,Object>();
//        map1 = (Map<String,Object>) gson.fromJson(json, map1.getClass());
//        MachineFail machineFail = new MachineFail(map1.get("fId").toString(),
//                map1.get("fLine").toString(),map1.get("fMachine").toString(),map1.get("downTime").toString(),
//                map1.get("restartTime").toString(),map1.get("fCode").toString(),map1.get("fSubCode").toString(),
//                map1.get("fDetail").toString(),map1.get("fPhen").toString(),map1.get("repairDetail").toString(),
//                map1.get("worker").toString(),map1.get("workStart").toString(),map1.get("workEnd").toString(),
//                Double.parseDouble(map1.get("workDuration").toString()));
//        boolean result = new MachineFailDAO().updateMachineFail(machineFail);
//        Map<String, Object> map = new HashMap<String, Object>();
//        if (result) {
//            map.put("STATUS", StatusCode.SUCCESS);
//        } else {
//            map.put("STATUS", StatusCode.NOT_SUCCESS);
//        }
//        resp.getWriter().write(new Gson().toJson(map));
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/x-www-form-urlencoded; charset=UTF-8 application/json");
//        boolean result = new MachineFailDAO().deleteMachineFail(req.getParameter("id"));
//        Map<String, Object> map = new HashMap<String, Object>();
//        if (result) {
//            map.put("STATUS", StatusCode.SUCCESS);
//        } else {
//            map.put("STATUS", StatusCode.NOT_SUCCESS);
//        }
//        resp.getWriter().write(new Gson().toJson(map));
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/x-www-form-urlencoded; charset=UTF-8 application/json");
//        response.setCharacterEncoding("UTF-8");
//        String servletPath = request.getServletPath();
//        if (servletPath.equals("/apartments")) {
//            Pagination pagination = new Pagination();
//            pagination.setLimit(Integer.parseInt(request.getParameter("limit")));
//            pagination.setPage(Integer.parseInt(request.getParameter("page")));
//            String name = request.getParameter("name");
//            List<Apartment> apartmentList = new ApartmentDAO().getAllApartment(pagination, name);
//            Map<String, Object> map = new HashMap<String, Object>();
//            if (apartmentList != null) {
//                map.put("DATA", apartmentList);
//                map.put("STATUS", StatusCode.SUCCESS);
//                map.put("PAGINATION", pagination);
//            } else {
//                map.put("STATUS", StatusCode.NOT_SUCCESS);
//            }
//            response.getWriter().write(new Gson().toJson(map));
//        } else if (servletPath.equals("/apartment")) {
//            MachineFail machineFail = new MachineFailDAO().getMachineFails(request.getParameter("id"));
//            Map<String, Object> map = new HashMap<String, Object>();
//            if (machineFail != null) {
//                map.put("DATA", machineFail);
//                map.put("STATUS", StatusCode.SUCCESS);
//            } else {
//                map.put("STATUS", StatusCode.NOT_SUCCESS);
//            }
//            response.getWriter().write(new Gson().toJson(map));
//        }
//    }
//}
