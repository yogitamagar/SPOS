package scheduleAtZero;

import java.util.ArrayList;

public class FCFS extends process{
	FCFS(ArrayList<proc>p,int n){
		this.p = p;
		this.n = n;
	}
	public void findWT() {
		WT = new ArrayList<Integer>();
		WT.add(0,0);
		for(int i = 1; i < n;i++) {
			int ele = p.get(i-1).exec + WT.get(i-1);
			WT.add(i, ele);
		}
	}
	
	public void findAvgTime() {
		findWT();
		findTAT();
		double wt ,tat ;
		wt = tat = 0;
		for(int i = 0 ;i < n ; i++) {
			wt+=WT.get(i);
			tat+=TAT.get(i);
		}
		awt = wt/n;
		atat = tat/n;
		print();
	}
	
}