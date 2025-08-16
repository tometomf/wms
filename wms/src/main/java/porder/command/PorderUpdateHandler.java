package porder.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemListService;
import mvc.command.CommandHandler;
import porder.model.Porder;
import porder.service.PorderService;
import ware.model.Ware;
import ware.service.WareListService;

public class PorderUpdateHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/wareUpdate.jsp";
	private PorderService pOrderService = new PorderService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		
		String purchaseNo = req.getParameter("purchase_No");  // 여기서 파라미터 읽음
		Porder pOrder = pOrderService.selectByPurchaseNo(purchaseNo);
		
		req.setAttribute("pOrder", pOrder);
		
		return "/WEB-INF/view/pOrderUpdate.jsp";
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Porder pOrder = new Porder();
		
		pOrder.setPurchase_No(req.getParameter("purchase_no"));
		pOrder.setPurchase_Nm(req.getParameter("purchase_nm"));
		pOrder.setQty(req.getParameter("qty"));
		pOrder.setPurchase_Dept(req.getParameter("purchase_dept"));
		pOrder.setPurchase_User(req.getParameter("purchase_user"));
		pOrder.setDescr(req.getParameter("descr"));
		
		try {
			pOrderService.update(pOrder);
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('수정이 완료되었습니다.');");
			out.println("location.href='list.do';");
			out.println("</script>");
			out.close();
			return null;
		} catch (DuplicateFormatFlagsException e) {
			return FORM_VIEW;
		}
	}
}
