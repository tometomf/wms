package stock.model;

import java.util.Date;

/**
 * StockPlus 모델 (조인 결과용 DTO)
 * - 재고 + 품목 + 창고 정보를 모두 담는 클래스
 */
public class StockPlus {
    private int stock_No;         // 재고번호 / 在庫番号
    private String item_Cd;       // 품목코드 / 品目コード
    private String item_Nm;       // 품목명 / 品目名
    private String spec;          // 규격 / 規格
    private String item_Gubun;    // 품목구분 / 品目区分
    private String manufacturer;  // 제조사 / メーカー
    private int qty;              // 수량 / 数量
    private String ware_Cd;       // 창고코드 / 倉庫コード
    private String ware_Nm;       // 창고명 / 倉庫名
    private Date reg_Ymd;         // 등록일 / 登録日

    // 기본 생성자 /  基本 コンストラクタ
    public StockPlus() {}

    // 전체 필드를 받는 생성자 / 全フィールドを受けるコンストラクタ
    public StockPlus(int stock_No, String item_Cd, String item_Nm, String spec,
                     String item_Gubun, String manufacturer, int qty,
                     String ware_Cd, String ware_Nm, Date reg_Ymd) {
        this.stock_No = stock_No;
        this.item_Cd = item_Cd;
        this.item_Nm = item_Nm;
        this.spec = spec;
        this.item_Gubun = item_Gubun;
        this.manufacturer = manufacturer;
        this.qty = qty;
        this.ware_Cd = ware_Cd;
        this.ware_Nm = ware_Nm;
        this.reg_Ymd = reg_Ymd;
    }

 // 각 필드에 대한 getter/setter / 各フィールドの getter/setter
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

    public String getItem_Nm() {
        return item_Nm;
    }

    public void setItem_Nm(String item_Nm) {
        this.item_Nm = item_Nm;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getItem_Gubun() {
        return item_Gubun;
    }

    public void setItem_Gubun(String item_Gubun) {
        this.item_Gubun = item_Gubun;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public String getWare_Nm() {
        return ware_Nm;
    }

    public void setWare_Nm(String ware_Nm) {
        this.ware_Nm = ware_Nm;
    }

    public Date getReg_Ymd() {
        return reg_Ymd;
    }

    public void setReg_Ymd(Date reg_Ymd) {
        this.reg_Ymd = reg_Ymd;
    }

    // toString 오버라이드(디버깅용)
    @Override
    public String toString() {
        return "StockPlus [stock_No=" + stock_No +
               ", item_Cd=" + item_Cd +
               ", item_Nm=" + item_Nm +
               ", spec=" + spec +
               ", item_Gubun=" + item_Gubun +
               ", manufacturer=" + manufacturer +
               ", qty=" + qty +
               ", ware_Cd=" + ware_Cd +
               ", ware_Nm=" + ware_Nm +
               ", reg_Ymd=" + reg_Ymd + "]";
    }
}
