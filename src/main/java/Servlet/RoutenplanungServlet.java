package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import Dijkstra.Dijkstra;
import Dijkstra.Verbinder;
import com.alibaba.fastjson.JSONObject;

@WebServlet(name = "ServletRoutenplanung",urlPatterns = {"/RoutenplanungServlet"})

public class RoutenplanungServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }


        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              String start_ID1=request.getParameter("start_ID");
              String ziel_ID1=request.getParameter("ziel_ID");
              int start_ID= Integer.parseInt(start_ID1);
              int ziel_ID= Integer.parseInt(ziel_ID1);
              List<Integer> path;
              Dijkstra dijkstra;
              ArrayList<double[]> latlon =new ArrayList<>();

            dijkstra= Verbinder.getDijkstra();
            dijkstra.calculateShortest(start_ID, ziel_ID);
            path=dijkstra.getShortestPath(ziel_ID);

            if (path.size()!=0){
                for (int point:path) {
                    latlon.add(dijkstra.getLatLngFor(point));
                }
            }
            String json = JSON.toJSONString(latlon);

            PrintWriter a = response.getWriter();
            a.print(json);
        }


}
