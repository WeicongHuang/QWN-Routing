package algorithm;
import topo.node;

public class CalDis {
	public  static double distanceCal(node node1,node node2) {
		double distance = Math.sqrt(Math.pow(node1.getX() - node2.getX(), 2)
				+ Math.pow(node1.getY() - node2.getY(), 2));
		return distance;
	}
}
