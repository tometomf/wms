package store.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.Item;
import item.service.ItemListService;
import mvc.command.CommandHandler;
import store.model.Store;
import store.service.StoreListService;
import ware.model.Ware;
import ware.service.WareListService;

public class StoreInsertHandler implements CommandHandler {

	// 入庫登録画面のファイル経路。입고등록화면의 파일 경로
	private static final String FORM_VIEW = "/WEB-INF/view/storeInsert.jsp";
	private StoreListService storeListService = new StoreListService();	//入庫一覧サービス　입고일람 서비스
	private ItemListService itemService = new ItemListService();	//品目一覧サービス　품목 일람 서비스
	private WareListService wareService = new WareListService();	//倉庫一覧サービス　창고 일람 서비스

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// HTTPメソッドで分岐（GET=フォーム表示, POST=登録）http 메소드로 분기 (get=폼 표시, post=등록)
		if (req.getMethod().equalsIgnoreCase("GET")) {
			// 入庫登録フォームの画面を表す。 입고 등록 화면을 표시
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			// フォームからのデータをDBにセーブ。폼에 입력된 값을 DB에 저장
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	// フォーム表示：次の入庫番号と品目一覧を用意 폼 표시:다음 입고 번호와 품목일람을 준비시킴.
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String nextStoreNo = storeListService.getNewStoreNo();
		List<Item> itemList = itemService.getItemList();
		List<Ware> wareList = wareService.select();

		// 新しいstore_noを獲得するメソッドを呼び出す。 새로운 store_no를 획득하는 메소드 호출
		req.setAttribute("store_no", nextStoreNo);
		req.setAttribute("itemList", itemList);
		req.setAttribute("wareList", wareList);

		return FORM_VIEW;
	}

	// Post要請をパラメータで処理するメソッド 포스트 요청을 파라미터로 처리하는 메소드
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Store store = new Store();

		store.setStore_no(req.getParameter("store_no"));
		store.setStore_nm(req.getParameter("store_nm"));
		store.setItem_cd(req.getParameter("item_cd"));
		store.setQty(Integer.parseInt(req.getParameter("qty")));
		store.setStore_price(Integer.parseInt(req.getParameter("store_price")));
		store.setWare_cd(req.getParameter("ware_cd"));
		store.setStore_dept(req.getParameter("store_dept"));
		store.setStore_user(req.getParameter("store_user"));
		store.setDescr(req.getParameter("descr"));
		store.setReg_ymd(req.getParameter("reg_ymd"));

		try {
			storeListService.insert(store); //登録実行 등록 실행
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('登録できました。');");	//成功メッセージ 성공 메세지
			out.println("location.href='list.do';");	// 一覧へ移動 일람페이지로 이동
			out.println("</script>");
			out.close();
			return null;
		} catch (DuplicateFormatFlagsException e) {	// 失敗時：フォーム最表示 실패 시, 폼 재표시
			return FORM_VIEW;
		}
	}
}