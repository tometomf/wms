package item.model;

public class Item {
	private int storePrice;
	private int shipmentPrice;
	private String itemCd;
	private String useYn;
	private String manufacturer;
	private String itemGubun;
	private String spec;
	private String itemNm;
	private String unit;
	/**
	 * 
	 */
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param storePice
	 * @param shipmentPrice
	 * @param itemCd
	 * @param useYn
	 * @param manufacturer
	 * @param itemGubun
	 * @param spec
	 * @param itemNm
	 * @param unit
	 */
	public Item(int storePrice, int shipmentPrice, String itemCd, String useYn, String manufacturer, String itemGubun,
			String spec, String itemNm, String unit) {
		super();
		this.shipmentPrice = shipmentPrice;
		this.itemCd = itemCd;
		this.useYn = useYn;
		this.manufacturer = manufacturer;
		this.itemGubun = itemGubun;
		this.spec = spec;
		this.itemNm = itemNm;
		this.unit = unit;
	}
	public int getShipmentPrice() {
		return shipmentPrice;
	}
	public void setShipmentPrice(int shipmentPrice) {
		this.shipmentPrice = shipmentPrice;
	}
	public String getItemCd() {
		return itemCd;
	}
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getItemGubun() {
		return itemGubun;
	}
	public void setItemGubun(String itemGubun) {
		this.itemGubun = itemGubun;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getStorePrice() {
		return storePrice;
	}
	public void setStorePrice(int storePrice) {
		this.storePrice = storePrice;
	}
}
