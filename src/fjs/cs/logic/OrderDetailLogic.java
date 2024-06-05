package fjs.cs.logic;

import java.sql.SQLException;
import java.util.List;

import fjs.cs.common.Util;
import fjs.cs.dao.OrderDetailDao;
import fjs.cs.dto.MSTORDERDETAIL;
import fjs.cs.model.OrderDetailBean;


public class OrderDetailLogic 	{
	Util utils = new Util();
	private OrderDetailDao orderDetailDao;

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	
	public List<MSTORDERDETAIL> getOrders(OrderDetailBean bean) throws SQLException {
		return orderDetailDao.getOrders(bean);
	}

}
