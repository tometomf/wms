package ship.model;

import java.util.Date;

public class ShipViewModel {
    // 마스터 정보
    private int shipNo;
    private String shipNm;
    private String shipDept;
    private String shipUser;
    private String descr;
    private Date regYmd;
    private Date updYmd;
    private String shipYn;

    // 상세 정보
    private String itemCd;
    private int shipPrice;
    private int shipQty;
    private String shipGubun;

    // --- getter/setter 전체 ---
    public int getShipNo() { return shipNo; }
    public void setShipNo(int shipNo) { this.shipNo = shipNo; }

    public String getShipNm() { return shipNm; }
    public void setShipNm(String shipNm) { this.shipNm = shipNm; }

    public String getShipDept() { return shipDept; }
    public void setShipDept(String shipDept) { this.shipDept = shipDept; }

    public String getShipUser() { return shipUser; }
    public void setShipUser(String shipUser) { this.shipUser = shipUser; }

    public String getDescr() { return descr; }
    public void setDescr(String descr) { this.descr = descr; }

    public Date getRegYmd() { return regYmd; }
    public void setRegYmd(Date regYmd) { this.regYmd = regYmd; }

    public Date getUpdYmd() { return updYmd; }
    public void setUpdYmd(Date updYmd) { this.updYmd = updYmd; }

    public String getShipYn() { return shipYn; }
    public void setShipYn(String shipYn) { this.shipYn = shipYn; }

    public String getItemCd() { return itemCd; }
    public void setItemCd(String itemCd) { this.itemCd = itemCd; }

    public int getShipPrice() { return shipPrice; }
    public void setShipPrice(int shipPrice) { this.shipPrice = shipPrice; }

    public int getShipQty() { return shipQty; }
    public void setShipQty(int shipQty) { this.shipQty = shipQty; }

    public String getShipGubun() { return shipGubun; }
    public void setShipGubun(String shipGubun) { this.shipGubun = shipGubun; }
}
