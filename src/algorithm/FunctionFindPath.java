package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

import topo.link;
import topo.node;

public class FunctionFindPath {
	public static boolean constructPath(Stack<node> nodePath,node source, node des, int request_id,int max_energy,int max_width) {
		node current = source;
		node next_node = null;
		int size = nodePath.size();
		if (source == null && (size == 0 || size == 1)) {
			return false;
		} else if (source == null && size > 1) {// ’Î∂‘block node
			node unused = nodePath.pop();
			node peek = nodePath.peek();
			nodePath.pop();
			unused.neighbour.get(peek).setUse_state(false);
			peek.neighbour.get(unused).setUse_state(false);
			return constructPath(nodePath,peek, des, request_id,max_energy,max_width);
		} else {
			// Determines whether neighbor contains destination 
			nodePath.push(current);
			if (current.neighbour.containsKey(des) && current.neighbour.get(des).getUse_state() == false) {
				current.neighbour.get(des).setUse_state(true);
				des.neighbour.get(current).setUse_state(true);
				des.getRequest().add(request_id);
				nodePath.push(des);
				return true;
			} else {
				// choose optimal neighbor node
				double curr_des_distance= CalDis.distanceCal(des,current);	
				next_node = compare_metric(current,current.neighbour, request_id,max_width,  des, curr_des_distance,max_energy);
				if (next_node != null) {
					current.neighbour.get(next_node).setUse_state(true);
					next_node.neighbour.get(current).setUse_state(true);
				}
				current = next_node;
				return constructPath(nodePath,current, des, request_id,max_energy,max_width);
			}
		}
	}

	public static node compare_metric(node current,HashMap<node, link> neighbour, int request_id, int max_width,node des, double curr_des_distance,int max_energy) {
		int width = 0;
		int node_energy = 0;
		double metric;
		HashMap<node, Double> next_neighbour = new HashMap<node, Double>();
		Set<Map.Entry<node, link>> entrySet = neighbour.entrySet();
		for (Map.Entry<node, link> entry : entrySet) {
			if (entry.getValue().getUse_state() == true || entry.getKey().getRequest().contains(request_id)) {
				continue;
			}
			width = entry.getValue().getWidth();
			node_energy = entry.getKey().getResidual_energy();
			metric = node_energy / max_energy + width / max_width + 1-(CalDis.distanceCal(entry.getKey(),des)/curr_des_distance);
			next_neighbour.put(entry.getKey(), metric);
		}
		// back if the size of next_neighbor is 0
		if (next_neighbour.size() > 0) {
			
			List<Map.Entry<node, Double>> list = new ArrayList<Map.Entry<node, Double>>(next_neighbour.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<node, Double>>() {
				@Override
				public int compare(Entry<node, Double> o1, Entry<node, Double> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
			});
			int index = -1;
			for (int i = 0; i < list.size(); i++) {
				if (num_neighbour(list.get(i).getKey(), request_id) > 0) {
					index = i;
					break;
				}
			}
			if (index == -1) {
				return null;
			}
			list.get(index).getKey().getRequest().add(request_id);
			return list.get(index).getKey();	
		} else {
			return null;
		}
	}
	
	public static int num_neighbour(node n, int id) {
		if (n == null) {
			return 0;
		}
		int i = 0;
		for (Map.Entry<node, link> entry :  n.neighbour.entrySet()) {
			if (!entry.getValue().getUse_state() && !entry.getKey().getRequest().contains(id)) {
				i++;
			}
		}
		return i;
	}
	
	
	public static void pathBack(Stack<node> nodePath,int requestid) {
		// TODO Auto-generated method stub
		int i = 0;
		int endindex;
		int startindex;
		while (nodePath.size() > 2 && i < nodePath.size() - 2) {
			++i;
			node top = nodePath.get(nodePath.size() - i);
			startindex = -1;
			for (int k = 0; k < (nodePath.size() - 1 - i); k++) {
				if (top.neighbour.containsKey(nodePath.get(k))&&top.neighbour.get(nodePath.get(k)).getUse_state()==false) {
					startindex = k;
					break;
				}
			}
			
			endindex = nodePath.size() - i;
			if (startindex != -1) {
				for (int j = startindex; j < endindex; j++) {
					// delete intermediate nodes and relative relationship
					node pre = nodePath.get(j);
					node back = nodePath.get(j + 1);
					pre.neighbour.get(back).setUse_state(false); //set the state of unused link to false
					back.neighbour.get(pre).setUse_state(false);
					back.getRequest().remove( Integer.valueOf(requestid));
				}
				node start = nodePath.get(startindex);
				node end = nodePath.get(endindex);
				start.neighbour.get(end).setUse_state(true);
				end.neighbour.get(start).setUse_state(true);
				nodePath.get(endindex).getRequest().add(requestid);
				for (int j = endindex - 1; j > startindex; j--) {
					 nodePath.remove(j);
				}
			}
		}
	}
	
}
