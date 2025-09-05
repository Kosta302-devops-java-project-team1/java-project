package main.java.com.project.service;

import main.java.com.project.common.DBManager;
import main.java.com.project.dto.Flight;
import main.java.com.project.dto.Member;
import main.java.com.project.dto.Reservation;
import main.java.com.project.dto.Ticket;
import main.java.com.project.exception.InsufficientBalanceException;
import main.java.com.project.exception.MemberNotFoundException;
import main.java.com.project.repository.MemberDao;
import main.java.com.project.repository.MemberDaoImpl;
import main.java.com.project.repository.ReservationDao;
import main.java.com.project.repository.ReservationDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReservationServiceImpl implements ReservationService{
    private static final ReservationServiceImpl instance = new ReservationServiceImpl();
    private ReservationServiceImpl() {
    }
    public static ReservationService getInstance(){
        return instance;
    }
    private final ReservationDao reservationDao = new ReservationDaoImpl();
    private final MemberDao memberDao = new MemberDaoImpl();


    @Override
    public boolean makeReservation(Member member, Flight flight, Reservation reservation, List<Ticket> tickets) throws SQLException, InsufficientBalanceException, MemberNotFoundException {
        Connection con = DBManager.getConnection();
        con.setAutoCommit(false);
        int count = tickets.size();
        int totalAmount = (int)Math.round(flight.getPrice()) * count;
        member.setBalance(-totalAmount);
        int reservationResult = reservationDao.insertReservation(con, member.getId(), count, totalAmount);
        memberDao.updateBalance(member);
        return false;
    }
}
