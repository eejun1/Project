package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseUtility {

	// 싱글톤 패턴 : 생성자에서 DataSource 객체를 생성해서 instance에 할당
	// 객체는 한개만 생성 가능
	private BasicDataSource dataSource;
	private static DatabaseUtility instance = new DatabaseUtility();

	public static DatabaseUtility getInstance() {
		return instance;
	}

	private DatabaseUtility() {
		dataSource = new BasicDataSource(); // 객체 생성

		// 연결 DB 관련 설정
		dataSource.setDriverClassName("com.mysql.jdbc.Driver"); // jdbc Driver 사용한다
		dataSource.setUrl("jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=euc-kr"); //url 주소에 euc-kr 로 인코딩
		dataSource.setUsername("root"); //사용자 이름
		dataSource.setPassword("wnsdnjs2"); //사용자 비밀번호
	}

	public BasicDataSource getDataSource() {
		return dataSource;
	}

	public void close(PreparedStatement pstmt, Connection conn) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rset, PreparedStatement pstmt, Connection conn) {
		if (rset != null) {
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}