package leetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ListMapForEach {

	public static void main(String[] args) {
	

		HashMap dataMap1 = new HashMap();
		dataMap1.put("Jack", "Diamond");
		dataMap1.put("David", "Hearts");
		dataMap1.put("Alvin", "Club");
		dataMap1.put("Larry", "Spade");

		HashMap dataMap2 = new HashMap();
		dataMap2.put("Jack2", "Diamond2");
		dataMap2.put("David2", "Hearts2");
		dataMap2.put("Alvin2", "Club2");
		dataMap2.put("Larry2", "Spade2");

		List<Map<String, String>> list = new LinkedList<>();
		list.add(dataMap1);
		list.add(dataMap2);
		list.forEach(rs->{
			System.out.println(rs.get("Jack2"));
			if("Diamond2".equals(rs.get("Jack2"))) {
				System.out.println("------Jack2!------");
			}else {
				System.out.println("------------------");
			}
			
		});

	}

}
