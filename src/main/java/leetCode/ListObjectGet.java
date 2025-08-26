package leetCode;

import java.util.*;
import java.util.stream.Stream;

import org.springframework.util.CollectionUtils;

public class ListObjectGet {

	public static void main(String[] args) {

		ListObject obj = new ListObject("value1", "value2", "value3");
		ListObject obj2 = new ListObject("value4", "value5", "value6");
		ArrayList<ListObject> list = new ArrayList<ListObject>();
		list.add(obj);
		list.add(obj2);
		Stream<ListObject> stream = list.stream();
		stream.filter(e -> e != null).forEach(t -> {
			System.out.println(t);
		});

		/* method1 java 8 Stream */
//			stream.forEach(t -> {
//			System.out.println("結果->>>" + t.getObj1() + " : " + t.getObj2() + " : " + t.getObj3());
//		});
		/* method2 */
//		for(ListObject t :list) {
//			System.out.println("結果->>>" + t.getObj1() + " : " + t.getObj2() + " : " + t.getObj3());
//		}
		
		//CollectionUtils.containsAny
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("AA");//candidates
		ArrayList list2 = new ArrayList<String>();
		list2.addAll(Arrays.asList("AA", "BB"));
		System.out.println("List2 = " + list2);

		System.out.println("是否有包含:"+ (CollectionUtils.containsAny(list2, list1)==true?"Yes":"No"));//(source,candidates)

	}

}
