package main.java.com.project.repository;

import main.java.com.project.common.DBManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 테스트용 클래스(언제든 삭제 수정 가능)
 */
public class ReservationDaoImpl implements ReservationDao{

    @Override
    public void reservation() {
        Connection con = null;

        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBManager.releaseConnection(con, null);
        }
    }
}
