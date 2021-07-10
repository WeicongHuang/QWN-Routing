package RWfile;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteFile {
	public static void main(String[] args) throws IOException, RowsExceededException, WriteException {
		
		String root="D:\\ECLIPSE\\workspace\\adhoc\\example\\";
		String []childroot={"nodeSet-100.xls","nodeSet-150.xls","nodeSet-200.xls","nodeSet-250.xls","nodeSet-300.xls",};
		int  []nodeNum= {100,150,200,250,300};

		for(int i=0;i<nodeNum.length;i++) {
			
		WritableWorkbook writeBook = Workbook
				.createWorkbook(new File(root+childroot[i]));

		WritableSheet firstSheet = writeBook.createSheet("node information", 1);// 第一个参数为工作簿的名称，第二个参数为页数

		int row = nodeNum[i];
		firstSheet.addCell(new Label(0, 0, "node_id"));
		firstSheet.addCell(new Label(1, 0, "x_pos"));
		firstSheet.addCell(new Label(2, 0, "y_pos"));
		firstSheet.addCell(new Label(3, 0, "energy"));
		for (int j = 1; j <= row; j++) {
			firstSheet.addCell(new Label(0, j, String.valueOf(j)));
		}
		int x_PosVaule = 0;
		int y_PosVaule=0;
		int energy=0;	
		for (int j = 1; j <= row; j++) {
			x_PosVaule = (int) (1 + Math.random() * 1000);
			y_PosVaule = (int) (1 + Math.random() *2000);
			energy = (int) (1 + Math.random() * 40);
			firstSheet.addCell(new Label(1, j, String.valueOf(x_PosVaule)));
			firstSheet.addCell(new Label(2, j, String.valueOf(y_PosVaule)));
			firstSheet.addCell(new Label(3, j, String.valueOf(energy)));
			}

		writeBook.write();
		writeBook.close();
		System.out.println();
	}
	}
}