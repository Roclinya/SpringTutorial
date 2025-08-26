package leetCode;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;

public class Test {

	public static void main(String[] args) {

//		   //创建优先队列
//        PriorityQueue<Integer> numbers = new PriorityQueue<>();
//        //使用add()方法
//        numbers.add(4);
//        numbers.add(2);       
//        //使用offer()方法
//        numbers.offer(1);
//        System.out.println("PriorityQueue: " + numbers);
//
//        int numberQuery = numbers.peek();
//        System.out.println("訪問元素: " + numberQuery);
//        
//        //使用remove()方法
//        boolean result = numbers.remove(2);
//        System.out.println("元素2是否已删除? " + result);
//
//        //使用poll()方法
//        int numberPoll = numbers.poll();
//        System.out.println("使用poll()删除的元素: " + numberPoll);
//        
//        System.out.println("PriorityQueue: " + numbers);

		String string = "0123456789";
		byte[] bytes = string.getBytes();

		String result = new String(bytes, 7, 2).trim();
		System.out.println(result);

//        string.length()
		System.out.println(string.substring(9, 10));

		String string2 = "1,2,3,4";
		String[] splitResult = string2.split(",");
		System.out.println(splitResult[2]);
		splitResult[2].replace("-1", "");

		System.out.println(new Timestamp(System.currentTimeMillis()));
		System.out.println(Timestamp.valueOf("2023-01-01 11:11:22"));

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("123", "test");
		map.put("123", "test2");
		map.put("empty", "");
		map.put("Null", null);
		System.out.println(map);

		HashMap<String, Object> mapStringOb = new HashMap<String, Object>();
		mapStringOb.put("456", "test3");
		mapStringOb.put("empty", "");
		mapStringOb.put("Null", null);
		System.out.println(mapStringOb);

		// main thread
		Thread currThread = Thread.currentThread();

		System.out.println("current thread = " + currThread);

		ArrayList<String> arrayList = new ArrayList<String>();
		System.out.println(ObjectUtils.isEmpty(arrayList));

		ArrayList<Map<String, String>> arrayMapList = new ArrayList<Map<String, String>>();
		HashMap<String, String> arrayMap = new HashMap<String, String>();
		arrayMap.put("a1", "aa");
		arrayMap.put("b1", "bb");
		arrayMapList.add(arrayMap);
		HashMap[] mapToArrayList = new HashMap[arrayMapList.size()];
		System.out.println(mapToArrayList);
		arrayMapList.toArray(mapToArrayList);
		System.out.println(arrayMapList);
		List resultArrayMapList = arrayMapList.stream().map(e -> new ArrayList<String>(e.values()))
				.flatMap(List::stream).collect(Collectors.toList());
		System.out.println(resultArrayMapList);

	}
}
