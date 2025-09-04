package main.java.com.project.view;

import main.java.com.project.dto.Flight;
import main.java.com.project.view.paging.FlightDetailHandler;
import main.java.com.project.view.paging.FlightPagingView;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// todo 뷰수정
public class FlightSearchSuccessView {
    private static final Scanner sc = new Scanner(System.in);

    public static void printFlightList(List<Flight> flights) {
        String choice;

        while (true) {
            System.out.println("정렬 | [1] 가격순 [2] 최단 시간순 [3] 출발 시간순 [4] 도착 시간순 [0] 나가기");
            choice = sc.nextLine();

            if (choice.equals("1")) {
                flights.sort(Comparator.comparingDouble(Flight::getPrice));
            } else if (choice.equals("2")) {
                // todo duration 정제후 비교 오류 확인
                flights.sort(Comparator.comparing(Flight::getDuration));
            } else if (choice.equals("3")) {
                flights.sort(Comparator.comparing(Flight::getDeparture_time));
            } else if (choice.equals("4")) {
                // todo duration 정제후 비교 오류 확인
                flights.sort(Comparator.comparing(Flight::getArrival_time));
            } else if (choice.equals("0")) {
                return;
            } else {
                System.out.println("다시 선택해주세요.");
                continue;
            }
            FlightPagingView.paging(5, flights, new FlightDetailHandler());
            //todo 회원 or 비회원 메뉴 뷰로 이동
        }
    }
    private static int parseDuration(String duration) {
        if (duration == null || duration.isEmpty()) {
            return 0;
        }

        // "PT"를 제거
        String cleanDuration = duration.substring(2);

        int hours = 0;
        int minutes = 0;

        int hIndex = cleanDuration.indexOf('H');
        int mIndex = cleanDuration.indexOf('M');

        if (hIndex != -1) {
            hours = Integer.parseInt(cleanDuration.substring(0, hIndex));
        }

        if (mIndex != -1) {
            int startIndex = (hIndex != -1) ? hIndex + 1 : 0;
            minutes = Integer.parseInt(cleanDuration.substring(startIndex, mIndex));
        }

        return (hours * 60) + minutes;
    }



    public static void printFlightList(List<Flight> flights, List<Flight> returnFlights) {
        String choice;
        while (true) {
            System.out.println("[1] 가격순 [2] 최단 시간순 [3] 출발 시간순 [4] 도착 시간순 [0] 나가기");
            choice = sc.nextLine();

            if (choice.equals("1")) {
                flights.sort(Comparator.comparingDouble(Flight::getPrice));
            } else if (choice.equals("2")) {
                // todo duration 정제후 비교 오류 확인
                flights.sort(Comparator.comparing(Flight::getDuration));
            } else if (choice.equals("0")) {
                return;
            } else {
                System.out.println("다시 선택해주세요.");
                continue;
            }

            // todo
            FlightPagingView.paging(5, flights, new FlightDetailHandler());
            System.out.println("------------복귀행-------------");

            FlightPagingView.paging(5, returnFlights, new FlightDetailHandler());

            //todo 메인뷰로 이동
        }
    }
}
