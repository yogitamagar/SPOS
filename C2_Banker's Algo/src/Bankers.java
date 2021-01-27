import java.util.*;

public class Bankers {

	public static void main(String[] args) {
		int m ;//No of Resources
		int n ; //No of Processes
		int[][] alloc;    //Allocation Matrix
		int max[][];      //Maximum Matrix
		int total[];	//Total resources available of each type
		int work[];	//Available Matrix
		int need[][];	//Remaining need Matrix
		ArrayList<Integer>safeSeq = new ArrayList<Integer>();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\t\t\tBanker's Algorithm\n");
		System.out.println("\t-->Total Processes (n) : ");
		n = scan.nextInt();
		scan.nextLine();
		System.out.println("\t-->Total Resources (m) : ");
		m = scan.nextInt();
		scan.nextLine();
		
		total = new int[m];
		work = new int[m];
		System.out.println("\t\tEnter total number of resources of each type :");
		for(int i = 0 ; i < m ; i++) {
			total[i] = scan.nextInt();
			work[i] = total[i];
		}
		scan.nextLine();
		
		alloc = new int[n][m];
		System.out.println("\t\tAllocation Matrix");
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				alloc[i][j] = scan.nextInt();
				work[j] -= alloc[i][j];
			}
			scan.nextLine();
		}
		
		max = new int[n][m];
		System.out.println("\t\tMaximum Matrix");
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				max[i][j] = scan.nextInt();
			}
			scan.nextLine();
		}
		
		need = new int[n][m];
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				need[i][j] = max[i][j] - alloc[i][j];
			}
		}
		
		
		
		int finish[] = new int[n];   //finish[i] = 1 if process i has completed its execution
		for(int i = 0 ; i < n ; i++) {
			finish[i] = 0;
		}
		
		System.out.println("\tInitially ");
		System.out.print("\t\tWork Array : ");
		for(int i : work) System.out.print(i+" ");
		System.out.print("\n\t\tFinish Array : ");
		for(int i : finish ) System.out.print(i+" ");
		
		System.out.println();
		int count = 0;
		while(count < n) {
			boolean found = false;
			for(int i = 0 ; i < n ; i++) {
				int j;
				if(finish[i] == 0) {
					for(j = 0 ; j < m ; j++) {
						if(need[i][j] > work[j]) break;
					}
					if(j==m) {
						safeSeq.add(i);
						count++;
						for(int k = 0 ; k < m ; k++) {
							work[k] = work[k] + alloc[i][k];
						}
						finish[i] = 1;
						found = true;
						
						System.out.println("\n\tProcess "+i+" executed");
						System.out.print("\t\tWork Array : ");
						for(int w : work) System.out.print(w+" ");
						System.out.print("\n\t\tFinish Array : ");
						for(int f : finish ) System.out.print(f+" ");
						System.out.println();
					}
				}
			}
				if(!found) break;    // If in an entire loop no process has been processed
		}
		
		if(count<n) System.out.println("\n\t\t System is in Unsafe State");
		
		else {
			System.out.print("\n\n\tSafe Sequence : ");
			for(int i : safeSeq) {
				System.out.print(i+"->");
			}
		}
	}

}
