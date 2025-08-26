package leetCode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ReadFile {

	// https://javarush.com/tw/groups/posts/tw.593.bufferedreader--bufferedwriter

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {

		// 在複製檔案時是先將檔案整個讀取到記憶體後後才寫入。若是檔案非常大的時候會占滿記憶體並導致伺服器掛掉。請改成讀取一定量後就先寫入檔案，避免發生記憶體耗盡。
		try (FileOutputStream fos = new FileOutputStream("input.txt");
				OutputStreamWriter outfile = new OutputStreamWriter(fos, "MS950");
				PrintWriter msgfile = new PrintWriter(outfile, true);) {
			System.out.println("write file success");
			msgfile.println("this is a pen");
			msgfile.println("this is a desk");

		} catch (Exception ex) {
			// 例外處理
			System.out.println("file not found");
		}

		ArrayList<String> list = new ArrayList<String>();

		try (FileReader fr = new FileReader("input.txt"); BufferedReader bfr = new BufferedReader(fr);) {

			String instr = null;
			while ((instr = bfr.readLine()) != null) {
				System.out.println(instr);
				list.add(instr);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (FileOutputStream fos2 = new FileOutputStream("input2.txt");
				OutputStreamWriter outfile2 = new OutputStreamWriter(fos2, "MS950");
				PrintWriter msgfile2 = new PrintWriter(outfile2, true);) {

			list.forEach(e -> {
				msgfile2.println(e);
			});
			list.forEach(e -> {
				msgfile2.println(e + " 2nd");
			});
		}

	}

}
