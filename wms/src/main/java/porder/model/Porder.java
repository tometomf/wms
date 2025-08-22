package porder.model;

// 발주정보를 담는 클래스 (DB 한 행 = 객체 1개)
// 発注情報を持つクラス (DBの1行 = オブジェクト1つ)
public class Porder {

	// 필드(멤버 변수) / フィールド（メンバ変数）
	public String no; // 행 번호 / 行番号
	public String purchase_No; // 발주 번호 / 発注番号
	public String purchase_Nm; // 발주 명 / 発注名
	public String item_Cd; // 품목 코드 / 品目コード
	public String item_Nm; // 품목 명 / 品目名
	public String qty; // 수량 / 数量
	public String purchase_Dept; // 발주 부서 / 発注部署
	public String purchase_User; // 발주 담당자 / 発注担当者
	public String descr; // 비고 / 備考
	public String reg_Ymd; // 등록일 / 登録日

	// 기본 생성자 / デフォルトコンストラクタ
	public Porder() {
		super();
	}

	// 전체 필드를 채우는 생성자 / 全フィールドを埋めるコンストラクタ
	public Porder(String no, String purchase_No, String purchase_Nm, String item_Cd, String item_Nm, String qty,
			String purchase_Dept, String purchase_User, String descr, String reg_Ymd) {
		this.no = no;
		this.purchase_No = purchase_No;
		this.purchase_Nm = purchase_Nm;
		this.item_Cd = item_Cd;
		this.item_Nm = item_Nm;
		this.qty = qty;
		this.purchase_Dept = purchase_Dept;
		this.purchase_User = purchase_User;
		this.descr = descr;
		this.reg_Ymd = reg_Ymd;
	}

	// getter & setter 메서드 / getter & setter メソッド
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getPurchase_No() {
		return purchase_No;
	}

	public void setPurchase_No(String purchase_No) {
		this.purchase_No = purchase_No;
	}

	public String getPurchase_Nm() {
		return purchase_Nm;
	}

	public void setPurchase_Nm(String purchase_Nm) {
		this.purchase_Nm = purchase_Nm;
	}

	public String getItem_Cd() {
		return item_Cd;
	}

	public void setItem_Cd(String item_Cd) {
		this.item_Cd = item_Cd;
	}

	public String getItem_Nm() {
		return item_Nm;
	}

	public void setItem_Nm(String item_Nm) {
		this.item_Nm = item_Nm;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getPurchase_Dept() {
		return purchase_Dept;
	}

	public void setPurchase_Dept(String purchase_Dept) {
		this.purchase_Dept = purchase_Dept;
	}

	public String getPurchase_User() {
		return purchase_User;
	}

	public void setPurchase_User(String purchase_User) {
		this.purchase_User = purchase_User;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getReg_Ymd() {
		return reg_Ymd;
	}

	public void setReg_Ymd(String reg_Ymd) {
		this.reg_Ymd = reg_Ymd;
	}
}
