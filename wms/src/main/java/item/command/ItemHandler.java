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
		
		List<Item> itemList = itemListService.getItemList();
        request.setAttribute("itemList", itemList);
        return "/WEB-INF/view/itemList.jsp";
	}
}