package scheduleAtZero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RoundRobin extends process{
	int quantum;
	RoundRobin(ArrayList<proc>p,int n,int quantum){
		this.p = p;
		this.n = n;
		this.quantum = quantum;
	}
	@Override
	public void findWT() {
		WT = new ArrayList<Integer>(Arrays.asList(new Integer[n]));
		Collections.fill(WT, 0);
		ArrayList<proc>p1 = new ArrayList<proc>();
		p1 = p;
		int t = 0;
		while(true) {
			boolean done = false;
			for(int i = 0 ; i< n; i++) {
				if(p1.get(i).exec > 0) {
					if(p1.get(i).exec > quantum) {
						t += quantum;
						p1.get(i).exec-=quantum;
					}
					else {
						t += p1.get(i).exec;
						WT.set(i,t - (p.get(i).exec));
						p1.get(i).exec = 0;
						done = true;
					}
				}
			}
			if(done) break;
		}
	}

	@Override
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

