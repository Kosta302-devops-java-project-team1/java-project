package main.java.com.project.repository;

import main.java.com.project.dto.Ticket;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TicketDao {
    void insertTicket(Connection con, List<Ticket> list) throws SQLException;
    void deleteTicket(Connection con, long reservationId) throws SQLException;
    List<Ticket> selectByReservationId(Connection con, long reservationId) throws SQLException;
}
