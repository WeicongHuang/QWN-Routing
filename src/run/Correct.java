package run;
import java.io.File;
import java.io.IOException;

import algorithm.LinkDistribute;
import algorithm.MajorPath;
import algorithm.entangle_Swap;
import algorithm.repairPath;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Correct {
	static double loop_time=1000;
	static int max_energy = 40;
	static double [] link_creat_success= {0.5,0.6,0.7,0.8,0.9};// default link_creat_success[3]
	static int [] request_num= { 2,4,6,8,10};//default request_num[3]
	static int [] max_width= {4,5,6,7,8};//default max_width[2]
	static int [] node_num= {100,150,200,250,300};
	static double[] entangle_create_success= { 0.6,0.7,0.8,0.9,1};//default entangle_create_success[3]
	static String[] pathSet= {"D:\\ECLIPSE\\workspace\\adhoc\\example\\nodeSet-100.xls","D:\\ECLIPSE\\workspace\\adhoc\\example\\nodeSet-150.xls","D:\\ECLIPSE\\workspace\\adhoc\\example\\nodeSet-200.xls","D:\\ECLIPSE\\workspace\\adhoc\\example\\nodeSet-250.xls","D:\\ECLIPSE\\workspace\\adhoc\\example\\nodeSet-300.xls"};
	
	static int row=0;
	public static void main(String[] args) throws RowsExceededException, BiffException, WriteException, IOException {
		// TODO Auto-generated method stub
		WritableWorkbook writeBook = Workbook.createWorkbook(new File("D:\\ECLIPSE\\workspace\\adhoc\\example\\correctrate.xls"));
		WritableSheet firstSheet = writeBook.createSheet("result", 1);
		
		link_creat_success(0,firstSheet);
		link_creat_success(1,firstSheet);
		link_creat_success(2,firstSheet);
		link_creat_success(3,firstSheet);
		link_creat_success(4,firstSheet);
		
		max_width(0,firstSheet);
		max_width(1,firstSheet);
		max_width(2,firstSheet);
		max_width(3,firstSheet);
		max_width(4,firstSheet);
		
		entangle_create_success(0,firstSheet);
		entangle_create_success(1,firstSheet);
		entangle_create_success(2,firstSheet);
		entangle_create_success(3,firstSheet);
		entangle_create_success(4,firstSheet);
		
		request_num(0,firstSheet); //100
		request_num(1,firstSheet); //150
		request_num(2,firstSheet); //200
		request_num(3,firstSheet); //250
		request_num(4,firstSheet); //300
		
		writeBook.write();
		writeBook.close();
		System.out.println();
	}
		

	public static void link_creat_success(int k,WritableSheet firstSheet) throws RowsExceededException, BiffException, WriteException, IOException {
		/*next --node_num	 */			
		for(int j=0;j<link_creat_success.length;j++) {
			firstSheet.addCell(new Label(0, row, String.valueOf("link_creat_success: "+link_creat_success[j]+ "request num"+request_num[k])));
			firstSheet.addCell(new Label(1, row, String.valueOf(link_creat_success[j])));
			firstSheet.addCell(new Label(2, row, String.valueOf(request_num[k])));
			
			
			int size_request=0;
			int size_direct_request=0;
			for(int i =0;i<loop_time;i++) {
				MajorPath.findPath(max_energy,max_width[2],link_creat_success[j],request_num[k],pathSet[2]);

				LinkDistribute.LinkDis(link_creat_success[j]);
				repairPath.constructRepair(max_energy,max_width[2],link_creat_success[j]);
				entangle_Swap.try_entangle(entangle_create_success[3]);
			    entangle_Swap.try_directentangle(entangle_create_success[3]);
				size_request+=entangle_Swap.num_success_request.size();
				size_direct_request+=  entangle_Swap.num_success_direct_request.size();
				}

			double a=(size_request/loop_time)/request_num[k];
			double b=(size_direct_request/loop_time)/request_num[k];	
			firstSheet.addCell(new Label(3, row, String.valueOf(a)));
			firstSheet.addCell(new Label(4, row, String.valueOf(b)));
			row++;
			System.out.println("link_creat_succes "+link_creat_success[j]+", request_num"+request_num[k]+"rate: "+a+",  "+b);
		}		
		System.out.println();
	}
	
	public static void request_num(int k,WritableSheet firstSheet) throws RowsExceededException, BiffException, WriteException, IOException {/*next 根据reference进行更改进行实验--node_num	 */		
	
		for(int j=0;j<request_num.length;j++) {
			int size_request=0;
			int size_direct_request=0;
			firstSheet.addCell(new Label(0, row, String.valueOf("request_num: "+request_num[j]+"node num"+node_num[k])));
			firstSheet.addCell(new Label(1, row, String.valueOf(request_num[j])));
			firstSheet.addCell(new Label(2, row, String.valueOf(node_num[k])));
			for(int i =0;i<loop_time;i++) {
				MajorPath.findPath(max_energy,max_width[2],link_creat_success[3],request_num[j],pathSet[k]);
				LinkDistribute.LinkDis(link_creat_success[3]);
				repairPath.constructRepair(max_energy,max_width[2],link_creat_success[3]);
				entangle_Swap.try_entangle(entangle_create_success[3]);
			    entangle_Swap.try_directentangle(entangle_create_success[3]);
				size_request+=entangle_Swap.num_success_request.size();
				size_direct_request+=  entangle_Swap.num_success_direct_request.size();
				}
			double a=(size_request/loop_time)/request_num[j];
			double b=(size_direct_request/loop_time)/request_num[j];	
			firstSheet.addCell(new Label(3, row, String.valueOf(a)));
			firstSheet.addCell(new Label(4, row, String.valueOf(b)));
			row++;
			System.out.println("request size "+request_num[j]+"node num"+node_num[k]+"rate: "+a+",  "+b);
		}	
		System.out.println();
	}
	
	public static void max_width(int k,WritableSheet firstSheet) throws RowsExceededException, BiffException, WriteException, IOException {/*next 根据reference进行更改进行实验--node_num	 */		
			
			for(int j=0;j<max_width.length;j++) {
				int size_request=0;
				int size_direct_request=0;
				firstSheet.addCell(new Label(0, row, String.valueOf("max_width: "+max_width[j]+"request_num"+request_num[k])));	
				firstSheet.addCell(new Label(1, row, String.valueOf(max_width[j])));	
				firstSheet.addCell(new Label(2, row, String.valueOf(request_num[k])));	
				for(int i =0;i<loop_time;i++) {
					MajorPath.findPath(max_energy,max_width[j],link_creat_success[3],request_num[k],pathSet[2]);
					LinkDistribute.LinkDis(link_creat_success[3]);
					repairPath.constructRepair(max_energy,max_width[j],link_creat_success[3]);
					entangle_Swap.try_entangle(entangle_create_success[3]);//设置
				    entangle_Swap.try_directentangle(entangle_create_success[3]);
					size_request+=entangle_Swap.num_success_request.size();
					size_direct_request+=  entangle_Swap.num_success_direct_request.size();
					}
			
				double a=(size_request/loop_time)/request_num[k];
				double b=(size_direct_request/loop_time)/request_num[k];	
				firstSheet.addCell(new Label(3, row, String.valueOf(a)));
				firstSheet.addCell(new Label(4, row, String.valueOf(b)));
				row++;
				System.out.println("max_width size"+max_width[j]+"request num"+request_num[k]+"rate: "+a+", "+b);
			}
			System.out.println();
}

	
	public static void entangle_create_success(int k,WritableSheet firstSheet) throws RowsExceededException, WriteException, IOException, BiffException {
	
		for(int j=0;j<entangle_create_success.length;j++) {
			int size_request=0;
			int size_direct_request=0;
			firstSheet.addCell(new Label(0, row, String.valueOf("entangle_create_success: "+entangle_create_success[j]+"request_num"+request_num[k])));
			firstSheet.addCell(new Label(1, row, String.valueOf(entangle_create_success[j])));
			firstSheet.addCell(new Label(2, row, String.valueOf(request_num[k])));
			
			
			for(int i =0;i<loop_time;i++) {
				MajorPath.findPath(max_energy,max_width[2],link_creat_success[3],request_num[k],pathSet[2]);
				LinkDistribute.LinkDis(link_creat_success[3]);
				repairPath.constructRepair(max_energy,max_width[2],link_creat_success[3]);
				entangle_Swap.try_entangle(entangle_create_success[j]);
				entangle_Swap.try_directentangle(entangle_create_success[j]);
				size_request+=entangle_Swap.num_success_request.size();
				size_direct_request+=  entangle_Swap.num_success_direct_request.size();
				}
			double a=(size_request/loop_time)/request_num[k];
			double b=(size_direct_request/loop_time)/request_num[k];	
			firstSheet.addCell(new Label(3, row, String.valueOf(a)));
			firstSheet.addCell(new Label(4, row, String.valueOf(b)));
			row++;
			System.out.println("entangle_create_success size"+entangle_create_success[j]+"request_num "+request_num[k]+"rate: "+a+", "+b);
			
			}		
		
		System.out.println();
		}	

}