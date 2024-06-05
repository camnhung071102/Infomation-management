package fjs.cs.logic;

import java.util.List;

import fjs.cs.dao.SettingMenuDao;
import fjs.cs.dto.MSTMENU;
import fjs.cs.model.SettingMenuBean;

public class SettingMenuLogic {
	private SettingMenuDao settingMenuDao;

	public void setSettingMenuDao(SettingMenuDao settingMenuDao) {
		this.settingMenuDao = settingMenuDao;
	}

	public List<MSTMENU> getAllByIds(String id) {
		return settingMenuDao.getAllByIds(id);
	}

	public List<MSTMENU> getAllExceptA(List<MSTMENU> listA) {
		return settingMenuDao.getAllExceptA(listA);
	}

	public List<MSTMENU> getAllMenus() {
		return settingMenuDao.getAllMenus();
	}

	public void updateById(SettingMenuBean bean) {
		settingMenuDao.updateById(bean);
	}
}
