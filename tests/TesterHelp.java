package ex1;


import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TesterHelp {

    private static Random _rnd = null;
    private static int _errors = 0, _tests = 0,_number_of_exception=0;
    private static String _log = "";
    private static String path = "/Users/yuval/Desktop/graph.txt";

    /////////// Private Functions /////////////////
    private static void test(String test, boolean val, boolean req) {
        test(test, "" + val, "" + req);
    }
    private static void test(String test, int val, int req) {
        test(test, ""+val, ""+req);
    }
    private static void test(String test, String val, String req) {
        boolean ans = true;
        ans = val.equals(req);
        String tt = _tests+") "+test+"  pass: "+ans;
        _log += "\n"+tt;
        if(!ans) {
            _errors++;
            String err = "  ERROR("+_errors+") "+val+"!="+req;
            System.err.println(tt+err);
            _log += err;

        }
        _tests++;
    }
     public static  weighted_graph specificGraph(){
        weighted_graph graph = new WGraph_DS();
         graph.addNode(0);
         graph.addNode(1);
         graph.addNode(2);
         graph.addNode(3);
         graph.addNode(4);
         graph.addNode(5);
         graph.addNode(6);
         graph.addNode(7);
         graph.addNode(8);
         graph.addNode(9);
         graph.connect(0,1,4);
         graph.connect(0,2,4);
         graph.connect(1,2,2);
         graph.connect(1,4,1);
         graph.connect(3,2,1);
         graph.connect(3,5,4);
         graph.connect(5,4,2);
         graph.connect(4,6,1);
         graph.connect(6,7,3);
         graph.connect(7,8,3);
         graph.connect(7,9,2);
         graph.connect(8,9,4);

         graph.connect(1,0,4);
         graph.connect(2,0,4);
         graph.connect(2,1,2);
         graph.connect(4,1,1);
         graph.connect(2,3,1);
         graph.connect(5,3,4);
         graph.connect(4,5,2);
         graph.connect(6,4,1);
         graph.connect(7,6,3);
         graph.connect(8,7,3);
         graph.connect(9,7,2);
         graph.connect(9,8,4);
         return graph;
     }
    public static boolean AreTheGraphsEquals(weighted_graph g1, weighted_graph g2) {
        if(g1.nodeSize() != g2.nodeSize()){
            return false;
        }
        if(g1.edgeSize() != g2.edgeSize()){
            return false;
        }
        for (node_info n1:g1.getV()){
            boolean Found = false;
            for (node_info n2:g2.getV()){
                if(n1.getKey() == n2.getKey()){
                    Found = true;
                }
            }
            if(!Found){ return false;}
            for (node_info ni1: g1.getV(n1.getKey())){
                boolean NiFound = false;
                for (node_info ni2: g2.getV(n1.getKey())){
                    if(ni1.getKey() == ni2.getKey()){
                        NiFound =  true;
                    }
                }
                if(!NiFound){ return false;}
            }
        }

        return true;
    }
        public static weighted_graph weighted_graph_creator(int v_size, int e_size, int seed){
            weighted_graph g = new WGraph_DS();
            _rnd = new Random(seed);
            for (int i = 0; i < v_size; i++) {
                g.addNode(i);
            }
             Iterator<node_info> itr = g.getV().iterator(); // Iterator is a more elegant and generic way, but KIS is more important
            int[] nodes = new int[g.nodeSize()];
            int i = 0;
            while(itr.hasNext()){
                nodes[i] = itr.next().getKey();
                i++;
            }
            while(g.edgeSize() < e_size) {
                int a = nextRnd(0,v_size);
                int b = nextRnd(0,v_size);
                int c = nextRnd(0,1000);
                double weight = (double)c/10;
                int k = nodes[a];
                int h = nodes[b];
                g.connect(k,h,c);
            }
            return g;
        }
        private static int nextRnd(int min, int max) {
            double v = nextRnd(0.0+min, (double)max);
            int ans = (int)v;
            return ans;
        }
        private static double nextRnd(double min, double max) {
            double d = _rnd.nextDouble();
            double dx = max-min;
            double ans = d*dx+min;
            return ans;
        }

        }


