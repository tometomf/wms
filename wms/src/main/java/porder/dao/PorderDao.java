package porder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import porder.model.Porder;

// 발주데이터를 DB에서 조회/등록/수정/삭제 /  発注データをDBで照会/登録/修正/削除
public class PorderDao {
	
	// 전체 발주 조회 / 全発注一覧を取得
	public List<Porder> select(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// wms_porder(발주테이블)과 wms_item(품목테이블)을 조인하여 전체 발주 목록 조회
			// wms_porder(発注テーブル)とwms_item(品目テーブル)を結合し、全発注一覧を取得
			pstmt = conn.prepareStatement(
				"select rownum no, " // 결과에 행번호 붙임 / 結果に行番号を付与
			  + "purchase_no, purchase_nm, a.item_cd, b.item_nm, qty, "
			  + "purchase_dept, purchase_user, "
			  + "nvl(descr, ' ') as descr, " // 비고가 null이면 공백으로 대체 / 備考がnullなら空白に置き換える
			  + "to_char(reg_ymd, 'yyyy-MM-dd') as reg_ymd " // 등록일을 yyyy-MM-dd 문자열로 변환 / 登録日をyyyy-MM-dd文字列に変換
			  + "from wms_porder a, wms_item b "
			  + "where a.item_cd = b.item_cd" // 품목코드 기준으로 매칭 / 品目コードで結合
			);
			
			rs = pstmt.executeQuery();
			List<Porder> pOrder = new ArrayList<>();
			while (rs.next()) {
				// 조회된 한 줄을 Porder 객체로 변환해서 리스트에 담음
				// 取得した1行をPorderオブジェクトに変換してリストに入れる
				pOrder.add(pOrderConvert(rs));
			}
			return pOrder;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
		
	// 신규 등록 시 다음 발주번호 생성 / 新規登録時に次の発注番号を生成
	public Porder getPurchaseNo(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 가장 큰 발주번호를 찾고 +1 해서 새로운 번호를 만든다
			// 一番大きい発注番号を探し、+1して新しい番号を作る
			pstmt = conn.prepareStatement(
				"select NVL("
			  + "   'PO' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(purchase_no, 3))), 0) + 1), 3, '0'), "
			  + "   'PO001') AS purchase_no "
			  + "from wms_porder"
			);
			rs = pstmt.executeQuery();
			Porder pOrder = null;
			
			if (rs.next()) {
				// 새 번호를 담은 Porder 객체 반환 / 新しい番号を持ったPorderを返す
				pOrder = new Porder(
						null,
						rs.getString("purchase_no"),
						null, null, null, null, null, null, null, null
				);
			}
			return pOrder;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 특정 발주 조회 / 特定の発注を取得
	public Porder selectByPurchaseNo(Connection conn, String purchaseNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(
				"select purchase_no, purchase_nm, a.item_cd, b.item_nm, qty, "
			  + "purchase_dept, purchase_user, "
			  + "nvl(descr, ' ') as descr, "
			  + "to_char(reg_ymd, 'yyyy-MM-dd') as reg_ymd "
			  + "from wms_porder a join wms_item b on a.item_cd = b.item_cd "
			  + "where purchase_no = ?" // 특정 발주번호로 조건 검색 / 特定の発注番号で検索
			);
			pstmt.setString(1, purchaseNo);
			rs = pstmt.executeQuery();
			Porder pOrder = null;
			
			if (rs.next()) {
				// 결과를 Porder 객체로 변환 / 結果をPorderオブジェクトに変換
				pOrder = new Porder(
					null,
					rs.getString("purchase_no"),
					rs.getString("purchase_nm"),
					rs.getString("item_cd"),
					rs.getString("item_nm"),
					rs.getString("qty"),
					rs.getString("purchase_dept"),
					rs.getString("purchase_user"),
					rs.getString("descr"),
					rs.getString("reg_ymd")
				);
			}
			return pOrder;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 발주 등록 / 発注登録
	public void insert(Connection conn, Porder pOrder) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"insert into wms_porder values(?, ?, ?, ?, ?, ?, ?, ?)")
		) {
			// Porder 객체의 값을 DB에 넣음
			// Porderオブジェクトの値をDBに挿入
			pstmt.setString(1, pOrder.getPurchase_No());
			pstmt.setString(2, pOrder.getPurchase_Nm());
			pstmt.setString(3, pOrder.getItem_Cd());
			pstmt.setString(4, pOrder.getQty());
			pstmt.setString(5, pOrder.getPurchase_Dept());
			pstmt.setString(6, pOrder.getPurchase_User());
			pstmt.setString(7, pOrder.getDescr());
			pstmt.setString(8, pOrder.getReg_Ymd());
			pstmt.executeUpdate(); // 실행 / 実行
		}
	}

	// 발주 수정 / 発注修正
	public int update(Connection conn, Porder pOrder) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
				"update wms_porder "
			  + "set purchase_nm = ?, qty = ?, purchase_dept = ?, purchase_user = ?, descr = ?, reg_ymd = ? "
			  + "where purchase_no = ?"
			);
			pstmt.setString(1, pOrder.getPurchase_Nm());
			pstmt.setString(2, pOrder.getQty());
			pstmt.setString(3, pOrder.getPurchase_Dept());
			pstmt.setString(4, pOrder.getPurchase_User());
			pstmt.setString(5, pOrder.getDescr());
			pstmt.setString(6, pOrder.getReg_Ymd());
			pstmt.setString(7, pOrder.getPurchase_No());
			
			return pstmt.executeUpdate(); // 수정된 행 수 반환 / 更新された行数を返す
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	// 발주 삭제 / 発注削除
	public int delete(Connection conn, String purchaseNo) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
				"delete wms_porder where purchase_no = ?"
			);
			pstmt.setString(1, purchaseNo);
			return pstmt.executeUpdate(); // 삭제된 행 수 반환 / 削除された行数を返す
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// ResultSet → Porder 객체 변환 / ResultSet → Porderオブジェクトに変換
	private Porder pOrderConvert(ResultSet rs) throws SQLException {
		return new Porder(
			rs.getString("no"),
			rs.getString("purchase_no"),
			rs.getString("purchase_nm"),
			rs.getString("item_cd"),
			rs.getString("item_nm"),
			rs.getString("qty"),
			rs.getString("purchase_dept"),
			rs.getString("purchase_user"),
			rs.getString("descr"),
			rs.getString("reg_ymd")
		);
	}
}
