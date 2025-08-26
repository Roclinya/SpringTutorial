package leetCode;

import java.io.File;

public class FileCheck {

	public static void main(String[] args) {

//		String folderPath = "./copyFile";
//		String textfilePath = "./copyFile/input.txt";
//
//		File folder = new File(folderPath);
//		File textfile = new File(textfilePath);
//		System.out.println("folder isFile? " + folder.isFile());
//		System.out.println("textfile isFile? " + textfile.isFile());
//		if (folder.exists()) {
//			File[] listOfFiles = folder.listFiles();
//			System.out.println(listOfFiles);
//			for (File file : listOfFiles) {
//			    if (file.isFile()) {
//			        System.out.println(file.getName());
//			    }
//			}
//		}
		String folderPath2 = "./copyFile2/copyFile2";
		File folder2 = new File(folderPath2);
		if (folder2.exists()) {
			
		}else {
			System.out.println("folder not exist");
	        /*不存在的話,直接建立資料夾*/
			folder2.mkdirs();
            System.out.print("已成功建立資料夾");
		}
		
	}

}
