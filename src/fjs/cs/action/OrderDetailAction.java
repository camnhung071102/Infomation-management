package fjs.cs.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import fjs.cs.common.Constants;
import fjs.cs.dto.MSTORDERDETAIL;
import fjs.cs.logic.OrderDetailLogic;
import fjs.cs.model.OrderDetailBean;

public class OrderDetailAction extends ActionSupport {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		OrderDetailBean bean = (OrderDetailBean) form;
		OrderDetailLogic orderDetailLogic = (OrderDetailLogic) getWebApplicationContext().getBean("orderDetailLogic");
		List<MSTORDERDETAIL> listOrder = new ArrayList<MSTORDERDETAIL>();
		
		if (Constants.LOGOUT.equals(bean.getMode())) {
			session.invalidate();
			return (mapping.findForward(Constants.LOGIN));
		}
		
		if (Constants.CANCEL.equals(bean.getMode())) {
			return (mapping.findForward(Constants.SEARCHORDER));
		}
		
		listOrder = orderDetailLogic.getOrders(bean);
		req.setAttribute("order", listOrder);
		req.setAttribute("orderId", bean.getOrderId());
		return (mapping.findForward(Constants.ORDERDETAIL));
	}

}
