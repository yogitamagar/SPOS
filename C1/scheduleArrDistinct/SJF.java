package scheduleArrDistinct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SJF extends process{
	
	public SJF(ArrayList<proc>p,int n){
		this.p = p;
		this.n = n;
	}
	
	@Override
	public void findFT() {
		FT = new ArrayList<Integer>(Arrays.asList(new Integer[n]));
		Collections.fill(FT, 0);
		System.out.println("FT size : "+FT.size());
		WT = new ArrayList<Integer>(Arrays.asList(new Integer[n]));
		Collections.fill(WT, 0);
		TAT = new ArrayList<Integer>(Arrays.asList(new Integer[n]));
		Collections.fill(TAT, 0);
		processFlow = new ArrayList<Integer>();
		//remaining TIme maintaining
		ArrayList<Integer>exe = new ArrayList<Integer>();
	   
	    for (int i = 0; i < n; i++) 
	        exe.add(p.get(i).exec); 
	    
	    int currTime = p.get(0).arr;
	    boolean check = true;
	    int minIndex = -1;
	    while(check) {
	    	int min = Integer.MAX_VALUE;
	    	check = false;
	    	for(int i = 0 ; i < n ; i++) {
		    	if(exe.get(i) < min && p.get(i).arr <= currTime && exe.get(i) > 0) {
		    		min = exe.get(i);
		    		minIndex = i;
		    		check = true;
		    	}
	    	}
	    	if(check) {
	    		currTime = Math.max(currTime,p.get(minIndex).arr) + p.get(minIndex).exec;
	    		exe.set(minIndex,0);
	    		processFlow.add(minIndex);
//	    		System.out.println("minindex = "+minIndex);
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
		System.out.println("      SJF : ");
		print();
	}
	
}
