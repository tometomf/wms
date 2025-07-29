package stock.model;

import java.util.Date;

public class Stock {

	private int stock_No;
	private String item_Cd;
	private int qty;
	private String ware_Cd;
	private Date reg_Ymd;
	
	public Stock(int stock_No, String item_Cd, int qty, String ware_Cd, Date reg_Ymd) {
		super();
		this.stock_No = stock_No;
		this.item_Cd = item_Cd;
		this.qty = qty;
		this.ware_Cd = ware_Cd;
		this.reg_Ymd = reg_Ymd;
	}

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

	

