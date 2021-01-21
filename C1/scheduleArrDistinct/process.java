package scheduleArrDistinct;

import java.util.ArrayList;

public abstract class process{
	int n;
	double awt,atat;
	ArrayList<Integer> FT,WT,TAT;
	ArrayList<Integer> processFlow;
	ArrayList<proc>p;
	public abstract void findFT();
	public abstract void findAvgTime() ;
	public void print() {
		String leftalign ="%-6s|%-8s|%-10s|%-14s|%-14s|%-13s|%-13s|%-16s| %n";
		System.out.println("      +--------+----------+--------------+--------------+-------------+-------------+----------------+");
		System.out.format(leftalign,"","Process","Priority","Execution Time","Arrival Time","Finish Time","Waiting Time","TurnAround Time");
		System.out.println("      +--------+----------+--------------+--------------+-------------+-------------+----------------+");
		for(int i=0;i<n;i++) {
			System.out.format(leftalign,"", p.get(i).pid,p.get(i).priority,p.get(i).exec,p.get(i).arr,FT.get(i),WT.get(i),TAT.get(i));
		}
		System.out.println("      +--------+----------+--------------+--------------+-------------+-------------+----------------+");
		System.out.println("\n\n\t\t\t-->Average Waiting Time :" + awt);
		System.out.println("\n\t\t\t-->Average TurnAround Time :" + atat);
		
		
//		leftalign = "%-6s|";
//		String form = "      +";
//		for(int i =0 ;i< n ;i++) {
//			form+= "----+";
//			leftalign += "%-4s|";
//		}
//		leftalign+="%n";
//		System.out.println(form);
		System.out.println("\n\n\t\tGantt Chart : \n");
		
		
		System.out.print("\t\t");
		int i = 0;
		for(i = 0 ; i < processFlow.size() -1; i++){
			System.out.print(p.get(processFlow.get(i)).pid + " --> ");
		}
		System.out.print(p.get(processFlow.get(i)).pid+"\n");
		
		System.out.print("\t\t"+p.get(processFlow.get(0)).arr+"    ");
		for(i = 0 ; i < processFlow.size() -1; i++){
			System.out.print(FT.get(processFlow.get(i)) + "    ");
		}
		System.out.print(FT.get(processFlow.get(i))+"\n\n");
	}
}