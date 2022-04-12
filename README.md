# QWN-Routing
Download and unzip Simulations.zip.

# Repository Structure

1. Q-CPS
`src` source code root. Under this directory:

a). package algorithmQCPS
- `algorithmQCPS/MajorPath.java` Construct the initial network and process concurrent requests in the network
- `algorithmQCPS/FunctionFindPath.java` Find paths for concurrent requests in the network
- `algorithmQCPS/CalDis.java` Calculate distance between two nodes
- `algorithmQCPS/entangle_Swap` Perform BSM and establish end-to-end long-distance entanglement
- `algorithmQCPS/outPut_result` Output the successful paths

b). package runQCPS
- `runQCPS/Simulations.java` The simulation experiment calculates average residual energy, the number of node, throughput and correct rate in the Q-CPS protocol

c). package RWfileQCPS
- `RWfile/GenerateNetworkNode.java` Generate a random network

d). package topoQCPS
- `topo/link.java` Define quantum links in a quantum network
- `topo/node.java` Define quantum nodes in a quantum network

2. Q-CFPSR
`src` source code root. Under this directory:

a). package algorithm
- `algorithm/MajorPath.java` Construct the initial network and process concurrent requests in the network
- `algorithm/FunctionFindPath.java` Find paths for concurrent requests in the network
- `algorithm/CalDis.java` Calculate distance between two nodes
- `algorithm/LinkDistribute.java` Distributing quantum entanglement between adjacent nodes
- `algorithm/outPut_Major_path.java` Output the major path in P1
- `algorithm/repairPath.java` Find repair paths in Q-CFPSR protocols.
- `algorithm/EntanglementSwap` Perform BSM and establish end-to-end long-distance entanglement
- `algorithm/outPut_result` Output the successful paths

b). package run
- `run/Simulations.java` The simulation experiment calculates the average residual energy, the number of node, throughput and correct rate in the Q-CFPSR  protocols.

c). package RWfile
- `RWfile/GenerateNetworkNode.java` Generate a random network

d). package topo
- `topo/link.java` Define quantum links in a quantum network
- `topo/node.java` Define quantum nodes in a quantum network

2. Q-CFPSR-WR
`src` source code root. Under this directory:

a). package algorithm
- `algorithm/MajorPath.java` Construct the initial network and process concurrent requests in the network
- `algorithm/FunctionFindPath.java` Find paths for concurrent requests in the network
- `algorithm/CalDis.java` Calculate distance between two nodes
- `algorithm/LinkDistribute.java` Distributing quantum entanglement between adjacent nodes
- `algorithm/EntanglementSwap` Perform BSM and establish end-to-end long-distance entanglement
- `algorithm/outPut_direct_result` Output the successful paths

b). package run
- `run/Simulations.java` The simulation experiment calculates the average residual energy, the number of node, throughput and correct rate in the Q-CFPSR-WR  protocols.

c). package RWfile
- `RWfile/GenerateNetworkNode.java` Generate a random network

d). package topo
- `topo/link.java` Define quantum links in a quantum network
- `topo/node.java` Define quantum nodes in a quantum network

# Run demo
### 1. Build the environment
Install Java(jdk vesion is 15) and eclipse (version is 2021-03 (4.19.0)) for simulation

### 2. Download source codes
git clone [https://github.com/WeicongHuang/QWN-Routing.git](https://github.com/WeicongHuang/QWN-Routing.git)

### 3. Run simulations
Unzip Simulations.zip
  - run `QCPS\src\Simulations.java`
  - run `Q-CFPSR\src\Simulations.java`
  - run `Q-CFPSR-WR\src\Simulations.java`
  
### 4. plot figures in our paper
- Draw histograms of average residual energy, the number of node, throughput and correct rate according to `Q-CFPSR/examples/CFPSR__Performance.xls` in Q-CFPSR.

- Draw histograms of average residual energy, the number of node, throughput and correct rate according to `Q-CFPSR-WR/examples/CFPSR-WR-Performance.xls` in Q-CFPSR-WR.

- Draw histograms of average residual energy, the number of node, throughput and correct rate according to `QCPS/examples/QCPS__Performance.xls` in QCPS.
