package index.model;

public class StoreExpect {

	public String item_Cd;
	public String item_Nm;
	public String item_Qty;
	public String manufacturer;
	public String store_Price;
	public String reg_Ymd;
	
	public StoreExpect(String item_Cd, String item_Nm, String item_Qty, String manufacturer, String store_Price,
			String reg_Ymd) {
		super();
		this.item_Cd = item_Cd;
		this.item_Nm = item_Nm;
		this.item_Qty = item_Qty;
		this.manufacturer = manufacturer;
		this.store_Price = store_Price;
		this.reg_Ymd = reg_Ymd;
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

	public String getItem_Qty() {
		return item_Qty;
	}

	public void setItem_Qty(String item_Qty) {
		this.item_Qty = item_Qty;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getStore_Price() {
		return store_Price;
	}

	public void setStore_Price(String store_Price) {
		this.store_Price = store_Price;
	}

	public String getReg_Ymd() {
		return reg_Ymd;
	}

	public void setReg_Ymd(String reg_Ymd) {
		this.reg_Ymd = reg_Ymd;
	}
}
