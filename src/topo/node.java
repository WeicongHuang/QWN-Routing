package topo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class node {
	int nodeId;
	int x; // node's x_position
	int y; // node's y_position
	int residual_energy; // node's residual energy
	List<Integer> request = new ArrayList<Integer>();//request list 
	public HashMap<node, link> neighbour = new HashMap<node, link>();//´æ´¢ÁÚ¾Ó

	public node(int nodeId, int x, int y, int residual_energy) {
		this.nodeId = nodeId;
		this.x = x;
		this.y = y;
		this.residual_energy = residual_energy;
	}

	public List<Integer> getRequest() {
		return request;
	}

	public void setRequest(List<Integer> request) {
		this.request = request;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getResidual_energy() {
		return residual_energy;
	}

	public void setResidual_energy(int residual_energy) {
		this.residual_energy = residual_energy;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

}
