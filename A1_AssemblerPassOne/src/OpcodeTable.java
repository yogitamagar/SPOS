
import java.util.HashMap;

public class OpcodeTable {
	HashMap<String, String> declarative;
	HashMap<String, String> imperative;
	HashMap<String, String> directive;
	HashMap<String, String> impSub;
	
	OpcodeTable(){
		declarative=new HashMap<String, String>();
		imperative=new HashMap<String, String>();
		directive=new HashMap<String, String>();
		impSub=new HashMap<String, String>();
		impSub=new HashMap<String, String>();
		
		declarative.put("DS", "01");
		declarative.put("DC", "02");
		
		imperative.put("STOP", "00");
		imperative.put("ADD", "01");
		imperative.put("SUB", "02");
		imperative.put("MUL", "03");
		imperative.put("MOVER","04");
		imperative.put("MOVEM","05");
		imperative.put("COMP", "06"); 
		imperative.put("BC", "07"); 
		imperative.put("DIV", "08");
		imperative.put("READ", "09"); 
		imperative.put("PRINT", "10"); 
		
		directive.put("END","02");
		directive.put("ORIGIN","03");
		directive.put("EQU","04");
		directive.put("LTORG","05");
		
		impSub.put("LT", "21");
		impSub.put("GT", "22");
		impSub.put("LTE", "23");
		impSub.put("GTE", "24");
		impSub.put("EQU", "25");
		
		impSub.put("AREG", "1");
		impSub.put("BREG", "2");
		impSub.put("CREG", "3");
		impSub.put("DREG", "4");
	}
}
