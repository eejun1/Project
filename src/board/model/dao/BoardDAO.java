package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import board.dto.BoardDTO;  //boardDTO 를 가져온다
import util.DatabaseUtility; //DB 커넥유틸리티


public class BoardDAO {

    //싱클톤 패턴
    private static BoardDAO instance;
    private DatabaseUtility databaseUtility;
    private BasicDataSource dataSource;

    private BoardDAO(){
        databaseUtility =DatabaseUtility.getInstance();
        dataSource = databaseUtility.getDataSource();

    }
    public static BoardDAO getInstance(){
        if(instance == null){
            instance = new BoardDAO();
        }
        return instance;
    }

    public ArrayList<BoardDTO> selectAllBoard() throws SQLException{
        ArrayList<BoardDTO> list = new ArrayList<>();
        String sql = "select * from mydb.qnaboard";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = dataSource.getConnection();

            preparedStatement = conn.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                list.add(new BoardDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6)));
                //BoardDTO 를 통해서 해당 칼럼을 가져온다
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.close(resultSet,preparedStatement,conn);
        }
        return list;
    }

    public ArrayList<BoardDTO> insertBoard() throws SQLException{
        ArrayList<BoardDTO> list = new ArrayList<>();
        String sql = "Insert Into mydb.qnaboard(qnabdtitle,qnabdcontent,qnabdpw,user_userseq) VALUES(?,?,?,?)";
        Connection conn=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        conn = dataSource.getConnection();

        preparedStatement = conn.prepareStatement(sql);

        resultSet = preparedStatement.executeQuery();


    }

}
