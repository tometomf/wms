package stock.service;

import java.util.List;

import stock.model.Stock;

public class StockList {

	private List<Stock> stock;

	public StockList(List<Stock> stock) {
		super();
		this.stock = stock;
	}

	public List<Stock> getStock() {
		return stock;
	}
}
