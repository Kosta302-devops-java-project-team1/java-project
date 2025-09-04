package main.java.com.project.view.paging;

import main.java.com.project.dto.Flight;

import java.util.Scanner;

public class FlightDetailHandler implements DetailViewHandler<Flight> {
    private static final Scanner sc = new Scanner(System.in);

    @Override
    public void showDetail(Flight flight) {
        while (true) {
            System.out.println("\n=== 항공권 상세보기 ===");
            System.out.println(flight);

            System.out.println("[1] 예매하기 [0] 다음으로");
            String input = sc.nextLine();

            if (input.equals("1")) {
                // 예매 뷰
                // TODO 개인정보 선택뷰로 이동
                return; // 상세보기 종료
            } else if (input.equals("0")) {
                return; // 뒤로가기
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
