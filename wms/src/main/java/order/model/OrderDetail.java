package order.model;

import java.util.Date;

public class OrderDetail {
    private String itemCd;
    private int orderPrice;
    private Date orderYmd;

    public OrderDetail(String itemCd, int orderPrice, Date orderYmd) {
        this.itemCd = itemCd;
        this.orderPrice = orderPrice;
        this.orderYmd = orderYmd;
    }
    
	public String getItemCd() {
		return itemCd;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public Date getOrderYmd() {
		return orderYmd;
	}
    
    

}