import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
public class sam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Map<String, Integer>s = new HashMap<>();
s.put("yogita", 1);
s.put("yogita", 5);

for( Entry<String, Integer> entry : s.entrySet() ){
    System.out.println( entry.getKey() + " => " + entry.getValue() );
}
	}

}
