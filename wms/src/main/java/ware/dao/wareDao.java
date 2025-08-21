package ware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import ware.model.Ware;
import jdbc.JdbcUtil;

public class wareDao {
	
	// 전체 조회
	public List<Ware> select(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select rownum no, ware_cd, ware_nm, ware_gubun, nvl(descr, ' ') as descr, case when use_yn = 'Y' then '利用あり' else '利用なし' end as use_yn "
					+ "from wms_ware order by ware_cd");
			rs = pstmt.executeQuery();
			List<Ware> wareList = new ArrayList<>();
			while (rs.next()) {
				wareList.add(makeWareFromResultSet(rs));
			}
			return wareList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
		
	// 신규 등록 시 창고 코드 조회
	public Ware selectWareCd(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select NVL('W' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(ware_cd, 2))), 0) + 1), 3, '0'), 'W001') AS next_ware_cd from wms_ware");
			rs = pstmt.executeQuery();
			Ware ware = null;
			
			if (rs.next()) {
				ware = new Ware(
						null
					  ,	rs.getString("next_ware_cd")
					  , null
					  , null
					  , null
					  , null);
			}
			return ware;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 창고 마스터 조회
	public Ware selectByWareCd(Connection conn, String wareCd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT WARE_CD, WARE_NM, ware_gubun, USE_YN, DESCR FROM WMS_WARE WHERE WARE_CD = ?");
			pstmt.setString(1, wareCd);
			rs = pstmt.executeQuery();
			Ware ware = null;
			
			if (rs.next()) {
				ware = new Ware(
					    null
				      , rs.getString("ware_cd")
					  , rs.getString("ware_nm")
					  , rs.getString("ware_gubun")
					  , rs.getString("use_yn")
					  , rs.getString("descr"));
			}
			return ware;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 창고 마스터 등록
	public void insert(Connection conn, Ware ware) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into wms_ware values(?, ?, ?, ?, ?)")) {
			pstmt.setString(1, ware.getWareCd());
			pstmt.setString(2, ware.getWareNm());
			pstmt.setString(3, ware.getWareGubun());
			pstmt.setString(4, ware.getDescr());
			pstmt.setString(5, ware.getUseYn());
			pstmt.executeUpdate();
		}
	}

	// 창고 마스터 수정
	public int update(Connection conn, Ware ware) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update wms_ware set ware_nm=?, ware_gubun=?, use_yn=?, descr = ? where ware_cd = ?");
			pstmt.setString(1, ware.getWareNm());
			pstmt.setString(2, ware.getWareGubun());
			pstmt.setString(3, ware.getUseYn());
			pstmt.setString(4, ware.getDescr());
			pstmt.setString(5, ware.getWareCd());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	// 창고 마스터 삭제
	public int delete(Connection conn, String wareCd) throws SQLException {
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete wms_ware where ware_cd=?");
			pstmt.setString(1, wareCd);
			return pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException  e) {
            // 예외 발생 시 처리 로직
            // 예: 사용자에게 메시지 표시, 로깅 등
            // throw new RuntimeException("데이터 삭제 중 오류가 발생했습니다.", e); // 예외를 다시 던져서 상위 서비스에서 처리
            return 0;
        } finally {
			JdbcUtil.close(pstmt);
		}
	}

	// convert
	private Ware makeWareFromResultSet(ResultSet rs) throws SQLException {
		return new Ware(rs.getString("no"), rs.getString("ware_cd"), rs.getString("ware_nm"), rs.getString("ware_gubun"),
				rs.getString("use_yn"), rs.getString("descr"));
	}
}
