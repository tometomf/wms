package item.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.Item;
import item.service.ItemListService;
import mvc.command.CommandHandler;

public class ItemHandler implements CommandHandler{

	@Override
	public String process (HttpServletRequest request, HttpServletResponse response) {
		ItemListService itemListService = new ItemListService();
		
		String itemCd = request.getParameter("itemCd");
		
		List<Item> itemList = itemListService.getItemListByItemCd(itemCd);
        request.setAttribute("itemList", itemList); //("name", value)
        request.setAttribute("itemCd", request.getParameter("itemCd"));
        
        return "/WEB-INF/view/itemList.jsp";
	}
}