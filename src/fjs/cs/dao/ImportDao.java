package fjs.cs.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import au.com.bytecode.opencsv.CSVReader;
import fjs.cs.common.Constants;
import fjs.cs.common.Util;
import fjs.cs.dto.MSTCUSTOMER;

/**
 * Them, cap nhat, truy van du lieu.
 * 
 * @author nhung-vtc-ttv
 *
 */
public class ImportDao extends HibernateDaoSupport {

	public ImportDao() {
	}

	/**
	 * HAM KIEM TRA EXTENSION.
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean checkExtension(String fileName) {
		String extension = "";
		int index = fileName.lastIndexOf('.');

		if (index > 0) {
			extension = fileName.substring(index + 1);
		}

		if (Constants.EXTENSION.equals(extension)) {
			return true;
		}

		return false;
	}

	/**
	 * HAM GAN GIA TRI SEX.
	 * 
	 * @param sex
	 * @return
	 */
	public String checkSex(String sex) {
		String valueSex = Constants.SEX1;

		if (sex.equals(Constants.MALE)) {
			valueSex = Constants.SEX0;
		}

		return valueSex;
	}

	/**
	 * HAM IMPORT FIE CSV.
	 * 
	 * @param formFile
	 * @param PSN_CD
	 * @return
	 * @throws HibernateException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public ActionMessages importFile(FormFile formFile, int PSN_CD)
			throws HibernateException, NumberFormatException, IOException {
		Util util = new Util();
		ActionMessages errors = new ActionMessages();
		ActionMessages errorInsert = new ActionMessages();
		String lineInsert = "";
		String lineUpdate = "";

		try {
			String[] record = null;
			int line = 0;
			boolean check = true;
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			CSVReader reader = new CSVReader(new InputStreamReader(formFile.getInputStream()));
			while ((record = reader.readNext()) != null) {
				if (line == 0) {
					line++;
					continue;
				}
				line++;
				ActionMessages error = new ActionMessages();
				Criteria cr = session.createCriteria(MSTCUSTOMER.class);
				MSTCUSTOMER customer = new MSTCUSTOMER();

				if (!record[0].isEmpty()) {
					customer = (MSTCUSTOMER) cr.add(Restrictions.eq("customerID", Integer.parseInt(record[0])))
							.uniqueResult();
					if (customer != null) {
						if (!record[1].equals(customer.getCustomerName()) || !record[3].equals(customer.getBirthday())
								|| !checkSex(record[2]).equals(customer.getSex())
								|| !record[4].equals(customer.getEmail())) {
							check = false;
						}
					} else {
						error.add("", new ActionMessage("id.exist", line, record[0]));
					}
				}

				if (record[1].isEmpty()) {
					error.add("", new ActionMessage("name.exist", line));
				}

				if (record[1].length() > 50) {
					error.add("", new ActionMessage("name.size", line));
				}

				if (!record[2].isEmpty()) {
					if (!record[2].equals(Constants.MALE) && !record[2].equals(Constants.FEMALE)) {
						error.add("", new ActionMessage("sex.isValid", line, record[2]));
					}
				}

				if (!record[3].isEmpty()) {
					if (!util.checkDate(record[3])) {
						error.add("", new ActionMessage("birthday.isValid", line, record[3]));
					}
				}

				if (!record[4].isEmpty()) {
					if (record[4].length() > 256) {
						error.add("", new ActionMessage("address.size", line));
					}
				}

				if (!error.isEmpty() || (check && !record[0].isEmpty())) {
					errors.add(error);
				}

				if (errors.isEmpty()) {
					customer.setCustomerName(record[1]);
					customer.setSex(checkSex(record[2]));
					customer.setBirthday(record[3]);
					customer.setAddress(record[4]);
					customer.setInsert_psn_cd(PSN_CD);
					customer.setUpdate_psn_cd(PSN_CD);
					customer.setInsert_ymd(Calendar.getInstance().getTime());
					customer.setUpdate_ymd(Calendar.getInstance().getTime());
					if (record[0].isEmpty()) {
						session.save(customer);
						lineInsert = lineInsert + line + ", ";
					} else {
						session.update(customer);
						lineUpdate = lineUpdate + line + ", ";
					}
				}
			}

			if (lineInsert.length() != 0) {
				errorInsert.add("", new ActionMessage("insert"));
				lineInsert = lineInsert.substring(0, lineInsert.length() - 2);
				errorInsert.add("", new ActionMessage("line", lineInsert));
			}
			
			if (lineUpdate.length() != 0) {
				errorInsert.add("", new ActionMessage("update"));
				lineUpdate = lineUpdate.substring(0, lineUpdate.length() - 2);
				errorInsert.add("", new ActionMessage("line", lineUpdate));
			}

			reader.close();
			tx.commit();
			errors.add(errorInsert);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return errors;
	}
}