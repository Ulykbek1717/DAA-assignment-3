1. Introduction

Efficient urban transportation planning requires minimizing the total cost of connecting all districts while maintaining full accessibility. This problem is represented mathematically as finding a Minimum Spanning Tree (MST) in a weighted undirected graph. Each vertex represents a district, and each edge represents a possible road with a construction cost as its weight.
This assignment applies Prim’s and Kruskal’s algorithms to identify the MST, compare their computational performance, and analyze their efficiency depending on graph characteristics such as density and size.

2. Purpose of the Work

The primary goal of this project is to develop a Java-based application that:

Reads graph data from a JSON input file representing city districts and potential road connections.

Applies Prim’s and Kruskal’s algorithms to construct the MST.

Records performance metrics, including execution time and operation counts.

Produces a JSON output file summarizing the MST edges, total cost, and algorithmic efficiency.
The ultimate objective is to determine the optimal algorithm for various types of transportation networks and understand trade-offs between computation time and implementation complexity.

3. Implementation and Methodology
3.1 Tools and Technologies


Library: Gson (for JSON parsing and writing)

Data Format: JSON input/output

IDE: IntelliJ IDEA

Performance Metrics: execution time (ms), operation counts

3.2 Data Structure

Each input graph includes:

A list of nodes (districts)

A list of edges (possible roads), where each edge has attributes from, to, and weight.

3.3 Algorithms Overview
<img width="955" height="534" alt="505618814-61ddcbf9-c9ad-4e88-9af9-6aa9f2eb11ea" src="https://github.com/user-attachments/assets/8d97cb23-e3d4-4aaf-9c0f-834fc44974a8" />


4. Experimental Data

<img width="295" height="464" alt="image" src="https://github.com/user-attachments/assets/c2acdcd7-6cf7-4421-aedb-db53081b499c" />


5. Results and Observations
5.1 Numerical Results
<img width="455" height="563" alt="image" src="https://github.com/user-attachments/assets/1f730fec-af9f-411a-a0e7-f2d4db405099" />


5.2 Visual Analysis
Analysis:


6. Analytical Discussion
6.1 Performance Interpretation

Time Efficiency:
Kruskal is slightly faster in Graph 1 due to fewer heap operations. However, Prim’s advantage grows in denser graphs.

Operation Complexity:
Prim’s algorithm depends heavily on the number of adjacent vertices; Kruskal depends primarily on the number of edges.

Scalability:
For very large and sparse graphs, Kruskal is preferred. For dense graphs where adjacency lists are easy to maintain, Prim’s algorithm is more efficient.

Implementation Complexity:
Kruskal’s implementation requires additional data structure (Union-Find), while Prim’s algorithm is simpler to implement when adjacency lists are available.


Conclusion:

This project successfully optimized a city’s road network using MST algorithms.
Both Prim’s and Kruskal’s algorithms produced identical minimal costs, validating correctness and theoretical equivalence.

Kruskal’s Algorithm proved efficient for sparse networks (fewer roads per district).

Prim’s Algorithm performed slightly better on dense networks (many potential connections).

The analysis confirms that choosing the right algorithm depends on the graph’s density and implementation constraints.
Overall, this work demonstrates a practical application of greedy graph algorithms for cost optimization in real-world infrastructure planning.
