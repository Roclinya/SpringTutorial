package exception;

import java.io.FileNotFoundException;
import java.io.InterruptedIOException;
import java.util.Random;

public class DealFile {

	public void openFile() throws FileNotFoundException {
		if (new Random().nextBoolean()) {
			System.out.println("隨機True");
			throw new FileNotFoundException("File not existed");
		} else {
			System.out.println("隨機False");
			throw new FileNotFoundException("File is broken");
		}
	}

	public void readFile() throws FileNotFoundException {
		System.out.println("file reading");
		try {
			openFile();
		} catch (FileNotFoundException e) {
			throw e;
		}
	}

	public void deals() throws InterruptedIOException {
		try {
			readFile();
		} catch (FileNotFoundException e) {
			/**
			 * 使用initCause函數，把readFile函數中的FileNotFoundException轉爲InterruptedIOException
			 * 並把FileNotFoundException傳遞給外層。
			 */
//			InterruptedIOException ie = new InterruptedIOException("file is interrupted");

			//以下兩種方式一樣都是把cause往上丟
			// 作法A
			MyException ie = new MyException("file is interrupted!!!!", e);
//			e.printStackTrace();
			// 作法B
//			MyException ie = new MyException("file is interrupted");
//			ie.initCause(e);
			throw ie;
		}
	}

	public static void main(String[] args) throws Exception {
		DealFile df = new DealFile();
		df.deals();

//		try {
//			df.deals();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("Cause : "
//					+ e.getCause());
//		}

	}

}
