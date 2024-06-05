package fjs.cs.properties;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fjs.cs.dto.MSTORDER;

public class ExportOrder {

	String fileName = "C:\\Users\\CAM NHUNG\\Desktop\\InformationManagement\\WEB-INF\\export\\Orders_"
			+ formatDate() + ".csv";
	private static final String PHAY = ",";
	private static final String NEW_LINE = "\n";

	public void writeFile(List<MSTORDER> orders) {
		 String FILE_HEADER = "\"OrderId\",\"CustomerId\",\"OrderDate\",\"TotalAmount\"";
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			// Viet header.
			fileWriter.append(FILE_HEADER);
			// Them hang moi sau header
			fileWriter.append(NEW_LINE);
			// Viet customer vao file
			for (MSTORDER order : orders) {
						fileWriter.append("\"" + order.getOrderId() + "\"");
						fileWriter.append(PHAY);
						fileWriter.append("\"" + order.getCustomerId() + "\"");
						fileWriter.append(PHAY);
						fileWriter.append("\"" + order.getDate() + "\"");
						fileWriter.append(PHAY);
						fileWriter.append("\"" + order.getTotalAmount() + "\"");
				fileWriter.append(NEW_LINE);
			}
			System.out.println("CSV file was created successfully !!!");
		} catch (Exception e) {
			System.out.println("Error in Csv FileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * get date
	 * 
	 * @return
	 */
	private String formatDate() {
		java.util.Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String format = formatter.format(date);
		return format;
	}
}
