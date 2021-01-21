package scheduleArrDistinct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin extends process{
	int quantum;
	
	RoundRobin(ArrayList<proc>p,int n,int quantum){
		this.p = p;
		this.n = n;
		this.quantum = quantum;
	}
	
	@Override
	public void findFT() {
		
		FT = new ArrayList<Integer>();
		WT = new ArrayList<Integer>();
		TAT = new ArrayList<Integer>();
		processFlow = new ArrayList<Integer>();
		
		Queue<Integer>ReadyQueue = new LinkedList<>(); 
		ArrayList<Integer>execTemp = new ArrayList<Integer>();
		
		for(proc pr : p) {
			execTemp.add(pr.exec);
		}
		
		for(int i = 0 ; i < n ; i++) {  //Ready Queue
			ReadyQueue.add(i);
		}
		
		int currTime = p.get(ReadyQueue.peek()).arr;
		while(!ReadyQueue.isEmpty()) {
			int ind = ReadyQueue.remove();
			int exe = execTemp.get(ind);
			if(exe > quantum) {
				currTime = Math.max(p.get(ind).arr,currTime) + quantum; //max Handling time gap between finish time of one process and arrival time of other process; 
				execTemp.set(ind,exe-quantum) ;
				ReadyQueue.add(ind);
				processFlow.add(ind);
			}
			else {
				currTime = Math.max(p.get(ind).arr,currTime) + exe;
				execTemp.set(ind,0);
				FT.add(ind,currTime);
				TAT.add(ind,currTime - p.get(ind).arr);
				WT.add(ind,TAT.get(ind)- p.get(ind).exec);
				processFlow.add(ind) ;
			}
		}
	}

	@Override
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

