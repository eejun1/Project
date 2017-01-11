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

    private BoardDAO() {
        databaseUtility = DatabaseUtility.getInstance();
        dataSource = databaseUtility.getDataSource();

    }

    public static BoardDAO getInstance() {
        if (instance == null) {
            instance = new BoardDAO();
        }
        return instance;
    }

    public ArrayList<BoardDTO> selectAllBoard() throws SQLException {

        ArrayList<BoardDTO> list = new ArrayList<>();
        String sql = "select a.qnabdtitle, a.qnabddate, b.username, a.qnabdseq from qnaboard a  , user b  WHERE user_userseq = userseq order by a.qnabdseq DESC";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                list.add(new BoardDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getInt(4)));
                //BoardDTO 를 통해서 해당 칼럼을 가져온다
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.close(resultSet, preparedStatement, conn);
        }
        return list;
    }

    public void insertBoard(BoardDTO boardDTO) throws SQLException { //게시판 글쓰기

        String sql = "Insert Into mydb.qnaboard(qnabdtitle,qnabdcontent,qnabdpw,user_userseq) VALUES(?,?,?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, boardDTO.getQnabdtitle());
            preparedStatement.setString(2, boardDTO.getQnabdcontent());
            preparedStatement.setInt(3, boardDTO.getQnabdpw());
            preparedStatement.setInt(4, boardDTO.getUser_userseq());
            preparedStatement.executeUpdate();

        } finally {
            databaseUtility.close(preparedStatement, conn);
        }

    }
    public void modifyBoard(BoardDTO boardDTO, int boardseq) throws SQLException{

        String sql ="update mydb.qnaboard set qnabdtitle =?, qnabdpw=?, qnabdcontent=? Where qnabdseq=?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,boardDTO.getQnabdtitle());
            preparedStatement.setInt(2,boardDTO.getQnabdpw());
            preparedStatement.setString(3,boardDTO.getQnabdcontent());
            preparedStatement.setInt(4,boardseq);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseUtility.close(preparedStatement, conn);
        }
    }

    public void deleteBoard(int qnabdseq) throws SQLException{ //게시판 삭제

        String sql ="delete from mydb.qnaboard where qnabdseq=?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,qnabdseq);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseUtility.close(resultSet, preparedStatement, conn);
        }

    }

    public ArrayList<BoardDTO> viewBoard(int qnabdseq) throws SQLException{

        ArrayList<BoardDTO> list = new ArrayList<>();
        String sql = "select a.qnabdtitle, a.qnabddate, b.username ,a.qnabdseq, a.qnabdpw, a.qnabdcontent, a.user_userseq from qnaboard a LEFT JOIN user b ON (a.user_userseq = b.userseq)  WHERE qnabdseq = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,qnabdseq);
//            preparedStatement.executeUpdate();

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                list.add(new BoardDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getInt(7)));
                //BoardDTO 를 통해서 해당 칼럼을 가져온다
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.close(resultSet, preparedStatement, conn);
        }
        return list;

    }

}
