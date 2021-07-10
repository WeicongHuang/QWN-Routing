package algorithm;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.Random;
import java.util.Set;
import topo.node;

public class LinkDistribute {
	static HashMap<Integer, Stack<node>> all_result = new HashMap<Integer, Stack<node>>(); // all_result store all request paths
	static HashMap<Integer,Stack<Integer>> fail_link_id;	
	static HashMap <Integer,Stack<node>> direct_success_path=new HashMap<Integer, Stack<node>>();
	public static void LinkDis(double link_creat_success) {
		all_result = MajorPath.r;
		fail_link_id=new HashMap<Integer,Stack<Integer>> ();
		Set<Entry<Integer, Stack<node>>> entrySet = all_result.entrySet();
		
		for (Entry<Integer, Stack<node>> entry : entrySet) {
			Stack<node> stack_node = entry.getValue();
			Stack<Integer> stack_states= new Stack<Integer>();
			
			for (int j = 0; j < stack_node.size()-1; j++) {
				node n = (node) stack_node.get(j);
				double link_success_rate = link_creat_success;
				int width=n.neighbour.get(stack_node.get(j + 1)).getWidth();
				boolean link_status=false;
				int success_width =0;
				int min_linkwidth=999;
				for(int k=0; k<width;k++) {
					Random random = new Random();
					int g = random.nextInt(10);
					if(g>=0&&g<10*link_success_rate) {
						link_status=true;
						success_width++;
					}
				}				
				if(link_status==false) {
					stack_states.add(j);
				}
				if(success_width<min_linkwidth) {
					min_linkwidth=success_width;
				}
				n.neighbour.get(stack_node.get(j + 1)).setRealWidth(min_linkwidth);
				stack_node.get(j + 1).neighbour.get(n).setRealWidth(min_linkwidth);
			}
			
			if(!stack_states.isEmpty()) {
				fail_link_id.put(entry.getKey(), stack_states);//store<requestid, falid link id>
			}			
		}		
		
//		output();
		calDirect_success();

	}
	
	@SuppressWarnings("unchecked")
	public static void calDirect_success() {
		direct_success_path=(HashMap<Integer, Stack<node>>)all_result.clone();
		for (Entry<Integer,Stack<Integer>>entry:fail_link_id.entrySet()) {
			direct_success_path.remove(entry.getKey());
		}	
	}

	public static void output() {
		for (Entry<Integer, Stack<Integer>> m : fail_link_id.entrySet()) {
			for(Integer i :m.getValue()) {
					System.out.println("request"+m.getKey()+"failed"+"link is"+i);
			}
		}
	}
}
