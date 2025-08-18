package porder.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import porder.model.Porder;
import porder.service.PorderService;
import ware.model.Ware;
import ware.service.WareListService;

public class PorderHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/view/pOrderList.jsp";
	private PorderService pOrderService = new PorderService();
	
	@Override
	public String process (HttpServletRequest request, HttpServletResponse response) {
		List<Porder> pOrderList = pOrderService.select();
		
        request.setAttribute("pOrderList", pOrderList);
        return FORM_VIEW;
	}
}