package item.model;

public class Item {
	private String itemCd;
	private String itemNm;
	private String spec;
	private String itemGubun;
	private String unit;
	private String useYn;
	private String manufacturer;
	private int storePrice;
	private int shipmentPrice;
	/**
	 * 
	 */
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param itemCd
	 * @param itemNm
	 * @param spec
	 * @param itemGubun
	 * @param unit
	 * @param useYn
	 * @param manufacturer
	 * @param storePrice
	 * @param shipmentPrice
	 */
	public Item(String itemCd, String itemNm, String spec, String itemGubun, String unit, String useYn,
			String manufacturer, int storePrice, int shipmentPrice) {
		super();
		this.itemCd = itemCd;
		this.itemNm = itemNm;
		this.spec = spec;
		this.itemGubun = itemGubun;
		this.unit = unit;
		this.useYn = useYn;
		this.manufacturer = manufacturer;
		this.storePrice = storePrice;
		this.shipmentPrice = shipmentPrice;
	}
	public String getItemCd() {
		return itemCd;
	}
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getItemGubun() {
		return itemGubun;
	}
	public void setItemGubun(String itemGubun) {
		this.itemGubun = itemGubun;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	public int getStorePrice() {
		return storePrice;
	}
	public void setStorePrice(int storePrice) {
		this.storePrice = storePrice;
	}
	public int getShipmentPrice() {
		return shipmentPrice;
	}
	public void setShipmentPrice(int shipmentPrice) {
		this.shipmentPrice = shipmentPrice;
	}
	
}