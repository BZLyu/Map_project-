package Dijkstra;

import java.io.File;
import java.util.Scanner;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import javax.servlet.ServletException;

import java.io.IOException;


public class Main {

    public static Graph graph;

    public static void main(String[] args) throws IOException, LifecycleException, ServletException {
        File graphFile;
        if (args != null && args.length == 1) {
            graphFile = new File(args[0]);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Geben sie einen Dateipfad ein: ");
            graphFile = new File(scanner.next() + scanner.nextLine());
            //graphFile = new File("/Volumes/Life/PP/Map/toy.fmi");//"/Volumes/Life/PP/Map/germany.fmi";

        }
        if (graphFile.exists()) {
            String graphPath = null;
            try {
                graphPath = graphFile.getCanonicalPath();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(0);
            }

            Verbinder.setPath(graphPath);
            System.out.println("Loading...");
            Verbinder.getDijkstra();
            System.out.println("Loading finished. Starting server now.");

            // --------------start Server------

            System.out.println("-----start Server------");
            Tomcat tomcat = new Tomcat();
            String baseDir =System.getProperty("user.dir")
                    + File.separator+"src/webapp/";
            System.out.println("baseDir："+baseDir);
            tomcat.setPort(8080);
            tomcat.setBaseDir(baseDir);
            tomcat.addWebapp("", baseDir);

            tomcat.enableNaming();
            tomcat.start();
            System.out.println("-----------finished Server------");
            System.out.println("Aufgabe1: http://localhost:8080/Aufgabe1.html");
            System.out.println("Aufgabe2: http://localhost:8080");
            tomcat.getServer().await();


/*
        System.out.println("Geben sie einen Dateipfad ein: ");
        Scanner sc = new Scanner(System.in);
        String path = sc.next();

        graph = GraphFileReader.readFile(path, sc);

     *//*   DijkstraData data = Dijkstra.computeDijkstra(graph, 0);
        data.printSolution();
        ArrayList<Integer> Path = data.getpath(1);

        System.out.println(Path);
        System.out.println("-----------");
        data.printSolution();
        System.out.println();
        System.out.println();
        sc.close();*//*

        //        --------------start Server------

        System.out.println("-----start Server------");
        Tomcat tomcat = new Tomcat();
        String baseDir =System.getProperty("user.dir")
                + File.separator+"src/webapp/";
        System.out.println("baseDir："+baseDir);
        tomcat.setPort(8080);
        tomcat.setBaseDir(baseDir);
        tomcat.addWebapp("", baseDir);

        tomcat.enableNaming();
        tomcat.start();
        System.out.println("-----------finished Server------");
        System.out.println("Aufgabe1: http://localhost:8080/Aufgabe1.html");
        System.out.println("Aufgabe2: http://localhost:8080/Aufgabe2.html");
        tomcat.getServer().await();*/
        }}

    }

