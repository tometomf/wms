package stock.model;

import java.util.Date;

/**
 * StockPlus 모델 (조인 결과용 DTO)
 * - 재고 + 품목 + 창고 정보를 모두 담는 클래스
 */
public class StockPlus {
    private String itemCd;         // 품목코드
    private String itemNm;         // 품목명
    private String spec;           // 규격
    private String itemGubun;      // 품목구분
    private String manufacturer;   // 제조사
    private int qty;               // 재고수량
    private String wareCd;         // 창고코드
    private String wareNm;         // 창고명
    private Date regYmd;           // 등록일

    // 기본 생성자
    public StockPlus() {}

    // 전체 필드를 받는 생성자
    public StockPlus(String itemCd, String itemNm, String spec, String itemGubun,
                     String manufacturer, int qty, String wareCd, String wareNm, Date regYmd) {
        this.itemCd = itemCd;
        this.itemNm = itemNm;
        this.spec = spec;
        this.itemGubun = itemGubun;
        this.manufacturer = manufacturer;
        this.qty = qty;
        this.wareCd = wareCd;
        this.wareNm = wareNm;
        this.regYmd = regYmd;
    }

    // getter/setter
    public String getItemCd() { return itemCd; }
    public void setItemCd(String itemCd) { this.itemCd = itemCd; }

    public String getItemNm() { return itemNm; }
    public void setItemNm(String itemNm) { this.itemNm = itemNm; }

    public String getSpec() { return spec; }
    public void setSpec(String spec) { this.spec = spec; }

    public String getItemGubun() { return itemGubun; }
    public void setItemGubun(String itemGubun) { this.itemGubun = itemGubun; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public String getWareCd() { return wareCd; }
    public void setWareCd(String wareCd) { this.wareCd = wareCd; }

    public String getWareNm() { return wareNm; }
    public void setWareNm(String wareNm) { this.wareNm = wareNm; }

    public Date getRegYmd() { return regYmd; }
    public void setRegYmd(Date regYmd) { this.regYmd = regYmd; }

    // toString 오버라이드(디버깅용)
    @Override
    public String toString() {
        return "StockPlus [itemCd=" + itemCd + ", itemNm=" + itemNm + ", spec=" + spec +
               ", itemGubun=" + itemGubun + ", manufacturer=" + manufacturer +
               ", qty=" + qty + ", wareCd=" + wareCd + ", wareNm=" + wareNm +
               ", regYmd=" + regYmd + "]";
    }
}
