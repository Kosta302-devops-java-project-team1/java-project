package main.java.com.project.repository;

import main.java.com.project.common.DBManager;

import java.sql.*;

/**
 * 테스트용 클래스(언제든 삭제 수정 가능)
 */
public class ReservationDaoImpl implements ReservationDao{

    @Override
    public long insertReservation(Connection con, long memberId, int count, int totalAmount) throws SQLException {
        PreparedStatement ps = null;
        String sql = "insert into reservations(member_id, count, total_amount) values(?, ?, ?)";
        int result = 0;
        long pk = 0;
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, memberId);
            ps.setInt(2, count);
            ps.setInt(3, totalAmount);
            result = ps.executeUpdate();
            if(result == 0){
                con.rollback();
                throw new SQLException("예약테이블 레코드생성 실패");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                pk = rs.getLong(1);
            }
        } catch (SQLException e) {
            con.rollback();
            throw new SQLException("예약테이블 레코드생성 실패");
        } finally {
            DBManager.releaseConnection(null, ps);
        }
        return pk;
    }

    @Override
    public void deleteReservation(Connection con, long reservationId) throws SQLException {
        PreparedStatement ps = null;
        String sql = "delete from reservations where reservation_id = ?";
        int result = 0;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, reservationId);
            result = ps.executeUpdate();
            if(result == 0){
                con.rollback();
                throw new SQLException("예약테이블 레코드생성 실패");
            }
        } catch (SQLException e) {
            con.rollback();
            throw new SQLException("예약테이블 레코드생성 실패");
        } finally {
            DBManager.releaseConnection(null, ps);
        }

    }
}
