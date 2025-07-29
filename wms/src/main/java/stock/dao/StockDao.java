package stock.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import stock.model.Stock;

public class StockDao {

	public List<Stock> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT stock_no, item_cd, qty, ware_cd, reg_ymd from wms_stock");
			rs = pstmt.executeQuery();
			List<Stock> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertStock(rs));
			}
			return result;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Stock convertStock(ResultSet rs) throws SQLException {

		return new Stock(rs.getInt("stock_no"), 
				rs.getString("item_cd"), 
				rs.getInt("qty"), 
				rs.getString("ware_cd"),
				toDate(rs.getTimestamp("reg_ymd")));
	}

	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
}
