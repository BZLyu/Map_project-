package Servlet;


import Dijkstra.Dijkstra;
import Dijkstra.Verbinder;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletEnd",urlPatterns = {"/ServletEnd"})

public class Setend extends HttpServlet {



    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lon1;
        String lat1;
        Dijkstra dijkstra;

        lon1 = request.getParameter("end_lon").trim();
        lat1 = request.getParameter("end_lat").trim();

        double lat= Double.parseDouble(lat1);
        double lon=Double.parseDouble(lon1);;

        dijkstra= Verbinder.getDijkstra();
        int point =dijkstra.getClosestNodeId(lat,lon);
        double[] position =dijkstra.getLatLngFor(point);
        double[] data = {point,position[0],position[1]};
       // System.out.println("End_point: "+ point+", lat:"+position[0]+", lon:" +position[1]);
        String json = JSON.toJSONString(data);
        PrintWriter a = response.getWriter();
        a.print(json);



    }

}


