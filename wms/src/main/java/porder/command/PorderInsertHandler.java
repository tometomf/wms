package porder.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.Item;
import item.service.ItemListService;
import mvc.command.CommandHandler;
import porder.model.Porder;
import porder.service.PorderService;

public class PorderInsertHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/pOrderInsert.jsp";
	private PorderService pOrderService = new PorderService();
	private ItemListService itemService = new ItemListService();

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
	
	// 등록 전 발주 pk 조회
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		Porder purchaseNo = pOrderService.getPurchaseNo();
		List<Item> itemList = itemService.getItemList();
		
		req.setAttribute("purchaseNo", purchaseNo);
		req.setAttribute("itemList", itemList);
		
		return FORM_VIEW;
	}
	
	// 등록 
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Porder pOrder = new Porder();
		
		pOrder.setPurchase_No(req.getParameter("purchase_no"));
		pOrder.setPurchase_Nm(req.getParameter("purchase_nm"));
		pOrder.setItem_Cd(req.getParameter("item_cd"));
		pOrder.setQty(req.getParameter("qty"));
		pOrder.setPurchase_Dept(req.getParameter("purchase_dept"));
		pOrder.setPurchase_User(req.getParameter("purchase_user"));
		pOrder.setDescr(req.getParameter("descr"));
		pOrder.setReg_Ymd(req.getParameter("reg_ymd"));
			
		try {
			pOrderService.insert(pOrder);
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('登録できました。');");
			out.println("location.href='list.do';");
			out.println("</script>");
			out.close();
			return null;
		} catch (DuplicateFormatFlagsException e) {
			return FORM_VIEW;
		}
	}

}
