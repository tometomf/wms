package ware.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ware.model.Ware;
import ware.service.WareListService;

public class WareHandler implements CommandHandler{

	@Override
	public String process (HttpServletRequest request, HttpServletResponse response) {
		WareListService wareListService = new WareListService();
		
		List<Ware> wareList = wareListService.select();
        request.setAttribute("wareList", wareList);
        return "/WEB-INF/view/wareList.jsp";
	}
}