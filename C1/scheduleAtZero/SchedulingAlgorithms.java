package scheduleAtZero;

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
			System.out.println("\t\t------Scheduling Algos------\n\n");
			System.out.println("\t-->Enter your choice");
			System.out.println("\t\t1.FCFS\n\t\t2.SJF\n\t\t3.Priority\n\t\t4.Robin Round\n\t\t5.Exit");
			ch = scan.nextInt();
			scan.nextLine();
			switch(ch) {
			case 1:{
				int n; //Total Number of Processes
				
				System.out.println("Enter the total number of processes");
				
				n = scan.nextInt();
				scan.nextLine();
				
				ArrayList<proc>process = new ArrayList<proc>();
				
				int i=0;
				while(i<n) {
					System.out.println("Enter the Process(number) in the Ready Queue and its corresponding execution time");
					int pid = scan.nextInt();
					scan.nextLine();
					int exec = scan.nextInt();
					scan.nextLine();
					proc p = new proc(pid,exec);
					process.add(p);
					i++;
				}
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
					System.out.println("Enter the Process(number) in the Ready Queue and its corresponding execution time");
					int pid = scan.nextInt();
					scan.nextLine();
					int exec = scan.nextInt();
					scan.nextLine();
					proc p = new proc(pid,exec);
					process.add(p);
					i++;
				}
				Collections.sort(process,new ExecComparator()); 
				FCFS fcfs = new FCFS(process,n);
				fcfs.findAvgTime();
				break;
			}
			case 3:{
				int n; //Total Number of Processes
				
				System.out.println("\t-->Enter the total number of processes");
				
				n = scan.nextInt();
				scan.nextLine();
				
				ArrayList<proc>process = new ArrayList<proc>();
				
				int i=0;
				while(i<n) {
					System.out.println("\t\t-->Enter the Process id:");
					int pid = scan.nextInt();
					scan.nextLine();
					System.out.println("\t\t-->Execution time : ");
					int exec = scan.nextInt();
					scan.nextLine();
					System.out.println("\t\t-->Priority");
					int priority = scan.nextInt();
					scan.nextLine();
					proc p = new proc(pid,exec,priority);
					process.add(p);
					i++;
				}
				Collections.sort(process,new PriorComparator()); 
				FCFS fcfs = new FCFS(process,n);
				fcfs.findAvgTime();
				break;
			}
			case 4:{
				int n; //Total Number of Processes
				
				System.out.println("Enter the total number of processes");
				
				n = scan.nextInt();
				scan.nextLine();
				
				ArrayList<proc>process = new ArrayList<proc>();
				
				int i=0;
				while(i<n) {
					System.out.println("Enter the Process(number) in the Ready Queue and its corresponding execution time");
					int pid = scan.nextInt();
					scan.nextLine();
					int exec = scan.nextInt();
					scan.nextLine();
					proc p = new proc(pid,exec);
					process.add(p);
					i++;
				}
				System.out.println("Enter quantum : ");
				int quantum = scan.nextInt();
				scan.nextLine();
				RoundRobin r = new RoundRobin(process,n,quantum);
				r.findAvgTime();
				break;
			}
			}
		}while(ch!=5);
	}	
}
