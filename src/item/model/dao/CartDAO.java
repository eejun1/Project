package item.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import item.dto.CartDTO;
import util.DatabaseUtility;

public class CartDAO {
	// 싱글톤 패턴

	private static CartDAO instance;
	private DatabaseUtility databaseUtility;
	private BasicDataSource dataSource;

	private CartDAO() {
		databaseUtility = DatabaseUtility.getInstance();
		dataSource = databaseUtility.getDataSource();
	}

	public static CartDAO getInstance() {
		if (instance == null) {
			instance = new CartDAO();
		}
		return instance;
	}

	// cart 테이블 추가
	public void addToCart(CartDTO cartDTO) throws SQLException {
		String sql = "INSERT INTO mydb.cart (user_userseq, item_itemseq, itemquantity) VALUES (?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cartDTO.getUser_userSeq());
			preparedStatement.setString(2, cartDTO.getItem_itemSeq());
			preparedStatement.setString(3, cartDTO.getItemQuantity());
			preparedStatement.executeUpdate();
		} finally {
			databaseUtility.close(preparedStatement, connection);
		}
	}

	// 아이템 존재 여부 확인
	public CartDTO checkDuplicatedItem(String itemSeq, String userSeq) throws SQLException {
		CartDTO cartDTO = null;

		String sql = "select * from mydb.cart where user_userseq = ? and item_itemseq = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userSeq);
			preparedStatement.setString(2, itemSeq);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				cartDTO = new CartDTO(itemSeq, userSeq, "0");
			}

		} finally {
			DatabaseUtility.close(resultSet, preparedStatement, connection);
		}

		return cartDTO;

	}
}
