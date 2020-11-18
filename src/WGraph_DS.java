package ex1;

import org.w3c.dom.Node;

import java.io.Serializable;
import java.util.*;

public class WGraph_DS implements weighted_graph, Serializable {
    private static int id = 0;
    int EdgeSize,MC;
    HashMap<Integer,node_info> Nodes;
    HashMap<Integer,HashMap<Integer,Double>> WeightedEdges;
    HashMap<Integer,HashMap<Integer,node_info>> NodeEdges;

    public WGraph_DS(){
        this.Nodes = new HashMap<Integer,node_info>();
        this.WeightedEdges = new HashMap<Integer,HashMap<Integer,Double>>();
        this.NodeEdges = new HashMap<Integer,HashMap<Integer,node_info>>();

    }
    /**
     * return the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_info getNode(int key) {
        if(!Nodes.containsKey(key)) return null;
        return Nodes.get(key);
    }

    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * @param node1
     * @param node2
     * @return true if there's an edge
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        return  Nodes.containsKey(node1) &&
                Nodes.containsKey(node2) &&
                WeightedEdges.get(node1).containsKey(node2) &&
                WeightedEdges.get(node2).containsKey(node1) &&
                NodeEdges.get(node1).containsKey(node2) &&
                NodeEdges.get(node2).containsKey(node1) &&
                node1 != node2;
    }

    /**
     * return the weight if the edge (node1, node1).
     * @param node1
     * @param node2
     * @return the weight is there is an edge , -1 is there isn't
     */
    @Override
    public double getEdge(int node1, int node2) {
        if(hasEdge(node1,node2)) return WeightedEdges.get(node1).get(node2);
        return -1;
    }

    /**
     * add a new node to the graph with the given key.
     * @param key
     */
    @Override
    public void addNode(int key) {
        if(this.Nodes.containsKey(key)) return;
        node_info newNode = new NodeInfo(key);
        this.Nodes.put(key,newNode);
        this.WeightedEdges.put(key,new HashMap<Integer, Double>());
        this.NodeEdges.put(key,new HashMap<Integer, node_info>());
        MC++;
    }

    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * if there is an edge , replace the weight
     * @param node1
     * @param node2
     * @param w
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if(Nodes.containsKey(node1) && Nodes.containsKey(node2) && node1 != node2) {
            if (!hasEdge(node1,node2)){
                NodeEdges.get(node2).put(node1, Nodes.get(node1));
                NodeEdges.get(node1).put(node2, Nodes.get(node2));
                EdgeSize++;
        }
            WeightedEdges.get(node1).put(node2,w);
            WeightedEdges.get(node2).put(node1,w);
            MC++;

            }
        }

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV() {
        return Nodes.values();
    }

    /**
     * This method returns a Collection containing all the
     * nodes connected to node_id
     * @param node_id
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        if(Nodes.containsKey(node_id)) {
            return NodeEdges.get(node_id).values();
        }
        else return null;
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_info removeNode(int key) {
        node_info RemovedNode = getNode(key);
        if(RemovedNode != null){
            for (node_info ni: getV(key)){
                WeightedEdges.get(ni.getKey()).remove(key);
                NodeEdges.get(ni.getKey()).remove(key);
                EdgeSize--;
                MC++;
            }
            NodeEdges.remove(key);
            WeightedEdges.remove(key);
            Nodes.remove(key);
            MC++;
            return RemovedNode;
        }
        return null;
    }

    /**
     * Delete the edge from the graph,
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if(hasEdge(node1,node2)){
            WeightedEdges.get(node1).remove(node2);
            WeightedEdges.get(node2).remove(node1);
            NodeEdges.get(node1).remove(node2);
            NodeEdges.get(node2).remove(node1);
            EdgeSize--;
            MC++;
        }
    }

    /**
     * return the number of vertices (nodes) in the graph.
     * @return
     */
    @Override
    public int nodeSize() {
        return Nodes.size();
    }

    /**
     * return the number of edges (undirectional graph).
     * @return
     */
    @Override
    public int edgeSize() {
        return EdgeSize;
    }

    /**
     * return the Mode Count - for testing changes in the graph.
     * @return
     */
    @Override
    public int getMC() {
        return MC;
    }
    public class NodeInfo implements node_info, Serializable {
        private int id;
        private int key;
        private double tag;
        private String info;

        public NodeInfo(){
            this.key = id++;
        }
        public NodeInfo(int key){
            this.key = key;
        }
        /**
         * Return the key (id) associated with this node.
         * @return
         */
        @Override
        public int getKey() {
            return this.key;
        }

        /**
         * return the remark (meta data) associated with this node.
         * @return
         */
        @Override
        public String getInfo() {
            return this.info;
        }

        /**
         * Allows changing the remark (meta data) associated with this node.
         * @param s
         */
        @Override
        public void setInfo(String s) {
            this.info  = s;
        }

        /**
         * Temporal data (aka distance, color, or state)
         * which can be used be algorithms
         * @return
         */
        @Override
        public double getTag() {
            return this.tag;
        }

        /**
         * Allow setting the "tag" value for temporal marking an node - common
         * practice for marking by algorithms.
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(double t) {
            this.tag = t;
        }
    }


}

