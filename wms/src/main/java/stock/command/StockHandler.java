package stock.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import stock.model.StockPlus;
import stock.service.StockService;
//사용자의 요청을 처리하는 핸들러 / ユーザーのリクエストを処理するハンドラ
public class StockHandler implements CommandHandler {

	private StockService stockService = new StockService(); 	// 서비스 호출 준비 / サービスの呼び出し準備
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		List<StockPlus> stockList = stockService.getSelectAll();	// 서비스에서 재고 데이터 가져오기 / 在庫データを取得する
		req.setAttribute("stockList", stockList);						// JSP에 전달할 데이터 저장 / JSPにデータを渡す
		return "/WEB-INF/view/stockList.jsp";						// 결과 보여줄 JSP 경로 반환 / 結果を表示するJSPパスを返す
	}  

}
