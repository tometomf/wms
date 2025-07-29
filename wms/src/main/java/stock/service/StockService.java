package stock.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import stock.dao.StockDao;
import stock.model.Stock;

public class StockService {

	private StockDao stockDao = new StockDao();

	public StockList getSelectAll() {

		List<Stock> content = null;
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			content = stockDao.selectAll(conn);
			return new StockList(content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}