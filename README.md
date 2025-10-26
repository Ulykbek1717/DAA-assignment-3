Introduction
Efficient urban transportation planning requires minimizing the total cost of connecting all districts while maintaining full accessibility. This problem is represented mathematically as finding a Minimum Spanning Tree (MST) in a weighted undirected graph. Each vertex represents a district, and each edge represents a possible road with a construction cost as its weight. This assignment applies Prim’s and Kruskal’s algorithms to identify the MST, compare their computational performance, and analyze their efficiency depending on graph characteristics such as density and size.

Purpose of the Work
The primary goal of this project is to develop a Java-based application that:

Reads graph data from a JSON input file representing city districts and potential road connections.

Applies Prim’s and Kruskal’s algorithms to construct the MST.

Records performance metrics, including execution time and operation counts.

Produces a JSON output file summarizing the MST edges, total cost, and algorithmic efficiency. The ultimate objective is to determine the optimal algorithm for various types of transportation networks and understand trade-offs between computation time and implementation complexity.
