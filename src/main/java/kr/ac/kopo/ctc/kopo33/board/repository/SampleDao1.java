package kr.ac.kopo.ctc.kopo33.board.repository;

import kr.ac.kopo.ctc.kopo33.board.domain.Sample;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SampleDao1 {

    private static final String DB_URL = "jdbc:mysql://192.168.26.233:3306/board";
    private static final String DB_USER = "root"; // 2 usages
    private static final String DB_PASSWORD = "root"; // 2 usages
    private static final String TABLE_NAME = "sample"; // 데이터를 조회할 테이블 이름

    public List<Sample> findAll() { // 1 usage new*
        List<Sample> ret = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sqlQuery = "SELECT * FROM " + TABLE_NAME;
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Sample sample = new Sample();
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                sample.setId(id);
                sample.setTitle(title);
                ret.add(sample);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public Sample findById(Long id) {
        Sample ret = new Sample();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.26.233:3306/board", "root", "root");

            String sqlQuery = "SELECT id, title FROM " + "sample" + " WHERE id = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ret = new Sample();
                Long sampleId = resultSet.getLong("id");
                String title = resultSet.getString("title");
                ret.setId(sampleId);
                ret.setTitle(title);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
}
