package run;
import java.io.File;
import java.io.IOException;

import algorithm.LinkDistribute;
import algorithm.MajorPath;
import algorithm.entangle_Swap;
import algorithm.outPut_Major_path;
import algorithm.outPut_result;
import algorithm.output_direct_result;
import algorithm.repairPath;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Thoughout {
	static double loop_time=1;
	static int max_energy = 60;// energy
	static double [] link_creat_success= {0.5,0.6,0.7,0.8,0.9};//link_creat_success[3]
	static int [] request_num= { 2,4,6,8,10};//request_num[3]
	static int [] max_width= {4,5,6,7,8};//max_width[2]
	static int [] node_num= {100,150,200,250,300};// node_num[2]
	static double[] entangle_create_success= { 0.6,0.7,0.8,0.9,1};//entangle_create_success[3]
	//pathSet[2]
	static String[] pathSet= {"D:\\ECLIPSE\\workspace\\adhoc\\example\\nodeSet-100.xls","D:\\ECLIPSE\\workspace\\adhoc\\example\\nodeSet-150.xls","D:\\ECLIPSE\\workspace\\adhoc\\example\\nodeSet-200.xls","D:\\ECLIPSE\\workspace\\adhoc\\example\\nodeSet-250.xls","D:\\ECLIPSE\\workspace\\adhoc\\example\\nodeSet-300.xls"};
	static int row=0;
	public static void main(String[] args) throws RowsExceededException, BiffException, WriteException, IOException {
		// TODO Auto-generated method stub
		WritableWorkbook writeBook = Workbook.createWorkbook(new File("D:\\ECLIPSE\\workspace\\adhoc\\example\\test.xls"));
		WritableSheet firstSheet = writeBook.createSheet("result", 1);
		
		firstSheet.addCell(new Label(0, row, String.valueOf("condition")));
		firstSheet.addCell(new Label(1, row, String.valueOf("thoughtout -repaire ")));
		firstSheet.addCell(new Label(2, row, String.valueOf("thoughtout - without repaire")));
		row++;
		reference(firstSheet);
		link_creat_success(firstSheet);
		request_num(0,firstSheet); //100
		max_width(firstSheet);
		node_num(firstSheet);
		entangle_create_success(firstSheet);
		
		request_num(1,firstSheet); //150
		request_num(2,firstSheet); //200
		request_num(3,firstSheet); //250
		request_num(4,firstSheet); //300
		
		writeBook.write();
		writeBook.close();
		System.out.println();
	}
	
	public static void reference(WritableSheet firstSheet) throws RowsExceededException, BiffException, WriteException, IOException {
		/*next-node_num	 */		
		//reference link0.6 request10 width7 node160 entangle0.9 simulte1000times 
		double run_eps_sum=0;	
		double run_direct_eps_sum=0;
		firstSheet.addCell(new Label(0, row, String.valueOf("reference")));
		for(int i =0;i<loop_time;i++) {
			MajorPath.findPath(max_energy,max_width[2],link_creat_success[3],request_num[3],pathSet[2]);
			outPut_Major_path.output();//output the initial path 
		    LinkDistribute.LinkDis(link_creat_success[3]);
		   
			repairPath.constructRepair(max_energy,max_width[2],link_creat_success[3]);
			run_eps_sum+=entangle_Swap.try_entangle(entangle_create_success[3]);
			run_direct_eps_sum+=entangle_Swap.try_directentangle(entangle_create_success[3]);
		    
		    outPut_result.output();		
		    output_direct_result.output();
		}
		
		firstSheet.addCell(new Label(1, row, String.valueOf(run_eps_sum/loop_time)));
		firstSheet.addCell(new Label(2, row, String.valueOf(run_direct_eps_sum/loop_time)));

		row++;
		
		System.out.println("1. reference: "+(run_eps_sum/loop_time)+ "----direct without repair "+(run_direct_eps_sum/loop_time));
	}
	
	
	public static void link_creat_success(WritableSheet firstSheet) throws RowsExceededException, BiffException, WriteException, IOException {
		/*next node_num	 */		
		/* modify parameter link_creat_success based on reference*/		
		for(int j=0;j<link_creat_success.length;j++) {
			firstSheet.addCell(new Label(0, row, String.valueOf("link_creat_success: "+link_creat_success[j])));
			double link_run_eps_sum=0;
			double link_run_direct_eps_sum=0;
			for(int i =0;i<loop_time;i++) {
				MajorPath.findPath(max_energy,max_width[2],link_creat_success[j],request_num[3],pathSet[2]);
				LinkDistribute.LinkDis(link_creat_success[j]);
				repairPath.constructRepair(max_energy,max_width[2],link_creat_success[j]);
				link_run_eps_sum+=entangle_Swap.try_entangle(entangle_create_success[3]);
				link_run_direct_eps_sum+=entangle_Swap.try_directentangle(entangle_create_success[3]);
				}

			firstSheet.addCell(new Label(1, row, String.valueOf(link_run_eps_sum/loop_time)));
			firstSheet.addCell(new Label(2, row, String.valueOf(link_run_direct_eps_sum/loop_time)));

			row++;
			
			System.out.println(j+". link_creat_success "+link_creat_success[j]+":  "+link_run_eps_sum/loop_time+"---direct without repair "+link_run_direct_eps_sum/loop_time);
		}		
	}
	
	public static void request_num(int k,WritableSheet firstSheet) throws RowsExceededException, BiffException, WriteException, IOException {/*next 根据reference进行更改进行实验--node_num	 */		
		/* modify parameter request_num based on reference */			
		for(int j=0;j<request_num.length;j++) {
			double request_run_eps_sum=0;
			double request_run_direct_eps_sum=0;
			firstSheet.addCell(new Label(0, row, String.valueOf("request_num: "+request_num[j])));
			for(int i =0;i<loop_time;i++) {
				MajorPath.findPath(max_energy,max_width[2],link_creat_success[3],request_num[j],pathSet[k]);
				LinkDistribute.LinkDis(link_creat_success[3]);
				repairPath.constructRepair(max_energy,max_width[2],link_creat_success[3]);
				request_run_eps_sum+=entangle_Swap.try_entangle(entangle_create_success[3]);
				request_run_direct_eps_sum+=entangle_Swap.try_directentangle(entangle_create_success[3]);
				}

			firstSheet.addCell(new Label(1, row, String.valueOf(request_run_eps_sum/loop_time)));
			firstSheet.addCell(new Label(2, row, String.valueOf(request_run_direct_eps_sum/loop_time)));
			row++;			
			System.out.println(j+". "+request_num[j]+"-numnode"+node_num[k]+":"+request_run_eps_sum/loop_time+"---direct without repair "+request_run_direct_eps_sum/loop_time);
		}	
	}
	
	public static void max_width(WritableSheet firstSheet) throws RowsExceededException, BiffException, WriteException, IOException {/*next 根据reference进行更改进行实验--node_num	 */		
		/* modify parameter max_width based on reference */							
			for(int j=0;j<max_width.length;j++) {
				double width_run_eps_sum=0;
				double width_run_direct_eps_sum=0;
				firstSheet.addCell(new Label(0, row, String.valueOf("max_width: "+max_width[j])));			
				for(int i =0;i<loop_time;i++) {
					MajorPath.findPath(max_energy,max_width[j],link_creat_success[3],request_num[3],pathSet[2]);
					LinkDistribute.LinkDis(link_creat_success[3]);
					repairPath.constructRepair(max_energy,max_width[j],link_creat_success[3]);
					width_run_eps_sum+=entangle_Swap.try_entangle(entangle_create_success[3]);
					width_run_direct_eps_sum+=entangle_Swap.try_directentangle(entangle_create_success[3]);
					}

				firstSheet.addCell(new Label(1, row, String.valueOf(width_run_eps_sum/loop_time)));
				firstSheet.addCell(new Label(2, row, String.valueOf(width_run_direct_eps_sum/loop_time)));

				row++;
				System.out.println(j+". width "+max_width[j]+":  "+width_run_eps_sum/loop_time+" direct"+width_run_direct_eps_sum/loop_time);

			}
}
	
public static void node_num(WritableSheet firstSheet) throws RowsExceededException, BiffException, WriteException, IOException {/*next 根据reference进行更改进行实验--node_num	 */		
	/* modify parameter node_num based on reference */		
	for(int j=0;j<pathSet.length;j++) {
		firstSheet.addCell(new Label(0, row, String.valueOf("pathSet: "+node_num[j])));
		double nodenum_run_eps_sum=0;
		double nodenum_run_direct_eps_sum=0;
		for(int i =0;i<loop_time;i++) {
			MajorPath.findPath(max_energy,max_width[2],link_creat_success[3],request_num[3],pathSet[j]);
			LinkDistribute.LinkDis(link_creat_success[3]);
			repairPath.constructRepair(max_energy,max_width[2],link_creat_success[3]);
			nodenum_run_eps_sum+=entangle_Swap.try_entangle(entangle_create_success[3]);
			nodenum_run_direct_eps_sum+=entangle_Swap.try_directentangle(entangle_create_success[3]);
			}
		firstSheet.addCell(new Label(1, row, String.valueOf(nodenum_run_eps_sum/loop_time)));
		firstSheet.addCell(new Label(2, row, String.valueOf(nodenum_run_direct_eps_sum/loop_time)));	

		row++;
		System.out.println(j+". nodenum_creat_success "+node_num[j]+":  "+nodenum_run_eps_sum/loop_time+"  direct"+nodenum_run_direct_eps_sum/loop_time);
	}
}
	
	public static void entangle_create_success(WritableSheet firstSheet) throws RowsExceededException, WriteException, IOException, BiffException {
		/* modify parameter entangle_create_success based on reference */				
		for(int j=0;j<entangle_create_success.length;j++) {
			double entangle_run_eps_sum=0;
			double entangle_run_direct_eps_sum=0;
			firstSheet.addCell(new Label(0, row, String.valueOf("entangle_create_success: "+entangle_create_success[j])));
			for(int i =0;i<loop_time;i++) {
				MajorPath.findPath(max_energy,max_width[2],link_creat_success[3],request_num[3],pathSet[2]);
				LinkDistribute.LinkDis(link_creat_success[3]);
				repairPath.constructRepair(max_energy,max_width[2],link_creat_success[3]);
				entangle_run_eps_sum+=entangle_Swap.try_entangle(entangle_create_success[j]);//设置
				entangle_run_direct_eps_sum+=entangle_Swap.try_directentangle(entangle_create_success[j]);
				}
			double entangle_run_eps_average=entangle_run_eps_sum/loop_time;
			double entangle_direct_eps_average=entangle_run_direct_eps_sum/loop_time;
			System.out.println(j+". entangle_create_success "+entangle_create_success[j]+":  "+entangle_run_eps_average+"  direct"+ entangle_direct_eps_average);
			firstSheet.addCell(new Label(1, row, String.valueOf(entangle_run_eps_average)));
			firstSheet.addCell(new Label(2, row, String.valueOf(entangle_direct_eps_average)));
			row++;
			}		
		}	
}