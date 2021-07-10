package algorithm;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import topo.node;

public class output_direct_result {
	static HashMap<Integer, Stack<node>> output_path =new HashMap<Integer, Stack<node>>();// 初始生成的路径
	
	public static void output() throws RowsExceededException, WriteException, IOException{
		output_path=entangle_Swap.entagle_direct;
		if(output_path.size()==0) {
			return;
		}

	WritableWorkbook writeBook = Workbook.createWorkbook(new File("D:\\ECLIPSE\\workspace\\adhoc\\example\\Direct_Final_PATH.xls"));
	for(Map.Entry<Integer, Stack<node>> entry:output_path.entrySet())
	{

		WritableSheet firstSheet = writeBook.createSheet("request path is" + entry.getKey(), entry.getKey());
		firstSheet.addCell(new Label(0, 0, "node_id"));
		firstSheet.addCell(new Label(1, 0, "x_pos"));
		firstSheet.addCell(new Label(2, 0, "y_pos"));
		firstSheet.addCell(new Label(3, 0, "energy"));
		int j = 1;
	
		for (node n : entry.getValue()) {
			int id = n.getNodeId();
			int pos_x = n.getX();
			int pos_y = n.getY();
			int energy = n.getResidual_energy();
			firstSheet.addCell(new Label(0, j, String.valueOf(id)));
			firstSheet.addCell(new Label(1, j, String.valueOf(pos_x)));
			firstSheet.addCell(new Label(2, j, String.valueOf(pos_y)));
			firstSheet.addCell(new Label(3, j, String.valueOf(energy)));
			j++;
		}
}
writeBook.write();
writeBook.close();
}
}