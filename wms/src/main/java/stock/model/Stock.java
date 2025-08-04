package stock.model;

import java.util.Date;
//재고 정보를 담는 클래스 (在庫情報を保持するクラス)
public class Stock {

	private int stock_No;				// 재고번호 / 在庫番号
	private String item_Cd;			// 품목코드 / 品目コード
	private int qty;						// 재고수량 / 在庫数量
	private String ware_Cd;			// 창고코드 / 倉庫コード
	private Date reg_Ymd;			// 등록일 / 登録日
	
	// 생성자 / コンストラクタ
	public Stock(int stock_No, String item_Cd, int qty, String ware_Cd, Date reg_Ymd) {
		super();
		this.stock_No = stock_No;
		this.item_Cd = item_Cd;
		this.qty = qty;
		this.ware_Cd = ware_Cd;
		this.reg_Ymd = reg_Ymd;
	}

	// 각 필드에 대한 getter/setter / 各フィールドの getter/setter
	public int getStock_No() {
		return stock_No;
	}
	
	public void setStock_No(int stock_No) {
		this.stock_No = stock_No;
	}

	public String getItem_Cd() {
		return item_Cd;
	}

	public void setItem_Cd(String item_Cd) {
		this.item_Cd = item_Cd;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getWare_Cd() {
		return ware_Cd;
	}

	public void setWare_Cd(String ware_Cd) {
		this.ware_Cd = ware_Cd;
	}

	public Date getReg_Ymd() {
		return reg_Ymd;
	}

	public void setReg_Ymd(Date reg_Ymd) {
		this.reg_Ymd = reg_Ymd;
	}
}

	

