# Map_Project

> Use Dijkstra,KDTree,Tomcat, Ajax, Servlet ,Leaflet.

# Dijkstra:

    public class Dijkstra {
    
        private final int NONE = -1;
        private final int INFINITY = Integer.MAX_VALUE;
    
        private Graph graphData;
        private boolean hasOneToAllData;
    
        private int[] dijkstraResult;
        private int[] dijkstraPath;
        private int fromNode = -1;
    
        public Dijkstra(Graph data) {
            graphData = data;
        }
    
        public void executeOneToAll(int from) {
            fromNode = from;
            Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(value -> value[1]));
            dijkstraResult = new int[graphData.getTotalNodes()];
            Arrays.fill(dijkstraResult, INFINITY);
            dijkstraPath = new int[graphData.getTotalNodes()];
            Arrays.fill(dijkstraPath, NONE);
            boolean[] visited = new boolean[graphData.getTotalNodes()];
            int current, edge, index, target, shortest;
            dijkstraResult[from] = 0;
            queue.add(new int[] { from, 0 });
            while (!queue.isEmpty()) {
                current = queue.poll()[0];
                if (!visited[current]) {
                    visited[current] = true;
                    for (edge = graphData.getEdgesFor(current); --edge >= 0;) {
                        index = graphData.getOffsetFor(current) + edge;
                        target = graphData.getTargetFor(index);
                        if (!visited[target]) {
                            shortest = graphData.getDistanceFor(index) + dijkstraResult[current];
                            if (shortest < dijkstraResult[target]) {
                                dijkstraResult[target] = shortest;
                                dijkstraPath[target] = current;
                                queue.add(new int[] { target, shortest });
                            }
                        }
                    }
                }
            }
            hasOneToAllData = true;
        }
    
        public void calculateShortest(int from, int to) {
            hasOneToAllData = false;
            fromNode = from;
            Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(value -> value[1]));
            dijkstraResult = new int[graphData.getTotalNodes()];
            Arrays.fill(dijkstraResult, INFINITY);
            dijkstraPath = new int[graphData.getTotalNodes()];
            Arrays.fill(dijkstraPath, NONE);
            boolean[] visited = new boolean[graphData.getTotalNodes()];
            int current, edge, index, target, shortest;
            dijkstraResult[from] = 0;
            queue.add(new int[] { from, 0 });
            while (!queue.isEmpty()) {
                current = queue.poll()[0];
                if (!visited[current]) {
                    visited[current] = true;
                    for (edge = graphData.getEdgesFor(current); --edge >= 0;) {
                        index = graphData.getOffsetFor(current) + edge;
                        target = graphData.getTargetFor(index);
                        if (!visited[target]) {
                            shortest = graphData.getDistanceFor(index) + dijkstraResult[current];
                            if (shortest < dijkstraResult[target]) {
                                dijkstraResult[target] = shortest;
                                dijkstraPath[target] = current;
                                queue.add(new int[] { target, shortest });
                            }
                        }
                    }
                }
                if (current == to) {
                    break;
                }
            }
        }
    
        public int getShortestPathDistance(int toNode) {
            return dijkstraResult[toNode];
        }
    
        public List<Integer> getShortestPath(int toNode) {
            List<Integer> dijkstraShortestPath = new ArrayList<>();
            if (dijkstraResult == null || fromNode == toNode) {
                return dijkstraShortestPath;
            }
            dijkstraShortestPath.add(toNode);
            int value = dijkstraPath[toNode];
            while (value != NONE && value != fromNode) {
                dijkstraShortestPath.add(value);
                value = dijkstraPath[value];
            }
            dijkstraShortestPath.add(fromNode);
            return dijkstraShortestPath;
        }
    
        public boolean hasOneToAllData() {
            return hasOneToAllData;
        }
    
        public int getOneToAllNode() {
            return fromNode;
        }
    
        public int getClosestNodeId(double lat, double lng) {
            return graphData.getClosestNodeFor(lat, lng);
        }
    
        public double[] getLatLngFor(int node) {
            return graphData.getLatLngFor(node);
        }

# 2. KDTree

    public class KdTree {
    
        public KdTree(){
    
        }
    
        public static double variance(double[][] point,int dimention){
            double vsum = 0;
            double sum = 0;
            for(double[] d:point){
                sum+=d[dimention];
                vsum+=d[dimention]*d[dimention];
            }
            int n = point.length;
            return vsum/n-Math.pow(sum/n, 2);
        }
    
       public static kdNode insert(double[][] point,int depth){
            if(point.length==0){
                return null;
            }
            int medium_index=point.length/2;
    
            int partitionDimention=-1;
           double var = -1;
           double tmpvar;
           for(int i=0;i<2;i++){
               tmpvar=variance(point, i);
               if (tmpvar>var){
                   var = tmpvar;
                   partitionDimention = i;
               }
           }
    
           if(partitionDimention==0) {
               Arrays.sort(point,new Comparator<double[]>()  {
                   public int compare(double[] x, double[] y) {
                       return Double.compare(x[0], y[0]);
                   }
               });
           }else {
               Arrays.sort(point, new Comparator<double[]>() {
                   public int compare(double[] x, double[] y) {
                       return Double.compare(x[1], y[1]);
                   }
               });
    
           }
           double max=0,min=0;
           kdNode node;
          // node.partitionDimention=partitionDimention;
           if(partitionDimention==0){ //jetzt X
               for(int i=0;i<point.length;i++)
               {
                   if(point[i][1]>max)   // Maxy
                       max=point[i][1];
                   if(point[i][1]<min)   // Miny
                       min=point[i][1];}
                   node=new kdNode(point[medium_index],new Rect(point[0][0],min,point[point.length-1][0],max),partitionDimention);
               }
           else //jetzt y
           {
               for(int i=0;i<point.length;i++)
               {
                   if(point[i][0]>max)   // Maxx
                       max=point[i][0];
                   if(point[i][0]<min)   // Minx
                       min=point[i][0];}
                   node=new kdNode(point[medium_index],new Rect(min,point[0][1],max,point[point.length-1][1]),partitionDimention);
               }
    
    
            double[][] left=new double[medium_index][2];
           for (int i = 0; i < medium_index; i++) {
               left[i]=point[i];
           }
    
           node.left=insert(left,depth+1);
           left=null;
           double[][]right= new double[point.length-medium_index-1][2];
           int j=0;
           for (int i = medium_index+1; i < point.length; i++) {
               right[j]=point[i];
               j++;
           }
    
    
    
           node.right=insert(right ,depth+1);
           right=null;
    
           return node;
        }
    
        public kdNode nearest(double[] target,kdNode root) {
    
            return nearest(target, root, null);
        }
        private kdNode nearest(double[] target, kdNode node,kdNode currentBest) {
    
           if (node == null){return currentBest;}
           int dimention=node.partitionDimention;
            double value1 =target[dimention];
            double value2 =node.daten[dimention];
            kdNode next;
            kdNode other;
    
            next= value1 < value2? node.left:node.right;
            other= value1 < value2? node.right:node.left;
    
            kdNode nextBest = nearest(target, next, node);//zuerste Tiefsuche bis Leaf.
            double currentDistance;
    
            double nextDistance = nextBest.distanceSquareTo(target);
            if (currentBest == null) {
                currentBest = nextBest;
                currentDistance = nextDistance;
            } else {
                currentDistance = currentBest.distanceSquareTo(target);//find nearnest Punkt und Distanz.
                    if(nextDistance < currentDistance ){
                    currentBest = nextBest;
                    currentDistance = nextDistance;}
            }
    
            if ((other != null) && (other.rect.distanceSquareToPoint(target) < currentDistance)) {
                currentBest =nearest(target, other, currentBest);
            }
    
            return currentBest;}

# 3. Tomcat

## Use Embedded Tomcat

    import org.apache.catalina.LifecycleException;
    import org.apache.catalina.startup.Tomcat;
    
    
    tomcat.setPort(8080);
    tomcat.setBaseDir(baseDir);
    tomcat.addWebapp("", baseDir);
    

# 4. Ajax

    var url1="RoutenplanungServlet";
    
                $.ajax({url:url1,
                    data:{"start_ID":start_ID,"ziel_ID":ziel_ID},
                    type:"get",
                    datatype:"json",
                    traditional: true,
                    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                    success:function(data){
    
                    if (data!=null){
                        alert(data);
                        var arraydata = eval(data);
                        var position=[]
                        for (var i=0;i<arraydata.length;i++){
                            var lang=position.push(arraydata[i]);
                        }
                        Line=L.polyline(position, {color: 'red'}).addTo(mymap);
                        marker1 = L.marker(position[0]).addTo(mymap);
                        marker1.bindPopup("Start from hier! ").openPopup();
                        marker2 = L.marker(position[position.length-1]).addTo(mymap);
                        marker2.bindPopup("End hier! ").openPopup();
                    }
    
                    else {alert("No path available!")}

# 5. Servlet

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          long start_ID=0;
          long ziel_ID=0;
          List<Integer> path=null;
          Dijkstra dijkstra=null;
          ArrayList<double[]> latlon =new ArrayList<>();
    
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        start_ID=Long.parseLong(request.getParameter("start_ID").trim());
        ziel_ID=Long.parseLong(request.getParameter("ziel_ID").trim());
    
    
        dijkstra= Verbinder.getDijkstra();
        dijkstra.calculateShortest((int)start_ID, (int)ziel_ID);
        path=dijkstra.getShortestPath((int)ziel_ID);
        if (path!=null){
            for (int point:path) {
                latlon.add(dijkstra.getLatLngFor(point));
            }
        }
        String json = JSON.toJSONString(latlon);
    
        PrintWriter a = response.getWriter();
        a.print(json);
    }

# 6.Leaflet

[https://leafletjs.com/](https://leafletjs.com/)

    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
              integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
              crossorigin=""/>
        <script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
                integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
                crossorigin=""></script>
        <div id="myMap"></div>
    
        <style>
            #myMap {
                height: 500px;
            }</style>
    
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    
        <script language="JavaScript">
    
    
    </head>
    <body>
    
    <h1> Aufgabe 2 </h1>
    <button type="button" onclick="nextpunkt()">Nextpunkt</button>
    <button type="button" onclick="Routingplan()">Routingplan</button>
    <button type="button" onclick="clean()">clanMap</button>
    
    <script> // packet jquery
        var mymap = L.map('myMap').setView([48.744442, 9.106384], 13);//48.744442, 9.106384
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery &copy <a href="https://www.mapbox.com/">Mapbox</a>'
        }).addTo(mymap);
    
    var popup = L.popup()
    
    mymap.on('click', onMapClick);
    
    var marker1 =null;
    var marker2 =null;
    var Line =null;
    
    
    function onMapClick(e) {
        popup
            .setLatLng(e.latlng)
            .setContent("You clicked the map at " + e.latlng.toString())
            .openOn(mymap);
        return popup
    }
    function clean() {
        mymap.removeLayer(marker1);
        mymap.removeLayer(marker2);
        mymap.removeLayer(Line);
    }
    </script>
    
    
    </body>
    </html>
