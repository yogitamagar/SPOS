package scheduleAtZero;

import java.util.ArrayList;

public abstract class process{
	int n;
	double awt,atat;
	ArrayList<Integer> WT,TAT;
	ArrayList<proc>p;
	public abstract void findWT();
	public void findTAT() {
		TAT = new ArrayList<Integer>();
		for(int i = 0 ; i < n ; i++) {
			TAT.add(i,WT.get(i)+p.get(i).exec);
		}
	};
	public abstract void findAvgTime() ;
	public void print() {
		String leftalign ="%-6s|%-8s|%-14s|%-13s|%-16s| %n";
		System.out.println("      +--------+--------------+-------------+----------------+");
		System.out.format(leftalign,"","Process","Execution Time","Waiting Time","TurnAround Time");
		System.out.println("      +--------+--------------+-------------+----------------+");
		for(int i=0;i<n;i++) {
			System.out.format(leftalign,"", p.get(i).pid,p.get(i).exec,WT.get(i),TAT.get(i));
		}
		System.out.println("      +--------+--------------+-------------+----------------+");
		System.out.println("\n\n\t\t\t-->Average Waiting Time :" + awt);
		System.out.println("\n\t\t\t-->Average TurnAround Time :" + atat);
	}
}