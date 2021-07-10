package algorithm;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import topo.link;
import topo.node;
public class MajorPath {
	static ArrayList<node> nodeList ;
	static Stack<node> nodePath;
	static HashMap<Integer, Stack<node>> r;
	static ArrayList<Stack<node>> result;
	static HashMap<Integer,Stack<Integer>> request_restore;
	public static void findPath(int max_energy,int max_width,double link_success_rate, int requestNum,String FilePath) throws BiffException, IOException, RowsExceededException, WriteException {
		// TODO Auto-generated method stub
		request_restore=new HashMap<Integer,Stack<Integer>> ();
		nodeList = new ArrayList<>();
		int nodeNum=constructNode( FilePath);
		addNeighbour(max_width,link_success_rate);
		result = new ArrayList<Stack<node>>();
		r = new HashMap<Integer, Stack<node>>();
		for (int i = 0; i < requestNum; i++) {
			nodePath = new Stack<node>();
			node source = nodeList.get((int) (1 + Math.random() * nodeNum/2));
			node des = nodeList.get((int) (nodeNum/2 + Math.random() * nodeNum/2));
			int requestid = i;
			Stack<Integer> nodeid=new Stack<Integer>();
			nodeid.add(source.getNodeId());
			nodeid.add(des.getNodeId());
			request_restore.put(requestid, nodeid);
			
			source.getRequest().add(requestid);
			if (FunctionFindPath.constructPath(nodePath,source, des, requestid,max_energy,max_width)) {// 发出请求建立路径
				// path back
				FunctionFindPath.pathBack(nodePath,requestid);
				result.add(nodePath);
				r.put(requestid, nodePath);
			}
		}
	}

	public static int constructNode(String Path) throws BiffException, IOException {
		InputStream file = new FileInputStream(Path);
		Workbook rwb = Workbook.getWorkbook(file);
		Sheet oFirstSheet = rwb.getSheet(0);
		int rows = oFirstSheet.getRows();
		int nodeId;
		int x;
		int y;
		int residual_energy;
		for (int i = 1; i < rows; i++) {
			nodeId = Integer.parseInt(oFirstSheet.getCell(0, i).getContents());
			x = Integer.parseInt(oFirstSheet.getCell(1, i).getContents());
			y = Integer.parseInt(oFirstSheet.getCell(2, i).getContents());
			residual_energy = Integer.parseInt(oFirstSheet.getCell(3, i).getContents());
			node n = new node(nodeId, x, y, residual_energy);
			nodeList.add(n);
		}
		return (rows-1);
	}

	public static void addNeighbour(int maxwidth,double link_success_rate) {
		node current_node;
		node neighbour_node;
		int linkID = 0;
		int width;
		boolean use_state;
		
		for (int i = 0; i < nodeList.size() - 1; i++) {
			current_node = nodeList.get(i);
			for (int j = i + 1; j < nodeList.size(); j++) {
				neighbour_node = nodeList.get(j);
				double distance =CalDis.distanceCal(neighbour_node ,current_node);
				if (distance < 200) {//max distance is set to 200
					width = (int) (1 + Math.random() * maxwidth);
					use_state = false;
					link l = new link(++linkID, width, use_state, link_success_rate);
					current_node.neighbour.put(neighbour_node, l);
					neighbour_node.neighbour.put(current_node, l);
				}
			}
		}
	}
}