package store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import store.model.Store;

public class StoreDao {

	public List<Store> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;		//ResultSet,Pre... 객체 초기화
		try {
			pstmt = conn.prepareStatement("select*from wms_store_master");
			//wms_store_master에 있는 컬럼을 전부 조회하는 쿼리 준비
			
			rs = pstmt.executeQuery();
			List<Store> result = new ArrayList<>();
			//store 객체 담을 리스트 생성
			//ResultSet에서 다음 행이 있는동안 반복
			while (rs.next()) {				
				//현재 행의 데이터를 store 객체로 변환하여 리스트에 추가
				result.add(convertStore(rs));
			}
			return result;	//조회된 Store 리스트 반환
			} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}

	private Store convertStore(ResultSet rs) throws SQLException {
		return new Store(rs.getInt("store_no"),rs.getString("store_nm"), rs.getString("store_dept"), rs.getString("store_user"),
				rs.getString("store_descr"), toDate(rs.getTimestamp("store_reg_ymd")));
	}
	
	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
}

//주석만 AI돌려서 참고했고, 코드 짜는건 혼자 했습니다.
