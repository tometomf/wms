package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderDao {

    /**
     * 지정된 10개 상품의 총 주문 수량을 'orders' 테이블에서 조회합니다.
     * @param conn DB Connection 객체
     * @return 상품명을 Key, 총 수량을 Value로 갖는 Map
     * @throws SQLException
     */
    public Map<String, Integer> getOrderQuantities(Connection conn) throws SQLException {
        // 'orders' 테이블에서 상품명으로 그룹화하여 주문 수량의 합계를 구하는 SQL
        String sql = "SELECT product_name, SUM(quantity) AS total_quantity " +
                     "FROM orders " +
                     "WHERE product_name IN (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                     "GROUP BY product_name";

        // wms_item 데이터 기준의 상품명 배열
        String[] products = {
                "볼트 M8", "육각렌치", "산업용 장갑", "포장박스", "에폭시 본드",
                "포장테이프", "전동드릴", "포장완충재", "목장갑", "절연테이프"
        };

        Map<String, Integer> resultMap = new HashMap<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 10개의 상품명을 PreparedStatement에 바인딩
            for (int i = 0; i < products.length; i++) {
                pstmt.setString(i + 1, products[i]);
            }

            // 쿼리 실행 후 결과(상품명, 수량)를 Map에 저장
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String productName = rs.getString("product_name");
                    int quantity = rs.getInt("total_quantity");
                    resultMap.put(productName, quantity);
                }
            }
        }
        
        // 주문이 없는 상품은 수량을 0으로 설정
        for (String product : products) {
            resultMap.putIfAbsent(product, 0);
        }

        return resultMap;
    }
}