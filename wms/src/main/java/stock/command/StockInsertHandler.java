package stock.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import stock.model.Stock;
import stock.service.StockInsertService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StockInsertHandler implements CommandHandler {

    private StockInsertService insertService = new StockInsertService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // POST 방식으로 폼 데이터가 넘어왔는지 확인
        if (request.getMethod().equalsIgnoreCase("POST")) {

            // 1. 폼에서 보낸 값 꺼내기
            String itemCd = request.getParameter("itemCd");
            int qty = Integer.parseInt(request.getParameter("qty"));
            String wareCd = request.getParameter("wareCd");
            String regYmdStr = request.getParameter("regYmd");

            // 2. 등록일을 Date 타입으로 변환
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date regYmd = sdf.parse(regYmdStr);

            // 3. Stock 객체 생성 (stock_No는 DB에서 자동 생성된다고 가정)
            Stock stock = new Stock(0, itemCd, qty, wareCd, regYmd);

            // 4. insertService를 호출해서 DB에 저장
            boolean result = insertService.insert(stock);

            // 5. 성공/실패 메시지 저장
            String msg = result ? "재고가 등록되었습니다." : "등록에 실패했습니다.";
            request.setAttribute("msg", msg);

            // 6. 결과 페이지로 이동
            return "/WEB-INF/view/stockInsertResult.jsp";

        } else {
            // GET 요청이면 입력 폼을 보여줌
            return "/WEB-INF/view/stockForm.jsp";
        }
    }
}
