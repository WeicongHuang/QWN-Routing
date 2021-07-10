package RWfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Stack;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import topo.node;

public class ReadResultFile {
	public static void readFile(String path, ArrayList<Stack<node>> result) throws BiffException, IOException {
		String sFilePath = path;
		InputStream file = new FileInputStream(sFilePath);
		Workbook rwb = Workbook.getWorkbook(file);

		for (int k = 0; k < rwb.getNumberOfSheets(); k++) {
			System.out.println("no. of work sheet" + rwb.getNumberOfSheets() + rwb.getSheetNames()[k]);
			Sheet oFirstSheet = rwb.getSheet(k);
			int rows = oFirstSheet.getRows();
			int columns = oFirstSheet.getColumns();
			System.out.println("no. of row" + rows);
			System.out.println("no. of col" + columns);
			Stack<node> n = new Stack<node>();
			for (int i = 1; i < rows; i++) {
				int node_id = Integer.parseInt(oFirstSheet.getCell(0, i).getContents());
				int x_pos = Integer.parseInt(oFirstSheet.getCell(1, i).getContents());
				int y_pos = Integer.parseInt(oFirstSheet.getCell(2, i).getContents());
				int energy = Integer.parseInt(oFirstSheet.getCell(3, i).getContents());
				n.add(new node(node_id, x_pos, y_pos, energy));
			}
			result.add(n);
		}

	}

}
