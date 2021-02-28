import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		Pass1 p = new Pass1();
		String fileName = "C:\\Users\\dell\\Desktop\\TE_SEM_02\\SPOS\\A1_AssemblerPassOne\\";
		Scanner scan = new Scanner(System.in);
		System.out.println("\t\t-->File Name : ");
		String fn;
		fn = scan.nextLine();
		fileName+=fn;
		File f = new File(fileName);
		BufferedWriter writer;
		FileWriter fileWriter;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			p.calculate(br);
			
			System.out.println("--------------------------------------\n");
			
			String IC = p.print();
			System.out.println("\t\tIntermediate Code :");
			System.out.println(IC);
			fileWriter = new FileWriter("IC.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(IC);
			writer.close();
			
			System.out.println("--------------------------------------\n");
			
			String symTab = p.printSym();
			System.out.println("Symbol Table:");
			System.out.println(symTab);
			fileWriter = new FileWriter("SYMTAB.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(symTab);
			writer.close();
			
			System.out.println("--------------------------------------\n");
			
			
			String LITTAB = p.printLIT();
			System.out.println("Literal Table:");
			System.out.println(LITTAB);
			fileWriter = new FileWriter("LITTAB.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(LITTAB);
			writer.close();
			
			System.out.println("--------------------------------------\n");
			
			String PoolTab = p.printpool();
			System.out.println("Pool Table:");
			System.out.println(PoolTab);
			fileWriter = new FileWriter("PoolTab.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(PoolTab);
			writer.close();
			
			System.out.println("--------------------------------------\n");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("\t\tFile Not Found :( ");
			e.printStackTrace();
		}
	}

}
