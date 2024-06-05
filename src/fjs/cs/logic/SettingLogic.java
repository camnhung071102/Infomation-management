package fjs.cs.logic;

import java.util.ArrayList;
import java.util.List;

import fjs.cs.dao.SettingDao;

public class SettingLogic {
	private SettingDao settingDao;

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
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