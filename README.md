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

<img width="455" height="563" alt="image" src="https://github.com/user-attachments/assets/1f730fec-af9f-411a-a0e7-f2d4db405099" />




6. Analytical Discussion
6.1 Performance Interpretation

Time Efficiency:
Prim’s algorithm generally performed faster than Kruskal’s on small and dense graphs, while Kruskal’s showed similar or slightly better results on sparse ones. For all tested graphs, both algorithms produced identical MST costs. Execution times ranged from 0.07 ms to 1.8 ms, showing stable and efficient performance.

Operation Complexity:
Prim’s algorithm runs in O(E log V), while Kruskal’s operates in O(E log E) due to edge sorting and union–find. The recorded operation counts (20–380) matched theoretical expectations.

Scalability:
Both algorithms scaled well up to large graphs (30+ vertices). Prim handled dense graphs slightly better, while Kruskal was more efficient for sparse networks. Performance remained consistent with minimal time growth.

Implementation Complexity:
Prim’s algorithm is easier to implement using adjacency structures, while Kruskal’s requires extra data structures (Disjoint Set). However, both were implemented correctly and produced equivalent MST outputs.


Conclusion:

This project successfully optimized a city’s road network using MST algorithms.
Both Prim’s and Kruskal’s algorithms produced identical minimal costs, validating correctness and theoretical equivalence.

Kruskal’s Algorithm proved efficient for sparse networks (fewer roads per district).

Prim’s Algorithm performed slightly better on dense networks (many potential connections).

The analysis confirms that choosing the right algorithm depends on the graph’s density and implementation constraints.
Overall, this work demonstrates a practical application of greedy graph algorithms for cost optimization in real-world infrastructure planning.
