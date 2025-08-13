package index.model;

public class ShipExpect {

	public String item_Cd;
	public String item_Nm;
	public String ship_Qty;
	public String manufacturer;
	public String ship_Price;
	public String reg_Ymd;
	
	public ShipExpect(String item_Cd, String item_Nm, String ship_Qty, String manufacturer, String ship_Price,
			String reg_Ymd) {
		super();
		this.item_Cd = item_Cd;
		this.item_Nm = item_Nm;
		this.ship_Qty = ship_Qty;
		this.manufacturer = manufacturer;
		this.ship_Price = ship_Price;
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

	public String getShip_Qty() {
		return ship_Qty;
	}

	public void setShip_Qty(String ship_Qty) {
		this.ship_Qty = ship_Qty;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getShip_Price() {
		return ship_Price;
	}

	public void setShip_Price(String ship_Price) {
		this.ship_Price = ship_Price;
	}

	public String getReg_Ymd() {
		return reg_Ymd;
	}

	public void setReg_Ymd(String reg_Ymd) {
		this.reg_Ymd = reg_Ymd;
	}
}
