package scheduleArrDistinct;

import java.util.ArrayList;

public class FCFS extends process{
	FCFS(ArrayList<proc>p,int n){
		this.p = p;
		this.n = n;
	}
	public void findFT() {
		FT = new ArrayList<Integer>();
		WT = new ArrayList<Integer>();
		TAT = new ArrayList<Integer>();
		processFlow = new ArrayList<Integer>();
		int currTime = p.get(0).arr;
		for(int i = 0; i < n;i++) {
			processFlow.add(i);
			currTime= Math.max(p.get(i).arr,currTime) + p.get(i).exec;
			FT.add(i, currTime);
			TAT.add(i,FT.get(i)-p.get(i).arr);
			WT.add(i,TAT.get(i) - p.get(i).exec);
		}
	}
	
	public void findAvgTime() {
		findFT();
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