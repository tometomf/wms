package index.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import index.model.ShipExpect;
import index.model.StoreExpect;
import index.model.WareItem;
import index.service.IndexService;
import item.model.Item;
import item.service.ItemListService;
import mvc.command.CommandHandler;

public class IndexHandler implements CommandHandler {

	IndexService indexService = new IndexService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		List<WareItem> wareItem = indexService.select2();	// 창고별재고현황
		List<StoreExpect> storeExpect = indexService.select3();	// 입고예정목록
		List<ShipExpect> shipExpect = indexService.select4();	// 출고예정목록
		
		req.setAttribute("wareItemList", wareItem);
		req.setAttribute("storeExpectList", storeExpect);
		req.setAttribute("shipExpectList", shipExpect);
        
		return "/index.jsp";
	}
}
