package main.java.com.project.controller;

import main.java.com.project.dto.Flight;
import main.java.com.project.dto.Member;
import main.java.com.project.dto.Reservation;
import main.java.com.project.dto.Ticket;
import main.java.com.project.service.ReservationService;
import main.java.com.project.service.ReservationServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class ReservationController {
    private static final ReservationController instance = new ReservationController();
    private ReservationController() {
    }
    public static ReservationController getInstance(){
        return instance;
    }
    private final ReservationService reservationService = ReservationServiceImpl.getInstance();


    public void makeReservation(Member member, Flight flight, Reservation reservation, List<Ticket> tickets){
        try {
            reservationService.makeReservation(member, flight, reservation, tickets);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
