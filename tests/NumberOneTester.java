package ex1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
class NumberOneTester {

    @Test
      void RunAllTests(){
        System.out.println("Running tests for Ex0 - this may take up to 10 seconds!");
        String path = "/Users/yuval/Desktop/graph2.txt";
        double RUN = new Date().getTime();
        try {test0();}
        catch(Exception a){System.out.println("test0 has failed");}
        try {test1();}
        catch(Exception a){System.out.println("test1 has failed");}
        try {test2();}
        catch(Exception a){System.out.println("test2 has failed");}
        try {test3();}
        catch(Exception a){System.out.println("test3 has failed");}
        try {test4();}
        catch(Exception a){System.out.println("test4 has failed");}
        try {test5(path);}
        catch(Exception a){System.out.println("test5 has failed");}
        double FINISH = new Date().getTime();
        double runtime = (FINISH - RUN)/1000.0;
        System.out.println("runtime is: "+runtime);
    }
         void test0() throws IOException { // check empty graph.
        weighted_graph g = TesterHelp.weighted_graph_creator(0,0,1);
        weighted_graph_algorithms ga = new WGraph_Algo();
        ga.init(g);
        int a = g.nodeSize(); // should be 0
        g.connect(0,1,3.5);
        int b = g.edgeSize(); //should be 0
        double c = g.getEdge(0,1);// should be -1
        boolean d = g.hasEdge(0,1);// should be false
        boolean e = ga.isConnected();// should be true
        double f = ga.shortestPathDist(0,1);//should be -1
       // LinkedList<node_info> list = (LinkedList) ga.shortestPath(0,1);//should be empty list
        weighted_graph g1 = ga.copy();
        ga.init(g1);
        int a1 = g1.nodeSize();
        g.connect(0,1,3.5);
        int b1 = g1.edgeSize();
        double c1 = g1.getEdge(0,1);
        boolean d1 = g1.hasEdge(0,1);
        boolean e1 = ga.isConnected();
        double f1 = ga.shortestPathDist(0,1);
        LinkedList<node_info> list1 = (LinkedList) ga.shortestPath(0,1);
        assertEquals(0,a,"test0 error (empty graph) in NodeSize");
        assertEquals(0,b,"test0 error (empty graph) in EdgeSize");
        assertEquals(-1,c,"test0 error (empty graph) in getEdge");
        assertEquals(false,d,"test0 error (empty graph) in hasEdge");
        assertEquals(true,e,"test0 error (empty graph) in isConnected");
        assertEquals(-1,f,"test0 error (empty graph) in ShortestPathDist");

        //******checking copy*******
        assertEquals(a,a1,"test0 error (copy) in NodeSize");
        assertEquals(b,b1,"test0 error (copy) in EdgeSize");
        assertEquals(c,c1,"test0 error (copy) in getEdge");
        assertEquals(d,d1,"test0 error (copy) in hasEdge");
        assertEquals(e,e1,"test0 error (copy) in isConnected");
        assertEquals(f,f1,"test0 error (copy) in ShortestPathDist");

    }
         void test1() { // check 1 node graph
        weighted_graph g = TesterHelp.weighted_graph_creator(1,0,1);
        weighted_graph_algorithms ga = new WGraph_Algo();
        ga.init(g);
        int a = g.nodeSize();//should be 1
        g.connect(0,0,3.5);
        int b = g.edgeSize();//should be 0
        double c = g.getEdge(0,0);//should be -1
        boolean d = g.hasEdge(0,0);//should be false
        boolean e = ga.isConnected();//should be true
        double f = ga.shortestPathDist(0,0);//should be 0
        weighted_graph g1 = ga.copy();
        ga.init(g1);
        int a1 = g1.nodeSize();
        g.connect(0,1,3.5);
        int b1 = g1.edgeSize();
        double c1 = g1.getEdge(0,0);
        boolean d1 = g1.hasEdge(0,0);
        boolean e1 = ga.isConnected();
        double f1 = ga.shortestPathDist(0,0);
        LinkedList<node_info> list1 = (LinkedList) ga.shortestPath(0,1);

        assertEquals(1,a,"test1 error in NodeSize");
        assertEquals(0,b,"test1 error in EdgeSize");
        assertEquals(-1,c,"test1 error in getEdge");
        assertEquals(false,d,"test1 error in hasEdge");
        assertEquals(true,e,"test1 error in isConnected");
        assertEquals(0,f,"test1 error in ShortestPathDist");

        //******checking copy*******
        assertEquals(a,a1,"test1 error (copy) in NodeSize");
        assertEquals(b,b1,"test1 error (copy) in EdgeSize");
        assertEquals(c,c1,"test1 error (copy) in getEdge");
        assertEquals(d,d1,"test1 error (copy) in hasEdge");
        assertEquals(e,e1,"test1 error (copy) in isConnected");
        assertEquals(f,f1,"test1 error (copy) in ShortestPath");

    }
         void test2() { // check 2 nodes 1 edge graph
        weighted_graph g = TesterHelp.weighted_graph_creator(2,0,1);
        weighted_graph_algorithms ga = new WGraph_Algo();
        ga.init(g);
        int a = g.nodeSize();//should be 2
        g.connect(0,1,3.5);
        g.connect(0,1,4);
        int b = g.edgeSize();//should be 1
        double c = g.getEdge(0,1);//should be 4
        boolean d = g.hasEdge(0,1);//should be true
        boolean e = ga.isConnected();//should be true
        double f = ga.shortestPathDist(0,1);//should be 4
        //LinkedList<node_info> list = (LinkedList) ga.shortestPath(0,0);//should be list with 2 nodes
        weighted_graph g1 = ga.copy();
        ga.init(g1);
        int a1 = g1.nodeSize();
        g.connect(0,1,3.5);
        int b1 = g1.edgeSize();
        double c1 = g1.getEdge(0,1);
        boolean d1 = g1.hasEdge(0,1);
        boolean e1 = ga.isConnected();
        double f1 = ga.shortestPathDist(0,1);
        LinkedList<node_info> list1 = (LinkedList) ga.shortestPath(0,1);

             assertEquals(2,a,"test2 error in NodeSize");
             assertEquals(1,b,"test2 error in EdgeSize");
             assertEquals(4,c,"test2 error in getEdge");
             assertEquals(true,d,"test2 error in hasEdge");
             assertEquals(true,e,"test2 error in isConnected");
             assertEquals(4,f,"test2 error in ShortestPathDist");

             //******checking copy*******
             assertEquals(a,a1,"test2 error (copy) in NodeSize");
             assertEquals(b,b1,"test2 error (copy) in EdgeSize");
             assertEquals(c,c1,"test2 error (copy) in getEdge");
             assertEquals(d,d1,"test2 error (copy) in hasEdge");
             assertEquals(e,e1,"test2 error (copy) in isConnected");
             assertEquals(f,f1,"test2 error (copy) in ShortestPath");
    }
         void test3() { // check specific graph
        int [] ArrForList = {3,2,1,4};
        weighted_graph g = TesterHelp.specificGraph();
        weighted_graph_algorithms ga = new WGraph_Algo();
        ga.init(g);
        int a = g.nodeSize();//should be 10
        int b = g.edgeSize();//should be 12
        double c = g.getEdge(4,6);//should be 1
        boolean d = g.hasEdge(2,3);//should be true
        boolean e = ga.isConnected();//should be true
        double f = ga.shortestPathDist(3,4);//should be 4
        LinkedList <node_info> Mylist = (LinkedList) ga.shortestPath(3,4);//3->2->1->4
             boolean isListCorrect = true;
             int i = 0;
             for (node_info n : Mylist) {
                 if(ArrForList[i] != n.getKey()){
                     isListCorrect = false;
                 }
                 i++;
             }
        g.removeNode(6);
        g.removeEdge(1,4);
        int ra = g.nodeSize();//should be 9
        int rb = g.edgeSize();//should be 9
        double rc = g.getEdge(4,6);//should be -1
        boolean rd = g.hasEdge(1,4);//should be false
        boolean re = ga.isConnected();//should be false
        double rf = ga.shortestPathDist(1,4);//should be 9
        weighted_graph g1 = ga.copy();
        ga.init(g1);
        int a1 = g1.nodeSize();
        int b1 = g1.edgeSize();
        double c1 = g1.getEdge(4,6);
        boolean d1 = g1.hasEdge(1,4);
        boolean e1 = ga.isConnected();
        double f1 = ga.shortestPathDist(1,4);
        LinkedList<node_info> list1 = (LinkedList) ga.shortestPath(0,1);
        assertEquals(10,a,"test3 error in NodeSize");
        assertEquals(12,b,"test3 error in EdgeSize");
        assertEquals(1,c,"test3 error in getEdge");
        assertEquals(true,d,"test3 error in hasEdge");
        assertEquals(true,e,"test3 error in isConnected");
        assertEquals(4,f,"test3 error in ShortestPathDist");
        assertEquals(true,isListCorrect,"test3 error in ShortestPath");
             //******checking after remove******
        assertEquals(9,ra,"test3 (after remove) error in NodeSize");
        assertEquals(9,rb,"test3 (after remove) error in EdgeSize");
        assertEquals(-1,rc,"test3 (after remove) error in getEdge");
        assertEquals(false,rd,"test3 (after remove) error in hasEdge");
        assertEquals(false,re,"test3 (after remove) error in isConnected");
        assertEquals(9,rf,"test3 (after remove) error in ShortestPathDist");

        //******checking copy*******

        assertEquals(ra,a1,"test3 error (copy) in NodeSize");
        assertEquals(rb,b1,"test3 error (copy) in EdgeSize");
        assertEquals(rc,c1,"test3 error (copy) in getEdge");
        assertEquals(rd,d1,"test3 error (copy) in hasEdge");
        assertEquals(re,e1,"test3 error (copy) in isConnected");
        assertEquals(rf,f1,"test3 error (copy) in ShortestPath");

    }
    void test4(){
        weighted_graph g = TesterHelp.weighted_graph_creator(1000,5000,1);//1000 Nodes and 5000 edges
        int a = g.nodeSize();
        int b = g.edgeSize();
        assertEquals(1000,a,"test4 error in NodeSize");
        assertEquals(5000,b,"test4 error in EdgeSize");
    }
    void test5(String path) {
        weighted_graph g = TesterHelp.specificGraph();
        weighted_graph_algorithms ga = new WGraph_Algo();
        ga.init(g);
        ga.save(path);
        weighted_graph g1 = new WGraph_DS();
        ga.init(g1);
        ga.load(path);
        boolean a = TesterHelp.AreTheGraphsEquals(ga.getGraph(),g);
        assertEquals(true,a,"test5 error in load/save");
    }
}


