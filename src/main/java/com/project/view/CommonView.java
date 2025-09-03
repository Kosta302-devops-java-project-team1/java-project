package main.java.com.project.view;

import main.java.com.project.controller.BoardController;
import main.java.com.project.controller.MemberController;
import main.java.com.project.dto.Board;
import main.java.com.project.dto.Member;

import java.util.List;

public class CommonView {
    MemberController memberController = MemberController.getInstance();
    BoardController boardController = BoardController.getInstance();
    public void run(){
        System.out.println("------------------------------");
        System.out.println("----------사이트 상표 자리------------");
        System.out.println("------------------------------");
        System.out.println("----------이벤트 배너------------");
        List<Board> list = viewEvent();
        for(Board b : list){
            System.out.println(b.getContent());
        }
        System.out.println("-------------------------------");
    }


    public void logoutView(Member member){
        memberController.logout(member);
    }

    public List<Board> viewEvent(){
        List<Board> list = boardController.searchNotClosedBoard();
        return list;
    }

}
