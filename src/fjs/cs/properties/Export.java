package fjs.cs.properties;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fjs.cs.dto.MSTCUSTOMER;

public class Export {

	String fileName = "C:\\Users\\CAM NHUNG\\Desktop\\InformationManagement\\WEB-INF\\export\\Customer_"
			+ formatDate() + ".csv";
	private static final String PHAY = ",";
	private static final String NEW_LINE = "\n";

	public void writeFile(List<MSTCUSTOMER> customers, List<String> listHeader) {
		// String FILE_HEADER = "\"Customer Id\",\"Customer
		// Name\",\"Sex\",\"Birthday\",\"Email\",\"Address\"";
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			// Viet header.
			fileWriter.append(header(listHeader));
			// Them hang moi sau header
			fileWriter.append(NEW_LINE);
			// Viet customer vao file
			for (MSTCUSTOMER customer : customers) {
				for (String header : listHeader) {
					if (header.equals("CustomerID")) {
						fileWriter.append("\"" + String.valueOf(customer.getCustomerID()) + "\"");
						fileWriter.append(PHAY);
					}
					if (header.equals("CustomerName")) {
						fileWriter.append("\"" + customer.getCustomerName() + "\"");
						fileWriter.append(PHAY);
					}
					if (header.equals("Sex")) {
						fileWriter.append("\"" + customer.getSex() + "\"");
						fileWriter.append(PHAY);
					}
					if (header.equals("Birthday")) {
						fileWriter.append("\"" + customer.getBirthday() + "\"");
						fileWriter.append(PHAY);
					}
					// fileWriter.append("\"" + customer.getEmail() + "\"");
					// fileWriter.append(PHAY);
					if (header.equals("Address")) {
						fileWriter.append("\"" + customer.getAddress() + "\"");
					}
				}
				fileWriter.append(NEW_LINE);
			}
			System.out.println("CSV file was created successfully !!!");
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
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

	private String header(List<String> listHeader) {
		String th = "";
		for (String header : listHeader) {
			if (!header.equals("Checkbox")) {
				th = th + "\" " + header + "\",";
			}
		}
		return th.substring(0, th.length() - 1);
	}
}