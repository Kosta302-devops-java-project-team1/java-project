package main.java.com.project.service;

import main.java.com.project.dto.Member;
import main.java.com.project.dto.Reservation;
import main.java.com.project.dto.Ticket;

import java.util.List;

public class ReservationServiceImpl implements ReservationService{
    private static final ReservationServiceImpl instance = new ReservationServiceImpl();
    private ReservationServiceImpl() {
    }
    public static ReservationService getInstance(){
        return instance;
    }

    @Override
    public boolean makeReservation(Member member, Reservation reservation, List<Ticket> tickets) {
        return false;
    }
}
