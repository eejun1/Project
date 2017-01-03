package item.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import item.dto.ItemDTO;
import util.DatabaseUtility;

public class ItemDAO {
	// 싱글톤 패턴

	private static ItemDAO instance;
	private DatabaseUtility databaseUtility;
	private BasicDataSource dataSource;

	private ItemDAO() {
		databaseUtility = DatabaseUtility.getInstance();
		dataSource = databaseUtility.getDataSource();
	}

	public static ItemDAO getInstance() {
		if (instance == null) {
			instance = new ItemDAO();
		}
		return instance;
	}

	// 전체 목록 조회
	public ArrayList<ItemDTO> selectAllItem() throws SQLException {
		ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
		String sql = "select * from mydb.item";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				itemList.add(new ItemDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9)));
			}
		} finally {
			DatabaseUtility.close(resultSet, preparedStatement, connection);
		}
		return itemList;
	}

	// 특정 상품 조회
	public ItemDTO selectItemByItemSeq(String itemSeq) throws SQLException {
		ItemDTO item = null;
		String sql = "select * from mydb.item where itemseq = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, itemSeq);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				item = new ItemDTO(itemSeq, resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9));
			}
		} finally {
			DatabaseUtility.close(resultSet, preparedStatement, connection);
		}

		return item;

	}

	// 상품 등록
	public void insertItem(ItemDTO itemDTO) throws SQLException {
		String sql = "INSERT INTO mydb.item (itemname, itemprice, itemcategory, user_userseq, itemquantity, itemtitlesrc, itemcontent, itemsrc) VALUES (?,?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, itemDTO.getItemName());
			preparedStatement.setString(2, itemDTO.getItemPrice());
			preparedStatement.setString(3, itemDTO.getItemCategory());
			preparedStatement.setString(4, itemDTO.getUser_UserSeq());
			preparedStatement.setString(5, itemDTO.getItemQuantity());
			preparedStatement.setString(6, itemDTO.getItemTitleSrc());
			preparedStatement.setString(7, itemDTO.getItemContent());
			preparedStatement.setString(8, itemDTO.getItemSrc());
			preparedStatement.executeUpdate();
		} finally {
			databaseUtility.close(preparedStatement, connection);
		}
	}

	// 상품 수정
	public void updateItemByItemSeq(ItemDTO itemDTO) throws SQLException {
		String sql = "update mydb.item set itemname = ?, itemprice = ?, itemcategory = ?, itemquantity = ?, itemtitlesrc = ?, itemcontent = ?, itemsrc = ? where itemseq = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, itemDTO.getItemName());
			preparedStatement.setString(2, itemDTO.getItemPrice());
			preparedStatement.setString(3, itemDTO.getItemCategory());
			preparedStatement.setString(4, itemDTO.getItemQuantity());
			preparedStatement.setString(5, itemDTO.getItemTitleSrc());
			preparedStatement.setString(6, itemDTO.getItemContent());
			preparedStatement.setString(7, itemDTO.getItemSrc());
			preparedStatement.setString(8, itemDTO.getItemSeq());

			preparedStatement.executeUpdate();
		} finally {
			databaseUtility.close(preparedStatement, connection);
		}
	}

	// 상품 삭제
	public void deleteItemByItemSeq(String itemSeq) throws SQLException {
		String sql = "delete from mydb.item where itemseq = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, itemSeq);

			preparedStatement.executeUpdate();

		} finally {

			databaseUtility.close(preparedStatement, connection);
		}

	}
}
