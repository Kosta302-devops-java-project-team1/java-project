package main.java.com.project.service;

import main.java.com.project.dto.Member;
import main.java.com.project.dto.Reservation;
import main.java.com.project.dto.Ticket;

import java.util.List;

public interface ReservationService {
    boolean makeReservation(Member member, Reservation reservation, List<Ticket> tickets);
}
