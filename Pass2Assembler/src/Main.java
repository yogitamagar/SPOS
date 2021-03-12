import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		String path = "C:\\Users\\dell\\Desktop\\TE_SEM_02\\SPOS\\A1_AssemblerPassOne\\";
		FileReader file;
		BufferedReader reader;
		Pass2 assembler = new Pass2();
		
		try {
			reader = new BufferedReader(new FileReader(path+"SYMTAB.txt"));
			assembler.setSymbolTable(reader);
			reader = new BufferedReader(new FileReader(path+"LITTAB.txt"));
			assembler.setLiteralTable(reader);
			reader = new BufferedReader(new FileReader(path+"IC.txt"));
			String machineCode = assembler.generateMC(reader);
			System.out.println(machineCode);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
