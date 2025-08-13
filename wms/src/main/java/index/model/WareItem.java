package index.model;

public class WareItem {
	
	public String item_Cd;
	public String item_Nm;
	public String ware_Cd;
	public String ware_Nm;
	public String qty;
	
	public WareItem(String item_Cd, String item_Nm, String ware_Cd, String ware_Nm, String qty) {
		super();
		this.item_Cd = item_Cd;
		this.item_Nm = item_Nm;
		this.ware_Cd = ware_Cd;
		this.ware_Nm = ware_Nm;
		this.qty = qty;
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
	public String getWare_Cd() {
		return ware_Cd;
	}
	public void setWare_Cd(String ware_Cd) {
		this.ware_Cd = ware_Cd;
	}
	public String getWare_Nm() {
		return ware_Nm;
	}
	public void setWare_Nm(String ware_Nm) {
		this.ware_Nm = ware_Nm;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
}
