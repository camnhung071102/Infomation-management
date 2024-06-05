package fjs.cs.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fjs.cs.dto.MSTMENU;
import fjs.cs.dto.MSTSTAFFMENU;
import fjs.cs.model.SettingMenuBean;

public class SettingMenuDao extends HibernateDaoSupport {

	public List<MSTMENU> getAllByIds(String id) {
		Criteria cr = getSession().createCriteria(MSTSTAFFMENU.class);
		cr.add(Restrictions.eq("userId", id));
		MSTSTAFFMENU staffMenu = (MSTSTAFFMENU) cr.uniqueResult();
		List<MSTMENU> menuList = new ArrayList<>();

		if (staffMenu != null && !staffMenu.getScreenId().isEmpty()) {
			String[] screenIds = staffMenu.getScreenId().split(",");
			for (String screenId : screenIds) {
				Criteria menuCr = getSession().createCriteria(MSTMENU.class);
				menuCr.add(Restrictions.eq("screenId", screenId.trim()));
				MSTMENU menu = (MSTMENU) menuCr.uniqueResult();
				menuList.add(menu);
			}
		}

		return menuList;
	}

	public List<MSTMENU> getAllExceptA(List<MSTMENU> listB) {
		List<String> screenIdsInA = new ArrayList<>();
		for (MSTMENU menu : listB) {
			screenIdsInA.add(menu.getScreenId());
		}
		Criteria cr = getSession().createCriteria(MSTMENU.class);
		cr.add(Restrictions.not(Restrictions.in("screenId", screenIdsInA)));
		List<MSTMENU> otherMenus = cr.list();
		return otherMenus;
	}

	public List<MSTMENU> getAllMenus() {
		Criteria cr = getSession().createCriteria(MSTMENU.class);
		List<MSTMENU> otherMenus = cr.list();
		return otherMenus;
	}

	public void updateById(SettingMenuBean bean) {
		Session session = getSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(MSTSTAFFMENU.class);
		MSTSTAFFMENU screen = (MSTSTAFFMENU) cr.add(Restrictions.eq("userId", bean.getUserID())).uniqueResult();
		if (screen != null) {
			screen.setScreenId(bean.getListSelectB());
			session.update(screen);
		} else {
			MSTSTAFFMENU scr = new MSTSTAFFMENU();
			scr.setUserId(bean.getUserID());
			scr.setScreenId(bean.getListSelectB());
			session.save(scr);
		}
		session.getTransaction().commit();
	}
}
