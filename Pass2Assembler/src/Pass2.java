import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Pass2 {
	ArrayList<String>SYMTAB ;
	ArrayList<String>LITTAB ;
	StringBuilder code;
	
	private static final String IMPERATIVE = "IS";
	private static final String DECLARATIVE = "DL";
	private static final String SYMBOL = "S";
	private static final String LITERAL = "L";
	private static final String CONSTANT = "C";
	private static final String ASSM_DIRECTIVE = "AD";
	
	public Pass2() {
		SYMTAB = new ArrayList<>();
		LITTAB = new ArrayList<>();
		code = new StringBuilder();
	}
	public void setSymbolTable(BufferedReader reader) throws NumberFormatException, IOException {
		assignTo(reader, SYMTAB);
	}
	
	public void setLiteralTable(BufferedReader reader) throws NumberFormatException, IOException {
		assignTo(reader,LITTAB);
	}
	private void assignTo(BufferedReader reader,ArrayList<String>indexTAB) throws NumberFormatException, IOException {
		String line;
		int line_count=0;
		while((line = reader.readLine()) != null) {
			if(line_count==0) {
				line_count++;
				continue;
			}
			String parts[] = line.split("\\s+");
//			System.out.println(parts[0]);
			indexTAB.add(Integer.parseInt(parts[0]),parts[2]);
		}
	}
	
	public String generateMC(BufferedReader reader) throws IOException {
		String line;
		while((line=reader.readLine())!=null) {
			String[] parts = line.split("\\s+");
			
			if (parts[1].contains(ASSM_DIRECTIVE)) {
				code.append("\t---\n");
				continue;
			}
			// Imperative statement
			if (parts[1].contains(IMPERATIVE)) {
				code.append(parts[0]).append("  ");				// Location counter
				int opcode = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
				code.append("+" + String.format("%02d", opcode)).append("  ");			// Opcode
				
				switch (parts.length) {
					case 3:
						// handle operand-1 as operand-2
						code.append("00").append("  ");		// empty operand-1
						handleOperandTwo(parts[2]);
						break;
					case 4:
						// Normal Imperative statement
						int regNo = Integer.parseInt(parts[2].replaceAll("[^0-9]", ""));
						code.append(String.format("%02d", regNo)).append("  ");	// register
//						System.out.println("IMP 4 code"+code);
						handleOperandTwo(parts[3]);
						break;
					case 2:
						// STOP statement
						code.append("00").append("  ").append("000").append("\n");
				}
			}
						
			// Declarative statement
			if (parts[1].contains(DECLARATIVE)) {
				code.append(parts[0]).append("  ");				// Location counter
				code.append("00").append("  ").append("00").append("  ");
				handleOperandTwo(parts[2]);
			}
		}
		return code.toString();
	}
	private void handleOperandTwo(String operand) {
		int value = Integer.parseInt(operand.replaceAll("[^0-9]", ""));
		
		String output = "000";
		if (operand.contains(SYMBOL)) {
//			System.out.println("Symbol value="+value+" addr = "+SYMTAB.get(value));
			output = String.format("%03d", Integer.parseInt(SYMTAB.get(value)));
		} else if (operand.contains(LITERAL)) {
			output = String.format("%03d", Integer.parseInt(LITTAB.get(value)));
		} else if (operand.contains(CONSTANT)) {
			output = String.format("%03d", value);
//			System.out.println("Decl "+"VALUE= "+value+" output = "+output);
		}
		code.append(output).append("\n");
	}
}
