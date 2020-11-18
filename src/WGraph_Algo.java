package ex1;
import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms{
        weighted_graph g;

    public WGraph_Algo(){
         this.g = new WGraph_DS();
    }
    /**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
    this.g = g;
    }
    /**
     * Return the underlying graph of which this class works.
     * @return
     */
    @Override
    public weighted_graph getGraph() {
        return g;
    }

    /**
     * Compute a deep copy of this weighted graph.
     * in this method , First of all i make a deep copy of all the vertices
     * and then i connect between them.
     * @return
     */
    @Override
    public weighted_graph copy() {
        weighted_graph newG = new WGraph_DS();
        for (node_info node : g.getV()){
            newG.addNode(node.getKey());
            newG.getNode(node.getKey()).setInfo(node.getInfo());
            newG.getNode(node.getKey()).setTag(node.getTag());
        }
        for (node_info node:g.getV()){
            for (node_info ni:g.getV(node.getKey())){
                newG.connect(node.getKey(),ni.getKey(), g.getEdge(node.getKey(),ni.getKey()));
            }
        }
        return newG;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * in this method i traverse the graph using BFS marking every node tag starting from an arbitrary node
     * at the end i check if there is an unmarked node to know if the graph is connected.q
     * @return
     */
    @Override
    public boolean isConnected() {
        if(g.nodeSize() == 1 || g.nodeSize() == 0) return true;
        boolean isConnected = true;
        Queue<node_info> nodes = new LinkedList<node_info>();
        node_info start = null;
        for (node_info node : g.getV()){
            node.setTag(-1);
        }
        for (node_info n : g.getV()) {
            start = n;
            if (n != null) {
                n.setTag(0);
                break;
            }
        }
        if (start == null) return true;
        nodes.add(start);
        while (!nodes.isEmpty()) {
            node_info curr = nodes.poll();
            for (node_info ni : g.getV(curr.getKey())) {
                if (ni.getTag() == -1) {
                    nodes.add(ni);
                    ni.setTag(0);
                }
            }
        }
        for (node_info node : g.getV()) {
            if (node.getTag() == -1) isConnected = false;
            node.setTag(-1);
        }
        return isConnected;
    }

    /**
     * returns the length of the shortest path between src to dest
     * this method traverse the graph using BFS starting from src marking every node's tag
     * when reaching a specific node if the previous tag the node contains is higher then the curr path
     * the node's tag will be updated , when in the end the dest node tag is going to be the shortest distance.
     * @param src  - start node
     * @param dest - end (target) node
     * @return the weight of the shortest path , -1 if there isn't
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        for (node_info n: g.getV()){
            n.setTag(Integer.MAX_VALUE);
        }
        node_info start = g.getNode(src);
        node_info end = g.getNode(dest);
        if(start == null || end == null) return -1;
        if (src == dest) return 0;
        Queue<node_info> q = new LinkedList<node_info>();
        q.add(start);
        start.setTag(0);
        while(!q.isEmpty()){
           node_info curr = q.poll();
            for (node_info node : g.getV(curr.getKey())){
                if (node.getTag() > curr.getTag() + g.getEdge(curr.getKey(),node.getKey())){
                    q.add(node);
                    node.setTag(curr.getTag() + g.getEdge(curr.getKey(), node.getKey()));
                }
            }
        }
        if(end.getTag() == Integer.MAX_VALUE) return -1;
        return end.getTag();
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * this method calls shortestPathDist so i will have the nodes in the path marked
     * using BuildList the path is built.
     * @param src  - start node
     * @param dest - end (target) node
     * @return list containing the nodes in the path , null if there is no path
     */
    @Override

    public List<node_info> shortestPath(int src, int dest) {
        List<node_info> path = new LinkedList<>();
        node_info start = g.getNode(src);
        double dist = shortestPathDist(src,dest);
         if (dist == -1) return path;
         else if(dist == 0){ path.add(start); return path;}
        return Buildlist(path,dest,src);
   }
    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * this method calls herself recursively after finding the previous node in the shortestPath
     * @param src  - start node , curr - the current node in the path LIST - the Current list
     * @return list containing the nodes in the path , null if there is no path
     */
    private List<node_info> Buildlist(List<node_info> path,int curr,int src) {
        node_info currNode = g.getNode(curr);
        if (curr ==  src) { path.add(currNode); return path;}
        else {
            for (node_info node : g.getV(curr)) {
                if (currNode.getTag() == node.getTag() + g.getEdge(curr, node.getKey())) {
                    path = Buildlist(path, node.getKey(), src);
                    path.add(currNode);
                    break;
                }
            }
            return path;
        }
    }
    /**
     * Saves this weighted (undirected) graph to the given
     * this method uses Serialization class in order to save the object in a serial number in the file
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.g);
            out.close();
            fileOut.close();
        } catch (IOException i) {
                i.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method load a graph to this graph algorithm.
     * this method uses Serialization class in order to load the object from a serial number in the file
     * then inits the graph loaded from the file
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        weighted_graph newG = new WGraph_DS();
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
             newG =(weighted_graph) in.readObject();
            init(newG);
            in.close();
             fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return false;
        }
        return true;
    }

}
