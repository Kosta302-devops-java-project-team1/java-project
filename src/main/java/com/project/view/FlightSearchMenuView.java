package main.java.com.project.view;

import main.java.com.project.controller.FlightController;

import java.util.Scanner;

public class FlightSearchMenuView {
    public static void search(Scanner sc) {
        // todo 입력 예외처리
        System.out.print("편도:1 | 왕복:2 >");
        int input = sc.nextInt();
        sc.nextLine();

        System.out.print("인원수> ");
        int adults = sc.nextInt();
        sc.nextLine();

        System.out.print("출발지> ");
        String origin = sc.nextLine();

        System.out.print("도착지> ");
        String destination = sc.nextLine();

        System.out.print("출발일> ");
        String departDate = sc.nextLine();



        // 왕복행
        if (input == 2) {
            System.out.print("복귀일> ");
            String returnDate = sc.nextLine();
            FlightController.flightSearch(origin, destination, departDate, adults, returnDate);
        } else {
            FlightController.flightSearch(origin, destination, departDate, adults);
        }




    }

}
