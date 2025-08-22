package item.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.Item;
import item.service.ItemListService;
import mvc.command.CommandHandler;

public class ItemInsertHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/itemInsert.jsp";
	private ItemListService itemListService = new ItemListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);//GETリクエストの場合、FORM画面を表示
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		Item item = itemListService.getItemCd();
		req.setAttribute("itemCd", item);
		
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Item item = new Item();
		//request parameterを取得し、対応するVOにセットする
		
		item.setItemCd(req.getParameter("itemCd"));
		item.setItemNm(req.getParameter("itemNm"));
		item.setSpec(req.getParameter("spec"));
		item.setItemGubun(req.getParameter("itemGubun"));
		item.setUnit(req.getParameter("unit"));
		item.setUseYn(req.getParameter("useYn"));
		item.setManufacturer(req.getParameter("manufacturer"));
		item.setStorePrice(Integer.parseInt(req.getParameter("storePrice")));
		item.setShipmentPrice(Integer.parseInt(req.getParameter("shipmentPrice")));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

//		if (itemCd == null || itemCd.trim().isEmpty()) {
//      errors.put("itemCd", true); //품목코드 미입력. 品目コード未入力
//	}
//	if (itemNm == null || itemNm.trim().isEmpty()) {
//  errors.put("itemNm", true); //품목명 미입력. 品目名未入力
//}
//	if (spec == null || spec.trim().isEmpty()) {
//  errors.put("spec", true); //규격 미입력. 規格未入力
//}
//	if (itemGubun == null || itemGubun.trim().isEmpty()) {
//  errors.put("itemGubun", true); //분류 미입력. 分類未入力
//}
//	if (unit == null || unit.trim().isEmpty()) {
//  errors.put("unit", true); //단위 미입력. 単位未入力
//}
//	if (useYn == null || useYn.trim().isEmpty()) {
//  errors.put("useYn", true); //사용유무 미입력. 使用有無未入力
//}
//	if (manufacturer == null || manufacturer.trim().isEmpty()) {
//  errors.put("manufacturer", true); //제조사 미입력. メーカー未入力
//}
//	if (storePrice == null || storePrice.trim().isEmpty()) {
//  errors.put("storePrice", true); //수주기준단가 미입력. 受注基準単価未入力
//}
//	if (shipmentPrice == null || shipmentPrice.trim().isEmpty()) {
//  errors.put("shipmentPrice", true); //출고기준단가 미입력. 出庫基準単価未入力
//}
//  if (!errors.isEmpty()) {
//  req.setAttribute("itemCd", insertService.getItemCd());
//  return FORM_VIEW;
//}
		
		try {
			itemListService.insert(item);
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('登録できました。');");
			out.println("location.href='list.do';");
			out.println("</script>");
			out.close();
			return null;
		} catch (DuplicateFormatFlagsException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
}
