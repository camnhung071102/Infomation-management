package fjs.cs.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import fjs.cs.common.Constants;

public class ReadProperties {
	
	/**
	 * Read rowOfPage in file .properties.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public int readRowOfPage() throws FileNotFoundException {
		// Khoi tao bien rowOfPage .
		int rowOfPage = 0;
		String url = Constants.URL;
		// Đọc dữ liệu từ File với Scanner
		FileInputStream fileInputStream = new FileInputStream(url);
		Scanner scanner = new Scanner(fileInputStream);
		// Khoi tao bien row.
		int row = 0;
		try {
			while (scanner.hasNextLine()) {
				// Khoi tao bien line.
				String line = scanner.nextLine();
				// Khoi tao bien part.
				String[] part = line.split("=");
				// Neu row = 0
				if (row == 0) {
					// Gan gia trị cho rowOfPage.
					rowOfPage = Integer.parseInt(part[1]);
					// Dung vong lap.
					break;
				}
			}
		} finally {
			try {
				scanner.close();
				fileInputStream.close();
			} catch (IOException ex) {
			}
		}
		return rowOfPage;
	}

}