package todo.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO extends DAO {

	/*商品コードで検索*/
	public List<Todo> select(String shouhin_id) throws Exception{
		
		Connection connection = getConnection();
		
		String sql = "SELECT * FROM shohin WHERE shouhin_id like CONCAT('%',?,'%')";
		
		PreparedStatement statment = connection.prepareStatement(sql);
		
		statment.setString(1, shouhin_id);
		
		ResultSet rs = statment.executeQuery();
		
		List<Todo> resultList = new ArrayList<>();
		while(rs.next()) {
			
			Todo td = new Todo();
			td.setShouhin_id(rs.getInt("shouhin_id"));
			td.setShohin_coode(rs.getString("shohin_coode"));
			td.setShohin_lot(rs.getString("shohin_lot"));
			td.setShohin_mei(rs.getString("shohin_mei"));
			td.setShohin_bunrui(rs.getString("shohin_bunrui"));
			td.setShouhin_quantity(rs.getInt("shouhin_quantity"));
			td.setSerial_number(rs.getInt("serial_number"));
			td.setTorokubi(rs.getString("torokubi"));
			
			resultList.add(td);			
		}
		return resultList;		
	}
	
	
	/* todo_listテーブルの全データを取得する */
	public List<Todo> todoList() throws Exception {
		List<Todo> returnList = new ArrayList<Todo>();

		String sql = "SELECT shouhin_id,shohin_coode , shohin_lot , shohin_mei , shohin_bunrui , shouhin_quantity , serial_number , torokubi  ORDER BY shouhin_id";

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);
		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();
		// 検索結果の行数分フェッチを行い、取得結果をTodoクラスのインスタンスdtoのリストに格納する
		while (rs.next()) {
			Todo dto = new Todo();
			dto.setShouhin_id(rs.getInt("shouhin_id"));
			dto.setShohin_coode(rs.getString("shohin_coode"));
			dto.setShohin_lot(rs.getString("shohin_lot"));
			dto.setShohin_mei(rs.getString("shohin_mei"));
			dto.setShohin_bunrui(rs.getString("shohin_bunrui"));
			dto.setShouhin_quantity(rs.getInt("shouhin_quantity"));
			dto.setSerial_number(rs.getInt("serial_number"));
			dto.setTorokubi(rs.getString("torokubi"));
			returnList.add(dto);
		}
		return returnList;
	}

	/* 指定されたタスク番号のタスクデータを取得する */
	public Todo detail(int shouhin_id) throws Exception {
		String sql = "SELECT shouhin_id,shohin_coode , shohin_lot , shohin_mei , shohin_bunrui , shouhin_quantity , serial_number , torokubi   where shouhin_id = ?";

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);
		statement.setInt(1, shouhin_id);
		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();
		// 検索結果をTodoクラスのインスタンスdtoへ格納する
		Todo dto = new Todo();
		while (rs.next()) {
			// クエリー結果をDTOへ格納
			dto.setShouhin_id(rs.getInt("shouhin_id"));
			dto.setShohin_coode(rs.getString("shohin_coode"));
			dto.setShohin_lot(rs.getString("shohin_lot"));
			dto.setShohin_mei(rs.getString("shohin_mei"));
			dto.setShohin_bunrui(rs.getString("shohin_bunrui"));
			dto.setShouhin_quantity(rs.getInt("shouhin_quantity"));
			dto.setSerial_number(rs.getInt("serial_number"));
			dto.setTorokubi(rs.getString("torokubi"));
		}
		return dto;
	}

	/* 指定されたタスク番号のタスクを削除する */
	public int delete(int shouhin_id) throws Exception {
		String sql = "DELETE FROM shohin where shouhin_id = ?";

		// SQLを実行してその結果を取得する
		int result = 0;
		try {
			// プリペアステートメントを取得し、実行SQLを渡す
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setInt(1, shouhin_id);
			result = statement.executeUpdate();
			// コミットを行う
			super.commit();
		} catch (Exception e) {
			// 例外の場合ロールバックを行いスローした例外はデータオブジェクトから脱出する
			super.rollback();
			throw e;
		}
		return result;
	}

	/* すべてのタスクを削除する */
	public int deleteAll() throws Exception {
		String sql = "DELETE FROM shohin";

		// SQLを実行してその結果を取得する
		int result = 0;
		try {
			// プリペアステートメントを取得し、実行SQLを渡す
			PreparedStatement statement = getPreparedStatement(sql);
			result = statement.executeUpdate();
			// コミットを行う
			super.commit();
		} catch (Exception e) {
			// 例外の場合ロールバックを行い、スローした例外はデータオブジェクトから脱出する
			super.rollback();
			throw e;
		}
		return result;
	}
	
	/* タスクを新規登録処理する */
	public int registerInsert(Todo dto) throws Exception {
		String sql = "INSERT INTO shohin (shouhin_id,shohin_coode,shohin_lot,shohin_mei,shohin_bunrui,shouhin_quantity,serial_number,torokubi) VALUES (?,?,?,?,?,?,?,?)";

		// SQLを実行してその結果を取得する
		int result = 0;
		try {
			// プリペアステートメントを取得し、実行SQLを渡す
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setInt(1, dto.getShouhin_id());
			statement.setString(2, dto.getShohin_coode());
			statement.setString(3, dto.getShohin_lot());
			statement.setString(4, dto.getShohin_mei());
			statement.setString(5, dto.getShohin_bunrui());
			statement.setInt(6, dto.getShouhin_quantity());
			statement.setInt(7, dto.getSerial_number());
			statement.setString(8, dto.getTorokubi());
			result = statement.executeUpdate();
			// コミットを行う
			super.commit();
		} catch (Exception e) {
			// 例外の場合ロールバックを行いスローした例外はデータオブジェクトから脱出する
			super.rollback();
			throw e;
		}
		return result;
	}

	/* 指定されたタスク番号のタスクを更新する */
	public int registerUpdate(Todo dto) throws Exception {
		String sql = "UPDATE shohin SET shouhin_id = ? , shohin_coode = ? , shohin_lot = ? , shohin_mei= ?  , shohin_bunrui = ? , shouhin_quantity = ? ,serial_numbe = ?,torokubi = ?  WHERE shouhin_id = ?";

		// SQLを実行してその結果を取得する
		int result = 0;
		try {
			// プリペアステートメントを取得し、実行SQLを渡す
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setString(2, dto.getShohin_coode());
			statement.setString(3, dto.getShohin_lot());
			statement.setString(4, dto.getShohin_mei());
			statement.setString(5, dto.getShohin_bunrui());
			statement.setInt(6, dto.getShouhin_quantity());
			statement.setInt(7, dto.getSerial_number());
			statement.setString(8, dto.getTorokubi());
			result = statement.executeUpdate();
			// コミットを行う
			super.commit();
		} catch (Exception e) {
			// 例外の場合ロールバックを行いスローした例外はデータオブジェクトから脱出する
			super.rollback();
			throw e;
		}
		return result;
	}
	
}