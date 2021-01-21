package scheduleArrDistinct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Priority extends process{

	public Priority(ArrayList<proc>p,int n){
		this.p = p;
		this.n = n;
	}
	
	@Override
	public void findFT() {
		
		FT = new ArrayList<Integer>(Arrays.asList(new Integer[n]));
		Collections.fill(FT, 0);
		WT = new ArrayList<Integer>(Arrays.asList(new Integer[n]));
		Collections.fill(FT, 0);
		TAT = new ArrayList<Integer>(Arrays.asList(new Integer[n]));
		Collections.fill(FT, 0);
		processFlow = new ArrayList<Integer>();
		
		ArrayList<Integer>prior = new ArrayList<Integer>();
		for(int i = 0; i< n ; i++) {
			prior.add(p.get(i).priority);
		}
		int currTime = p.get(0).arr;
		
		int minIndex = -1;
		boolean check = true;
		while(check) {
			int min = Integer.MAX_VALUE;
			check = false;
			for(int i = 0 ; i < n ; i++) {
				if(prior.get(i) < min  && p.get(i).arr <= currTime &&  prior.get(i) >0) {
					min = prior.get(i);
					minIndex = i;
					check = true;
				}
			}
			if(check) {
				currTime = Math.max(currTime, p.get(minIndex).arr) + p.get(minIndex).exec;
				prior.set(minIndex,0);
				processFlow.add(minIndex);
				FT.set(minIndex,currTime);
				TAT.set(minIndex,currTime - p.get(minIndex).arr);
				WT.set(minIndex,TAT.get(minIndex) - p.get(minIndex).exec);
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
