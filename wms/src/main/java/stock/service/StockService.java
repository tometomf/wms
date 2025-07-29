package stock.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import stock.dao.StockDao;
import stock.model.Stock;

public class StockService {

	private StockDao stockDao = new StockDao();

	public List<Stock> getSelectAll() {

		List<Stock> content = null;
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			return stockDao.selectAll(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}