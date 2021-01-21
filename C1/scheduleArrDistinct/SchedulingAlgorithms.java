package scheduleArrDistinct;

import java.util.*;
import java.io.*;


class ExecComparator implements Comparator<proc> { 
    // override the compare() method 
    public int compare(proc a, proc b) 
    { 
    	return a.exec - b.exec;
    } 
} 

class ArrComparator implements Comparator<proc> { 
    // override the compare() method 
    public int compare(proc a, proc b) 
    { 
    	return a.arr - b.arr;
    } 
} 
class PriorComparator implements Comparator<proc> { 
    // override the compare() method 
    public int compare(proc a, proc b) 
    { 
    	return a.priority - b.priority;
    } 
} 
public class SchedulingAlgorithms {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int ch  = 0;
			do {
				System.out.println("\t-->Enter your choice");
				System.out.println("\t\t1.FCFS\n\t\t2.SJF\n\t\t3.Priority\n\t\t4.Robin Round\n\t\t5.Exit");
				ch = scan.nextInt();
				scan.nextLine();
				switch(ch) {
				case 1:{
					int n; //Total Number of Processes
					
					System.out.println("\t\t-->Enter the total number of processes");
					
					n = scan.nextInt();
					scan.nextLine();
					
					ArrayList<proc>process = new ArrayList<proc>();
					
					int i=0;
					while(i<n) {
						System.out.println("\t\t-->Enter the Process id");
						
						
						int pid = scan.nextInt();
						scan.nextLine();
						System.out.println("\t\t-->Execution time");
						int exec = scan.nextInt();
						scan.nextLine();
						System.out.println("\t\t-->Arrival Time");
						int arr = scan.nextInt();
						scan.nextLine();
						proc p = new proc(pid,exec,0,arr);
						process.add(p);
						i++;
					}
					Collections.sort(process,new ArrComparator()); 
					FCFS fcfs = new FCFS(process,n);
					fcfs.findAvgTime();
					break;
				}
				case 2:{
					int n; //Total Number of Processes
					
					System.out.println("\t\t-->Enter the total number of processes");
					
					n = scan.nextInt();
					scan.nextLine();
					
					ArrayList<proc>process = new ArrayList<proc>();
					
					int i=0;
					while(i<n) {
						System.out.println("\t\t-->Enter the Process id");
						
						
						int pid = scan.nextInt();
						scan.nextLine();
						System.out.println("\t\t-->Execution time");
						int exec = scan.nextInt();
						scan.nextLine();
						System.out.println("\t\t-->Arrival Time");
						int arr = scan.nextInt();
						scan.nextLine();
						proc p = new proc(pid,exec,0,arr);
						process.add(p);
						i++;
					}
					Collections.sort(process,new ArrComparator()); 
					SJF sjf = new SJF(process,n);
					sjf.findAvgTime();
					break;
				}
				case 3:{
					int n; //Total Number of Processes
					
					System.out.println("\t\t-->Enter the total number of processes");
					
					n = scan.nextInt();
					scan.nextLine();
					
					ArrayList<proc>process = new ArrayList<proc>();
					
					int i=0;
					while(i<n) {
						System.out.println("\t\t-->Enter the Process id");
						int pid = scan.nextInt();
						scan.nextLine();
						System.out.println("\t\t-->Execution time");
						int exec = scan.nextInt();
						scan.nextLine();
						System.out.println("\t\t-->Arrival Time");
						int arr = scan.nextInt();
						scan.nextLine();
						System.out.println("\t\t-->Priority ");
						int priority = scan.nextInt();
						scan.nextLine();
						proc p = new proc(pid,exec,priority,arr);
						process.add(p);
						i++;
					}
//					Collections.sort(process,new ArrComparator()); //After sorting according to priority it behaves same as FCFS
					Priority pr = new Priority(process,n);
					pr.findAvgTime();
					break;
				}
				case 4:{
					int n; //Total Number of Processes
					
					System.out.println("\t\t-->Enter the total number of processes");
					
					n = scan.nextInt();
					scan.nextLine();
					
					ArrayList<proc>process = new ArrayList<proc>();
					
					int i=0;
					int qtm = 0;

					System.out.println("\t\t-->Quantum");
					qtm = scan.nextInt();
					scan.nextLine();
					
					while(i<n) {
						System.out.println("\t\t-->Enter the Process id");
						int pid = scan.nextInt();
						scan.nextLine();
						System.out.println("\t\t-->Execution time");
						int exec = scan.nextInt();
						scan.nextLine();
						System.out.println("\t\t-->Arrival Time");
						int arr = scan.nextInt();
						scan.nextLine();
						proc p = new proc(pid,exec,0,arr);
						process.add(p);
						i++;
					}
					Collections.sort(process,new ArrComparator()); 
					RoundRobin rr = new RoundRobin(process,n,qtm);
					rr.findAvgTime();
					break;
				}
				}
			}while(ch!=5);	
	}
}
