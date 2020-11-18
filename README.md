# Ex0


For Installation https://github.com/simon-pikalov/Ariel_OOP_2020/tree/master/Assignments/Ex0


### Author:
Yuval Ben Yaakov  
-----------


## Summary:
-------
The project focus on unweighted undirected graph and has three main Classes.
1. node_data - an interface that represents a node in the graph implemented by NodeData.
2. graph - an interface that represents an undirected unweighted graph implemented by Graph_DS.
3. graph_algorithms - an interface that represents a set of algorithms to perform on a graph implemented by Graph_Algo.
 
## Setup and data structure
my setup for the graph is using in NodeData an HashMap containing all adjacent nodes , the reason for hashtable is for making most of my methods in NodeData and Graph_DS O(1)
in Graph_DS there is an HashMap containing all the nodes in the graph , reason is the same as above.
## NodeInfo class methods:
------ 
all the methods below are O(1)
1. getKey – returns the key (id) of the node 
2. getInfo  – returns the info of the node
3. setInfo – set the info of the node 
4. getTag – returns the tag of the node 
5. setTag - set the tag of the node

## WGraph_DS class methods: 
-----
1. getNode – returns a node contained in the graph O(1)
2. hasEdge – checks if theres an edge between 2 nodes O(1)
3. addNode – Add a node to the graph O(1)
4. connect – connects an edge between 2 nodes with a given weight O(1)
5. getV() - returns a collection of all the nodes in the graph O(1)
6. getV(node_id) - returns a collection of all the adjacent nodes of a node with the node_id O(1)
7. removeNode -  removes a node from the graph and all its edges O(n)
8. removeEdge – Removes an edge between 2 given nodes from the graph O(1)
9. nodeSize – Returns the number of nodes in the graph O(1)
10. edgeSize – returns the number of edges in the graph O(1)
11. getMC – returns the number of changes made in the graph O(1)

## WGraph_Algo class methods: 
-----
1. init - inits a graph that the algorithms performs on O(1)
2. GetGraph - returns the graph the algorithms performs on
3. copy - makes a deep copy of the graph O(V+E)
4. isConnected - checks if there is a path from every 2 nodes in the graph
5. shortestPathDist - returns the shortest path (by edges weight )
6. shortestPath - returns a list  containing the node's in the shortest path 
7. save - save the graph that the algorithms performs on
8. load - loads a graph from a file to perform algorithms on
