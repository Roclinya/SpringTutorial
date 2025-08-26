package leetCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;

public class CopyFileToDirectory {

	public static void main(String[] args) throws IOException {
		
		//資料夾不存在,建立一個
		File file =new File("./NewFolder");    
		//如果資料夾不存在則建立    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    System.out.println("//不存在");  
		    file .mkdir();    
		} else   
		{  
		    System.out.println("//目錄存在");  
		} 
		
		
		
		
        //以下實現兩種copy檔案方式, java 7以後推薦使用NIO
		//https://mkyong.com/java/how-to-copy-file-in-java/
		
//		// Apache Commons IO
//		apacheCommonsCopy();
//		// Files.copy (NIO)
//		nioCopy();
		System.out.println("-------");
	}

	private static void nioCopy() throws IOException {
		String srcfilePath = "input.txt";
		String dstDir = "./NioCopyFile.txt"; // ./可以不加  等於同一層
		Path fromFile = Paths.get(srcfilePath);
		Path toFile = Paths.get(dstDir);
		Files.copy(fromFile, toFile, StandardCopyOption.REPLACE_EXISTING); //如果我們想避免 FileAlreadyExistException，那麼我們可以使用 REPLACE_EXISTING 選項替換

	}

	private static void apacheCommonsCopy() throws IOException {

		String srcFilePath = "input.txt";
		String dstDir = "./copyFile/output"; //同一層的資料夾

		System.out.println(new FileSystemResource(srcFilePath));
		File srcFile = new File(srcFilePath);
		File dstDirFile = new File(dstDir);
System.out.println(dstDirFile);
		System.out.println("The contents of the destination directory before copy - " + Arrays.toString(dstDirFile.listFiles()));
		System.out.println("Copying file - " + srcFilePath + " to directory - " + dstDir);

		FileUtils.copyFileToDirectory(srcFile, dstDirFile, false);

		System.out.println("The contents of the destination directory after copy - " + Arrays.toString(dstDirFile.listFiles()));
	}

}
