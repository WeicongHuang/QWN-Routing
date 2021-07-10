package algorithm;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Stack;
import topo.node;

public class repairPath {
	static HashMap<Integer,Stack<Integer>>Fail_s=new HashMap<Integer,Stack<Integer>>();
	static HashMap<Integer, Stack<node>> all_result = new HashMap<Integer, Stack<node>>() ; 
	static HashMap<Integer, Stack<Stack<node>>> success_repair_path ;
	static Stack<Integer> fail_repair_path ;
	static HashMap<Integer,Boolean> repair_path_status;
	static Stack<node> nodeP; 
	static Stack<Stack<node>>requst_path_node_set; 
	static Stack<Boolean> repair_success;
	public static void constructRepair(int max_energy,int max_width,double link_creat_success) {
		all_result = MajorPath.r;
		Fail_s=LinkDistribute.fail_link_id;
		success_repair_path = new HashMap<Integer, Stack<Stack<node>>>();
		fail_repair_path = new Stack<Integer>();
		repair_path_status=new HashMap<Integer,Boolean> ();
		for(Entry<Integer, Stack<Integer>> entry : Fail_s.entrySet()) {
			int request=entry.getKey();
		    repair_success=new Stack<Boolean>();
			requst_path_node_set=new Stack<Stack<node>>();
			for(Integer num_link:entry.getValue()) {
				node node1=all_result.get(request).get(num_link);
				node node2=all_result.get(request).get(num_link+1);
				nodeP = new Stack<node>();
				if (FunctionFindPath.constructPath(nodeP,node1, node2, request,max_energy,max_width)) {
					FunctionFindPath.pathBack(nodeP,entry.getKey());
					repair_success.add(true);
					requst_path_node_set.add(nodeP);
				}else {
					repair_success.add(false);
				}
			}
			if(repair_success.contains(false)) {
				repair_path_status.put(entry.getKey(), false);
				fail_repair_path.add(entry.getKey());
			}else {
				repair_path_status.put(entry.getKey(), true);
				success_repair_path.put(entry.getKey(), requst_path_node_set);
			}
		}
		link_create(link_creat_success);
//		output();
		delete_fail_path();
		establishFinalPath();
//		outputSuccessPath();
}
	
	public static void link_create(double link_creat_success) {
		Stack<Integer> temporary=new Stack<Integer>();
		for(Entry<Integer, Stack<Stack<node>>> entry : success_repair_path.entrySet()) {
			boolean flag=true;
			for(Stack<node> s:entry.getValue()) {				
				for (int j = 0; j < s.size()-1; j++) {
					node n = (node) s.get(j);
					int width=n.neighbour.get(s.get(j + 1)).getWidth();
					boolean link_status=false;
					int success_width =0;					
					for(int k=0; k<width;k++) {
						Random random = new Random();
						int g = random.nextInt(10);
						if(g>=0&&g<10*link_creat_success) {
							link_status=true;
							success_width++;
						}
					}				
					if(link_status==false) {
						temporary.add(entry.getKey());
						 fail_repair_path.add(entry.getKey());
						 flag=false;
						 break;
					}
					n.neighbour.get(s.get(j + 1)).setRealWidth(success_width);
					s.get(j + 1).neighbour.get(s.get(j)).setRealWidth(success_width);
				}
				if(flag==false) {
				break;
				}
			}		
	}
		for(Integer i:temporary) {
			success_repair_path.remove(i);
		}		
}
	
	public static void delete_fail_path() {
		for( Integer r:fail_repair_path) {
			all_result.remove(r);
		}
	}
	
	public static void establishFinalPath() {
		for(Entry<Integer, Stack<Stack<node>>> entry : success_repair_path.entrySet()) {			
			for(Stack<node>k:entry.getValue()) {
				int index =all_result.get(entry.getKey()).indexOf(k.get(0));
					for(int i=1;i<k.size()-1;i++) {
					all_result.get(entry.getKey()).add(index+i, k.get(i));
					}
				}		
		}		
	}
	

		private static void output() {
			// TODO Auto-generated method stub
			for (Integer a:fail_repair_path) {
				System.out.println("fail to establish link "+a);
			}
			for(Entry<Integer, Stack<Stack<node>>> entry : success_repair_path.entrySet()) {
				System.out.println("requests id after repairement"+entry.getKey());
				for( Stack<node> i:entry.getValue()) {
					for(node k:i) {
					System.out.print(k.getNodeId()+"-->");
					}
					System.out.println();
				}
				System.out.println();
			}
		}
	public static void outputSuccessPath() {
		System.out.println("start to output all paths after repairement");
		for(Entry<Integer, Stack<node>> entry : all_result.entrySet()) {
			System.out.println("request id "+entry.getKey());
			for(node n:entry.getValue()) {
				System.out.print(n.getNodeId()+"----");
			}
			System.out.println();	
		}	
	}
}

