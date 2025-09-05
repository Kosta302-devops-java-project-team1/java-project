package main.java.com.project.controller;

import main.java.com.project.dto.Member;
import main.java.com.project.dto.Reservation;
import main.java.com.project.dto.Ticket;
import main.java.com.project.service.ReservationService;
import main.java.com.project.service.ReservationServiceImpl;

import java.util.List;

public class ReservationController {
    private static final ReservationController instance = new ReservationController();
    private ReservationController() {
    }
    public static ReservationController getInstance(){
        return instance;
    }
    private final ReservationService reservationService = ReservationServiceImpl.getInstance();


    public void makeReservation(Member member, Reservation reservation, List<Ticket> tickets){
        reservationService.makeReservation(member, reservation, tickets);
    }

}
