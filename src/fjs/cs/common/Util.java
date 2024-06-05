package fjs.cs.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	public Util() {

	}

	/**
	 * HAM KIEM TRA DINH DANG NGAY.
	 * 
	 * @param dateStr
	 * @return
	 */
	public boolean checkDate(String dateStr) {
		if (dateStr.isEmpty()) {
			return true;
		}
		String regex = "^\\d{4}/\\d{2}/\\d{2}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(dateStr);
		return matcher.matches();
	}

	/**
	 * Kiem tra dinh dang email.
	 * 
	 * @param email
	 * @return
	 */
	public boolean checkEmail(String email) {
		if (email.isEmpty()) {
			return true;
		}
		String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
		return email.matches(emailRegex);
	}

	public List<String> listHeader(String listHeader) {
		List<String> list = new ArrayList<String>();
		if (!listHeader.isEmpty()) {
			listHeader = listHeader.replace("[", "").replace("]", "").replaceAll(" ", "");
			String listSplit[] = listHeader.split(",");
			for (int i = 0; i < listSplit.length; i++) {
				list.add(listSplit[i]);
			}
		}
		return list;
	}
}