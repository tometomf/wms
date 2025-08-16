package porder.model;

public class Porder {

	public String no;
	public String purchase_No;
	public String purchase_Nm;
	public String item_Cd;
	public String item_Nm;
	public String qty;
	public String purchase_Dept;
	public String purchase_User;
	public String descr;
	public String reg_Ymd;
	
	public Porder() {
		super();
	}

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
