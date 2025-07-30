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
			StoreListService storeListService = new StoreListService();		//storeListService 객체를 만들고,
			
			List<Store> storeList = storeListService.getStoreList();		
			//List<Store> 클래스인 storeList에 storeListService의 스토어리스트를 호출한다.
			request.setAttribute("storeList", storeList);
			//요청한다. jsp의 storeList에 여기 있는 값을 집어넣는다.
			return null "/WEB-INF/view/StoreList.jsp";
			//jsp로 돌려보낸다.
	}

}
