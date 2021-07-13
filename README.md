# QWN-Routing
### Repository Structure
`src` source code root. Under this directory:
- `algorithm/MajorPath.java`          Construct the initial network and process concurrent requests in the network
- `algorithm/FunctionFindPath.java`   Find the classic contention-free path for concurrent requests in the network
- `algorithm/CalDis.java`             Calculate distance between two nodes
- `algorithm/LinkDistribute.java`     The phase of entanglement distribution
- `algorithm/repairPath.java`         Repaire the paths where the entangled links fail to be established
- `algorithm/entangle_Swap`           Perform BSM and establish end-to-end long-distance entanglement
- `algorithm/output_direct_result`    Output the successful paths which does not consider competition and path recovery
- `algorithm/outPut_Major_path`       Output the successful paths without path recovery
- `algorithm/outPut_result`           Output the successful paths with path recovery


- `RWfileRead/ResultFile.java`        Read node location, energy, id information
- `RWfile/WriteFile.java`             Generate a random network 

- `topo/link.java`                    Define quantum links in a quantum network
- `topo/node.java`                    Define quantum nodes in a quantum network
  
- `run/Correct.java`                 Comparison of success rate between the proposed protocol and the existing protocol under different parameters
- `run/Thoughout.java`                Comparison of throughput between the proposed protocol and the existing protocol under different parameters


# Run demo

### 1. Build the environment 
Install Java(jdk vesion is 15) and eclipse (version is 2021-03 (4.19.0)) for simulation

### 2. Download source codes
git clone https://github.com/WeicongHuang/QWN-Routing.git

### 4. Run simulations
- run `RWfile/WriteFile.java`  
- run `run/Thoughout.java`  
- run `run/Correct.java`  

### 5. plot figures in our paper 
Draw a curve graph of throughput according to `examples\throughput.xls`
Draw a histogram of success rate according to `examples\correctrate.xls` 
