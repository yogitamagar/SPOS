import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class Pass1{
	int LC;
	String[][] opcode= new String[100][4] ;

	HashMap<String, String> Symbol;
	HashMap<String, String> lit_table;
	ArrayList<Integer>pool_table;
	ArrayList<String>index_in_lit_table ;
	ArrayList<String>index_in_sym_table ;
	
	Pass1(){
		LC = 0;
		Symbol=new HashMap<String, String>();
		lit_table=new HashMap<String, String>();
		pool_table = new ArrayList<Integer>();
		index_in_lit_table = new ArrayList<String>();
		index_in_sym_table = new ArrayList<String>();
	}
	String print() {
		StringBuilder s= new StringBuilder();
		for(int i = 0;i<40;i++) {
			boolean status = false;
			for(int j = 0 ; j<4;j++) {
				if(opcode[i][j]!=null) {
				s.append(opcode[i][j]+" ");
				status = true;
				}
			}
			if(!status)break;
			s.append("\n");
		}
		return s.toString();
	}
	String printLIT() {
		StringBuilder s = new StringBuilder();
		s.append("Index\tLiteral\tAddress\n");
		for(int i = 0;i<index_in_lit_table.size();i++) {
			s.append(i+"\t"+index_in_lit_table.get(i)+"\t"+lit_table.get(index_in_lit_table.get(i))+"\n");
		}
		return s.toString();
	}
	String printSym() {
		StringBuilder s = new StringBuilder();
		s.append("Index\tSymbol\tAddress\n");
		for(int i = 0;i<index_in_sym_table.size();i++) {
			s.append(i+"\t"+index_in_sym_table.get(i)+"\t"+Symbol.get(index_in_sym_table.get(i))+"\n");
		}
		return s.toString();
	}
	String printpool() {
		StringBuilder s = new StringBuilder();
		for(int i : pool_table) {
			s.append("#"+i+"\n");
		}
		return s.toString();
	}
	
	public int expr(String str) {
		int temp = 0;
		if (str.contains("+")) {
			String splits[] = str.split("\\+");
			temp = Integer.parseInt(Symbol.get(splits[0])) + Integer.parseInt(splits[1]);
		} else if (str.contains("-")) {
			String splits[] = str.split("\\-");
			temp = Integer.parseInt(Symbol.get(splits[0])) - Integer.parseInt(splits[1]);
		} else if (Symbol.containsKey(str)) {
			temp = Integer.parseInt(Symbol.get(str));
		} else {
			temp = Integer.parseInt(str);
		}
		return temp;
	}
	
	void calculate(BufferedReader br) throws IOException {
		OpcodeTable opTab = new OpcodeTable();
		int line_count = 0;
		String line;
		while((line = br.readLine()) != null) {
			String[] split_words = line.split("\\t");
			if(line_count == 0 ) {
				if(split_words.length>1) {
					LC = Integer.parseInt(split_words[1]);
					opcode[line_count][2] = "(C,"+split_words[1]+")";
				}
				opcode[line_count][0] = "---";
				opcode[line_count][1] = "(AD,01)";
				LC = LC++;
			}
			else {
				//if label field is not null
				if(split_words[0].length()>0) {
					if(!Symbol.containsKey(split_words[0]))
					index_in_sym_table.add(split_words[0]);
					Symbol.put(split_words[0], String.valueOf(LC));
				}	
				if(opTab.imperative.containsKey(split_words[1])) {
					opcode[line_count][0]=String.valueOf(LC);
					opcode[line_count][1]= "(IS,"+opTab.imperative.get(split_words[1])+ ")";
					if(split_words.length>2) {
					opcode[line_count][2]= "("+opTab.impSub.get(split_words[2])+ ")";
					if (split_words[3]. charAt(0)== '=') {
						if(!lit_table.containsKey(split_words[3])) {
							lit_table.put(split_words[3], "999");
							index_in_lit_table.add(split_words[3]);
						}
						opcode[line_count][3]= "(L,"+index_in_lit_table.indexOf(split_words[3])+ ")";
					}else {
						if(!Symbol.containsKey(split_words[3])) {
							Symbol.put(split_words[3], "999");
							index_in_sym_table.add(split_words[3]);
						}
						
						opcode[line_count][3]= "(S,"+index_in_sym_table.indexOf(split_words[3])+ ")";
					}
					}
					LC++;
				}
				else if (opTab.declarative.containsKey(split_words[1])) {
					opcode[line_count][0]=String.valueOf(LC);
					opcode[line_count][1]= "(DL,"+opTab.declarative.get(split_words[1])+ ")";
					opcode[line_count][2]= "(C,"+split_words[2]+ ")";
					if(split_words[1].equals("DS")) LC = LC + Integer.parseInt(split_words[2]);
					else LC++;
				}
				else if(opTab.directive.containsKey(split_words[1])) {
					opcode[line_count][0]="---";
					opcode[line_count][1]= "(AD,"+opTab.directive.get(split_words[1])+ ")";
					if(split_words[1].equals("LTORG") || split_words[1].equals("END")) {
						if(pool_table.size()==0) {
							pool_table.add(0);
						}
						else {
							pool_table.add(lit_table.size());
						}
						for(int i = 0 ; i<index_in_lit_table.size();i++) {
							String addr = lit_table.get(index_in_lit_table.get(i));
							if(addr.equals("999")) {
								lit_table.put(index_in_lit_table.get(i),String.valueOf(LC));
								LC++;
							}
						}
					}
					if(split_words[1].equals("ORIGIN")) {
						LC = expr(split_words[2]);
					}
					if(split_words[1].equals("EQU")) {
						int addr = expr(split_words[2]);
						Symbol.put(split_words[0],String.valueOf(addr));
						LC++;
					}
				}
			}
			line_count++;
		}
	}
}