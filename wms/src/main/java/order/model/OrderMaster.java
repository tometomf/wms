package order.model;

import java.util.Date;
import java.util.List;

public class OrderMaster {
    private int orderNo;
    private String orderNm;
    private String orderDept;
    private String orderUser;
    private String orderGubun;
    private String descr;
    private Date regYmd;
    private Date updYmd;
    private String storeYn;
    private List<OrderDetail> detailList;

    public OrderMaster(int orderNo, String orderNm, String orderDept, String orderUser, String orderGubun,
                       String descr, Date regYmd, Date updYmd, String storeYn, List<OrderDetail> detailList) {
        this.orderNo = orderNo;
        this.orderNm = orderNm;
        this.orderDept = orderDept;
        this.orderUser = orderUser;
        this.orderGubun = orderGubun;
        this.descr = descr;
        this.regYmd = regYmd;
        this.updYmd = updYmd;
        this.storeYn = storeYn;
        this.detailList = detailList;
    }

	public int getOrderNo() {
		return orderNo;
	}

	public String getOrderNm() {
		return orderNm;
	}

	public String getOrderDept() {
		return orderDept;
	}

	public String getOrderUser() {
		return orderUser;
	}

	public String getOrderGubun() {
		return orderGubun;
	}

	public String getDescr() {
		return descr;
	}

	public Date getRegYmd() {
		return regYmd;
	}

	public Date getUpdYmd() {
		return updYmd;
	}

	public String getStoreYn() {
		return storeYn;
	}

	public List<OrderDetail> getDetailList() {
		return detailList;
	}
    
    
}