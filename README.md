# QWN-Routing

# Repository Structure

1. QCPS.zip
`src` source code root. Under this directory:

package algorithmQCPS

- `algorithmQCPS/MajorPath.java` Construct the initial network and process concurrent requests in the network
- `algorithmQCPS/FunctionFindPath.java` Find paths for concurrent requests in the network
- `algorithmQCPS/CalDis.java` Calculate distance between two nodes
- `algorithmQCPS/entangle_Swap` Perform BSM and establish end-to-end long-distance entanglement
- `algorithmQCPS/outPut_result` Output the successful paths
package runQCPS
- `runQCPS/Correct.java` The simulation experiment calculates the correct rate in the Q-CPS protocol
- `runQCPS/Thoughout.java` The simulation experiment calculates the throughput in the Q-CPS protocol.

package RWfileQCPS

- `RWfile/WriteFile.java` Generate a random network

package topoQCPS

- `topo/link.java` Define quantum links in a quantum network
- `topo/node.java` Define quantum nodes in a quantum network

2. Q-CFPSR-Q-CFPSR-WR.zip
`src` source code root. Under this directory:

package algorithm

- `algorithm/MajorPath.java` Construct the initial network and process concurrent requests in the network
- `algorithm/FunctionFindPath.java` Find paths for concurrent requests in the network
- `algorithm/CalDis.java` Calculate distance between two nodes
- `algorithm/LinkDistribute.java` Distributing quantum entanglement between adjacent nodes
- `algorithm/output_direct_result.java` Output the paths without repairation
- `algorithm/outPut_Major_path.java` Output the major path in P1
- `algorithm/repairPath.java` Find repair paths in Q-CFPSR protocols.
- `algorithm/entangle_Swap` Perform BSM and establish end-to-end long-distance entanglement
- `algorithm/outPut_result` Output the successful paths
package run
- `run/Correct.java` The simulation experiment calculates the correct rate in the Q-CFPSR and Q-CFPSR-WR protocols.
- `run/Thoughout.java` The simulation experiment calculates the throughput in the Q-CFPSR and Q-CFPSR-WR protocols.
package RWfile
- `RWfile/WriteFile.java` Generate a random network

package topo

- `topo/link.java` Define quantum links in a quantum network
- `topo/node.java` Define quantum nodes in a quantum network

# Run demo

### 1. Build the environment

Install Java(jdk vesion is 15) and eclipse (version is 2021-03 (4.19.0)) for simulation

### 2. Download source codes

git clone [https://github.com/WeicongHuang/QWN-Routing.git](https://github.com/WeicongHuang/QWN-Routing.git)

### 3. Run simulations

1. Unzip QCPS.zip
  - run `runQCPS/Thoughout.java`
  - run `runQCPS/Correct.java`
  
2. Unzip Q-CFPSR-Q-CFPSR-WR.zip
  - run `run/Thoughout.java`
  - run `run/Correct.java`

### 4. plot figures in our paper
Draw histograms of throughput according to `examples\\throughput.xls` in  Q-CFPSR-Q-CFPSR-WR and QCPS.
Draw histograms of success rate according to `examples\\correctrate.xls` in  Q-CFPSR-Q-CFPSR-WR and QCPS.
