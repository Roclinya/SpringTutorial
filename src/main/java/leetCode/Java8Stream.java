package leetCode;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class Java8Stream {

	public static void main(String[] args) {
		// Creating an IntStream
		IntStream stream = IntStream.range(1, 7);

		// 測試mapToObj by Int
//		// Creating a Stream of Strings
//		// Using IntStream mapToObj(IntFunction mapper)
//		// to store binary representation of
//		// elements in IntStream
//		Stream<String> stream1 = stream.mapToObj(num -> Integer.toBinaryString(num));
//		// Displaying an object-valued Stream
//		// consisting of the results of
//		// applying the given function.
//		stream1.forEach(System.out::println);

		// 測試mapToObj by map
//		Stream<Object> stream2 = stream.mapToObj(i -> {
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("Anderson", String.valueOf(i + 1));
//			map.put("Wang", String.valueOf(i + 2));
//			map.put("Lin", String.valueOf(i + 3));
//
//			return map;
//		});
//
////		stream2.forEach(System.out::println);
//		System.out.println(stream2.collect(Collectors.toList()));

		// 測試mapToObj by object
		Stream<Column> stream3 = stream.mapToObj(i -> {
			Column column = new Column();
			column.setHeader("Header" + i);
			column.setType("Type" + i);
			return column;
		});
		List<Column> columnList = stream3.collect(toList());
		columnList.forEach(e->{
			System.out.println(e.getHeader());
		});
		
		
		//reduce test 自動加總同樣欄位的資料使用

		List<Map<String, String>> listOfMaps = new ArrayList<Map<String, String>>();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Type", "M4");
		map.put("Amt", "1.1f");
		map.put("Car", "BMW");
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Type", "GLC200");
		map2.put("Amt", "2.2f");
		map2.put("Car", "Benz");
		listOfMaps.add(map);
		listOfMaps.add(map2);
		// the Integer value 0 is the identity. 
		//It stores the initial value of the reduction operation and also 
		//the default result when the stream of Integer values is empty.
		Float result = listOfMaps.stream().map(e->Float.valueOf(e.get("Amt"))).reduce(0f,Float::sum);
		System.out.println(">> "+result);



		//ASC
		List<Integer> sortedAsc = Stream.of(120,24,59,63,11,74)
				.sorted()
				.collect(toList());
		System.out.println("sorted asc: " + sortedAsc);
//sorted asc: [11, 24, 59, 63, 74, 120]

//DESC
		List<Integer> sortedDesc = Stream.of(120,24,59,63,11,74)
				.sorted((n1,n2) -> n2.compareTo(n1))
				.collect(toList());
		System.out.println("sorted desc: " + sortedDesc);
//sorted desc: [120, 74, 63, 59, 24, 11]
		
	

	}
}
