package main.java.com.project.view;

import main.java.com.project.dto.Flight;

import java.util.List;

// todo 뷰수정
public class FlightSearchSuccessView {
    public static void printFlightList(List<Flight> flights) {
        // todo 페이지네이션
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }


    }

    public static void printFlightList(List<Flight> flights, List<Flight> returnFlights) {
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }

        System.out.println("------------복귀행-------------");
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }
    }
}
