# QWN-Routing
### Repository Structure
- '''src''' source code root. Under this directory:

- algorithm/MajorPath.java          Construct the initial network and process concurrent requests in the network
- algorithm/FunctionFindPath.java   Find the classic contention-free path for concurrent requests in the network
- algorithm/CalDis.java             Calculate distance between two nodes
- algorithm/LinkDistribute.java     The phase of entanglement distribution
- algorithm/repairPath.java         Repaire the paths where the entangled links fail to be established
- algorithm/entangle_Swap           Perform BSM and establish end-to-end long-distance entanglement
- algorithm/output_direct_result    Output the successful paths which does not consider competition and path recovery
- algorithm/outPut_Major_path       Output the successful paths without path recovery
- algorithm/outPut_result           Output the successful paths with path recovery


- RWfileRead/ResultFile.java        Read node location, energy, id information
- RWfile/WriteFile.java             Generate a random network 

- topo/link.java                    Define quantum links in a quantum network
- topo/node.java                    Define quantum nodes in a quantum network
  
- run/Correct.java                  Comparison of success rate between the proposed protocol and the existing protocol under different parameters
- run/Thoughout.java                Comparison of throughput between the proposed protocol and the existing protocol under different parameters



### Run demo

