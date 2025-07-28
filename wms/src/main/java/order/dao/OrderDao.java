package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderDao {

    // 10개 상품의 주문 수량을 조회하는 메서드
    public Map<String, Integer> getOrderQuantities(Connection conn) throws SQLException {
        String sql = "SELECT product_name, SUM(quantity) AS total_quantity " +
                     "FROM orders " +
                     "WHERE product_name IN (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                     "GROUP BY product_name";

        String[] products = {
            "볼트", "육각렌치", "산업용 장갑", "포장박스", "에폭시 본드",
            "포장테이프", "전동드릴", "포장완충재", "목장갑", "절연테이프"
        };

        Map<String, Integer> resultMap = new HashMap<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 10개의 물품명을 PreparedStatement에 바인딩
            for (int i = 0; i < products.length; i++) {
                pstmt.setString(i + 1, products[i]);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String productName = rs.getString("product_name");
                    int quantity = rs.getInt("total_quantity");
                    resultMap.put(productName, quantity);
                }
            }
        }
        
        for (String product : products) {
            resultMap.putIfAbsent(product, 0);
        }

        return resultMap;
    }
}
