package stock.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import stock.service.StockService;


//재고 삭제 요청을 처리하는 핸들러 / 在庫削除リクエストを処理するハンドラ
public class StockDeleteHandler implements CommandHandler {
	// StockService 객체 생성 / StockServiceオブジェクト生成
	private StockService stockService = new StockService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		 // 요청 파라미터에서 재고번호(stockNo) 가져오기 / リクエストパラメータから在庫番号(stockNo)を取得
		String stock = req.getParameter("stockNo");
		
		 // Service 호출 → 재고 삭제 실행 / サービス呼び出し → 在庫削除実行
		stockService.delete(stock);
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('削除できました。');");  //삭제완료 알림 / 削除完了のお知らせ
		out.println("location.href='list.do';");  // 목록 페이지로 이동 / リストページへ移動
		out.println("</script>");
		out.close();
		return null;  // 뷰(JSP)로 이동하지 않음 / JSPビューには移動しない
	}
}
