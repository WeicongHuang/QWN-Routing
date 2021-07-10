package algorithm;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.Map.Entry;
import topo.node;

public class entangle_Swap {
	static HashMap<Integer, Stack<Integer>> all_request_inforamtion = new HashMap<Integer, Stack<Integer>>();
	static HashMap<Integer, Stack<node>> link_success_path = new HashMap<Integer, Stack<node>>();// store all <id ,path > of final success requests
	static Stack<Integer> final_fail_path_idSet = new Stack<Integer>(); // store all failed path ids
	static Stack<Integer> tempory_failID;//  store path id due to failed entanglement
	public static HashMap<Integer, Integer> num_success_request;
	// next restore paths without repairment
	static HashMap<Integer, Stack<node>> entagle_direct = new HashMap<Integer, Stack<node>>();// 对link之后的直接进行纠缠产生
	static Stack<Integer> tempory_direct_failID;
	public static HashMap<Integer, Integer> num_success_direct_request;
	// direct entanglement
	public static int try_directentangle(double entangle_cre_succes) {
		tempory_direct_failID = new Stack<Integer>();
		num_success_direct_request = new HashMap<Integer, Integer>();
		entagle_direct = LinkDistribute.direct_success_path;
		for (Entry<Integer, Stack<node>> entry : entagle_direct.entrySet()) {
			Stack<Boolean> Path_directentangle_state = new Stack<Boolean>();
			int path_node_size = entry.getValue().size();// node size of each request
			int min_link_num = 999;
			if (path_node_size == 2) {
				min_link_num = entry.getValue().get(0).neighbour.get(entry.getValue().get(1)).getRealWidth();
				num_success_request.put(entry.getKey(), min_link_num);
			} else {
			for (int i = 0; i < path_node_size - 2; i++) {
				boolean entangle_status = false;
				int pre_path_width = entry.getValue().get(i).neighbour.get(entry.getValue().get(i + 1))
						.getRealWidth();
				int next_path_width = entry.getValue().get(i + 1).neighbour.get(entry.getValue().get(i + 2))
						.getRealWidth();
				int real_path_width = pre_path_width > next_path_width ? next_path_width : pre_path_width;
				int tempory_num_linknum = 0;
				for (int k = 0; k < real_path_width; k++) { // according to the width of path
					Random random = new Random();
					int g = random.nextInt(10);
					if (g >= 0 && g < 10 * entangle_cre_succes) {
						entangle_status = true;
						tempory_num_linknum++;
					}
				}
				Path_directentangle_state.add(entangle_status);
				if (tempory_num_linknum < min_link_num) {
					min_link_num = tempory_num_linknum;
				}
			}
			}
			if (Path_directentangle_state.contains(false)) {
				tempory_direct_failID.add(entry.getKey());
			} else {
				num_success_direct_request.put(entry.getKey(), min_link_num);
			}
		}

		for (Integer i : tempory_direct_failID) {
			entagle_direct.remove(i);
		}
		int sum = 0;
		for (Entry<Integer, Integer> entry : num_success_direct_request.entrySet()) {
			sum += entry.getValue();
		}
		return sum;
	}

//repair--entanglement path
	public static int try_entangle(double entangle_cre_succes) {
		all_request_inforamtion = MajorPath.request_restore;
		link_success_path = repairPath.all_result;
		final_fail_path_idSet = repairPath.fail_repair_path;
		tempory_failID = new Stack<Integer>();
		num_success_request = new HashMap<Integer, Integer>();

		for (Entry<Integer, Stack<node>> entry : link_success_path.entrySet()) {
			Stack<Boolean> Path_entangle_state = new Stack<Boolean>();
			int path_node_size = entry.getValue().size();
			int min_link_num = 888;
			if (path_node_size == 2) {
				min_link_num = entry.getValue().get(0).neighbour.get(entry.getValue().get(1)).getRealWidth();
				num_success_request.put(entry.getKey(), min_link_num);
			} else {
				for (int i = 0; i < path_node_size - 2; i++) {
					boolean entangle_status = false;
					int pre_path_width = entry.getValue().get(i).neighbour.get(entry.getValue().get(i + 1))
							.getRealWidth();
					int next_path_width = entry.getValue().get(i + 1).neighbour.get(entry.getValue().get(i + 2))
							.getRealWidth();
					int real_path_width = pre_path_width > next_path_width ? next_path_width : pre_path_width;
					int tempory_num_linknum = 0;
					for (int k = 0; k < real_path_width; k++) { 
						Random random = new Random();
						int g = random.nextInt(10);
						if (g >= 0 && g < 10 * entangle_cre_succes) {
							entangle_status = true;
							tempory_num_linknum++;
						}
					}
					Path_entangle_state.add(entangle_status);
					if (tempory_num_linknum < min_link_num) {
						min_link_num = tempory_num_linknum;
					}
				}
			}
			if (Path_entangle_state.contains(false)) {
				final_fail_path_idSet.add(entry.getKey());
				tempory_failID.add(entry.getKey());
			} else {
				num_success_request.put(entry.getKey(), min_link_num);
			}
		}
		remove_fail_finalPath();


		int sum = 0;
		for (Entry<Integer, Integer> entry : num_success_request.entrySet()) {
			sum += entry.getValue();
		}
		return sum;
	}

	public static void remove_fail_finalPath() {
		for (Integer i : tempory_failID) {
			link_success_path.remove(i);
		}
	}

	public static void output() {
		System.out.println(" all failed request id is");
		for (Integer i : final_fail_path_idSet) {
			System.out.print(i + "   ");
		}
		System.out.println("the id of success requests are");
		for (Entry<Integer, Stack<node>> entry : link_success_path.entrySet()) {
			System.out.print(entry.getKey() + "   ");
		}
		System.out.println();
		System.out.println("the number of success links for each request is");
		for (Entry<Integer, Integer> entry : num_success_request.entrySet()) {
			System.out.println(entry.getKey() + " ：" + entry.getValue());
		}
	}

	public static void printPath() {

		System.out.println("all requests are as follows");
		for (Entry<Integer, Stack<Integer>> entry : all_request_inforamtion.entrySet()) {
			System.out.print("request id is" + entry.getKey() + ":   ");
			for (Integer i : entry.getValue()) {
				System.out.print((i) + "   ");
			}
			System.out.println();
		}

		for (Entry<Integer, Stack<node>> entry : link_success_path.entrySet()) {
			System.out.print("request id is " + entry.getKey() + "  ");
			System.out.print("path is ");
			for (node n : entry.getValue()) {
				System.out.print(n.getNodeId() + "  ");
			}
			System.out.println();
		}

	}

	public static int throughput() {
		int sum = 0;
		for (Entry<Integer, Integer> entry : num_success_request.entrySet()) {
			sum += entry.getValue();
		}
		return sum;
	}
}
