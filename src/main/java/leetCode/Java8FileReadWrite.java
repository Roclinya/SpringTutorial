package leetCode;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import org.apache.commons.lang3.StringUtils;

public class Java8FileReadWrite {

	// https://javarush.com/tw/groups/posts/tw.593.bufferedreader--bufferedwriter

	public static void main(String[] args) throws Exception {

		ArrayList<String> dataList = null;

		File fileLog = new File("fileNameLog.txt");
		String fileName = readFileName(fileLog);
		System.out.println("讀取LOG檔案內容： " + fileName);

		File file = new File(fileName + ".txt");
		if (file.exists()) {
			dataList = readFile(file);
			System.out.println("讀取檔案內容： OK " + dataList);
		} else {
			System.out.println("讀取檔案內容： FAIL " + dataList);
			throw new Exception("找不到檔案");
		}

		if(!dataList.isEmpty()) {
			System.out.println("-----------------------------------");
			// 遍歷输出符合条件的元素
//			dataList.stream().filter(StringUtils::isNotBlank).forEach(System.out::println);
			
			 List<String[]> result = dataList.stream().filter(StringUtils::isNotBlank)
			.map(data->{
				System.out.println(data.split(","));
				return data.split(",");})
			.filter(data->{
				if(data.length>1) {
					System.out.println("true");
					return true;
				}else {	
					System.out.println("false");
					return false;
				}
			})
			.map(array->{
				System.out.println("取出該ROW第一筆 ： "+array[0]);
				return array;
			})
			.collect(Collectors.toList());
				System.out.println("-----------------------------------");
			 System.out.println("資料數量："+result.size());
			
		}

	}

	private static ArrayList<String> readFile(File file) throws FileNotFoundException, IOException {

		ArrayList<String> list = new ArrayList<>();
		try (BufferedReader input = new BufferedReader(new FileReader(file))) {
			String instr = null;
			while ((instr = input.readLine()) != null) {
				list.add(instr);
			}
			return list;
		}
	}

	private static String readFileName(File file) throws Exception {
		try (BufferedReader input = new BufferedReader(new FileReader(file))) {
			String instr = null;
			while ((instr = input.readLine()) != null) {
				return instr;
			}
			throw new Exception("查無檔案內容");
		}

	}

}
