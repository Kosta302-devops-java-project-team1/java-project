package main.java.com.project.view;

import main.java.com.project.controller.BoardController;
import main.java.com.project.controller.MemberController;
import main.java.com.project.dto.Member;

import java.util.Scanner;

public class AdminView {
    MemberController memberController = MemberController.getInstance();
    BoardController boardController = BoardController.getInstance();
    CommonView commonView = new CommonView();
    Scanner sc = new Scanner(System.in);
    public void run(Member member){
        while(true){
            commonView.run();
            System.out.println("-----"+member.getEmail()+"-----");
            System.out.println("1.항공편 관리\t2.예약 조회/관리\t3.이벤트 조회/관리\t4.로그아웃");
            System.out.println("---------------------------------");
            String menu = sc.nextLine();
            switch (menu){
                case "1" :
                    break;
                case "2" :
                    break;
                case "3" :
                    break;
                case "4" :
                    commonView.logoutView(member);
                    return;
            }
        }
    }

    public void viewEvent(Member member){

    }


}
