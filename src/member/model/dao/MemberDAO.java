package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import member.dto.MemberDTO;
import util.DatabaseUtility;

import org.apache.commons.dbcp2.BasicDataSource;

public class MemberDAO {
	// 싱글톤 패턴
	private static MemberDAO instance;
	private DatabaseUtility databaseUtility;
	private BasicDataSource dataSource;

	private MemberDAO() {
		databaseUtility = DatabaseUtility.getInstance();
		dataSource = databaseUtility.getDataSource();
	}

	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
		}

		return instance;
	}

	public MemberDTO selectMemberByEmail(String email) throws SQLException {
		MemberDTO member = null;

		String sql = "select * from mydb.user where email = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				member = new MemberDTO(resultSet.getString(1), email, resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7));
				System.out.println(resultSet.getString(1));
			}
		} finally {
			DatabaseUtility.close(resultSet, preparedStatement, connection);
		}

		return member;

	}

	public MemberDTO selectMemberByUserName(String userName) throws SQLException {
		MemberDTO member = null;

		String sql = "select * from mydb.user where username = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				member = new MemberDTO(resultSet.getString(1), resultSet.getString(2), userName,
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7));
			}
		} finally {
			DatabaseUtility.close(resultSet, preparedStatement, connection);
		}

		return member;

	}

	public void insertMember(MemberDTO memberDTO) throws SQLException {
		String sql = "INSERT INTO mydb.user (email, username, userpw, address, phonenumber) VALUES (?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getEmail());
			preparedStatement.setString(2, memberDTO.getUserName());
			preparedStatement.setString(3, memberDTO.getUserpw());
			preparedStatement.setString(4, memberDTO.getAddress());
			preparedStatement.setString(5, memberDTO.getPhoneNumber());

			preparedStatement.executeUpdate();
		} finally {
			databaseUtility.close(preparedStatement, connection);
		}
	}

	public ArrayList<MemberDTO> selectAllMember() throws SQLException {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "select * from mydb.user";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(new MemberDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
			}

		} finally {
			DatabaseUtility.close(resultSet, preparedStatement, connection);
		}
		return list;
	}

	public void updateMemberByEmail(MemberDTO memberDTO) throws SQLException {
		String sql = "update mydb.user set username=?, userpw = ?, address = ?, phonenumber = ? where email = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;


		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getUserName());
			preparedStatement.setString(2, memberDTO.getUserpw());
			preparedStatement.setString(3, memberDTO.getAddress());
			preparedStatement.setString(4, memberDTO.getPhoneNumber());
			preparedStatement.setString(5, memberDTO.getEmail());
			preparedStatement.executeUpdate();
		} finally {
			databaseUtility.close(preparedStatement, connection);
		}
	}

	public void deleteMemberByEmail(String email) throws SQLException {
		String sql = "delete from mydb.user where email = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, email);

			preparedStatement.executeUpdate();

		} finally {

			databaseUtility.close(preparedStatement, connection);
		}

	}

	public void deleteMemberByUserSeq(String userSeq) throws SQLException {
		String sql = "delete from mydb.user where userseq = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, userSeq);

			preparedStatement.executeUpdate();

		} finally {

			databaseUtility.close(preparedStatement, connection);
		}

	}
}
