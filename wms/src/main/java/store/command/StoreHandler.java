package store.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import store.model.Store;
import store.service.StoreListService;

public class StoreHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
			StoreListService storeListService = new StoreListService();		
			//storeListServiceのインスタンスを作る。
			List<Store> storeList = storeListService.getStoreList();		
			//List<Store>クラスのstoreListにstoreListServiceのstoreListを呼ぶ。
			req.setAttribute("storeList", storeList);
			//jspのstoreListにその結果を入れることを要請する
			return "/WEB-INF/view/storeList.jsp";
			//それをjspに戻す
	}

}
