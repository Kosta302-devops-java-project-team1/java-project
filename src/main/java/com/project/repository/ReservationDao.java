package main.java.com.project.repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 테스트용 클래스(언제든 삭제 수정 가능)
 */
public interface ReservationDao {
    int insertReservation(Connection con, long memberId, int count, int totalAmount) throws SQLException;

}
