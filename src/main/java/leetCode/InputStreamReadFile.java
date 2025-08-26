package leetCode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

public class InputStreamReadFile {

	public static void main(String[] args) throws IOException {
		long time = System.currentTimeMillis();
		int bufferSize = 1024;
		byte[] chunk = new byte[bufferSize];
		ByteBuffer byteBuf = ByteBuffer.allocate(bufferSize);
		int i;
		char c;
		String line;
		System.out.println("Characters printed:");
		//FileInputStream是读取一个文件来作InputStream
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("input.txt"));
				BufferedReader reader = new BufferedReader(new InputStreamReader(bis, "utf-8"), bufferSize);) {

//			// read bytes to the buffer
//			i = bis.read(chunk);
//
//			// prints
//			System.out.println("Number of bytes read: " + i);
//			// for each byte in buffer
//			for (byte b : chunk) {
//				// converts byte to character
//				c = (char) b;
//
//				// print
//				System.out.print(c);
//			}
			try (FileWriter fw = new FileWriter("Buffered.txt");
					/**
					 * 為了提高寫入的效率，使用了字元流的緩衝區。 建立了一個字元寫入流的緩衝區物件，並和指定要被緩衝的流物件相關聯。
					 */
					BufferedWriter writer = new BufferedWriter(fw);) {
				
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
					writer.write(line);
					// must do this: .readLine() will have stripped line endings
					writer.newLine();
				}
			}

		}

	}

}
