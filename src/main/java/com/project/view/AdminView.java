package main.java.com.project.view;

import main.java.com.project.controller.BoardController;
import main.java.com.project.controller.MemberController;
import main.java.com.project.dto.Board;
import main.java.com.project.dto.Member;
import main.java.com.project.view.paging.CommonPaging;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    MemberController memberController = MemberController.getInstance();
    BoardController boardController = BoardController.getInstance();
    CommonView commonView = new CommonView();
    Scanner sc = new Scanner(System.in);
    private final static int PAGESIZE = 5;
    public void run(Member member){
        while(true){
            commonView.run();
            System.out.println("-----관리자모드-----");
            System.out.println("1.항공편 관리\t2.예약 조회/관리\t3.이벤트 조회/관리\t4.로그아웃");
            System.out.println("---------------------------------");
            String menu = sc.nextLine();
            switch (menu){
                case "1" :
                    break;
                case "2" :
                    break;
                case "3" :
                    viewEvent(member);
                    break;
                case "4" :
                    commonView.logoutView(member);
                    return;
            }
        }
    }

    public void viewEvent(Member member){
        int currentPage = 0;
        List<Board> boards = boardController.searchAllBoard();
        int lastPage = (boards.size() / PAGESIZE);
        while(true){
            CommonPaging.paging(PAGESIZE, currentPage, boards, "event");


            System.out.println("\n메뉴: [0] 이전페이지 [1] 등록 [2] 수정 [3] 삭제 [6] 다음페이지 [9] 이전메뉴");
            String menu = sc.nextLine();
            switch (menu) {
                case "6":
                    if (currentPage < lastPage) {
                        currentPage++;
                    } else {
                        System.out.println("마지막페이지 입니다.");
                    }
                    break;
                case "0":
                    if (currentPage > 0) {
                        currentPage--;
                    } else {
                        System.out.println("첫페이지입니다.");
                    }
                    break;
                case "9":
                    return;
                case "1":
                    writeBoardView(member);
                    boards = boardController.searchAllBoard();
                    break;
                case "2":
                    updateBoardView(member);
                    boards = boardController.searchAllBoard();
                    break;
                case "3":
                    deleteBoardView(member);
                    boards = boardController.searchAllBoard();
                    break;
                default:
                    System.out.println("다시 입력해주세요.");
            }
        }
    }

    public void writeBoardView(Member member){
        System.out.println("내용 : ");
        String content = sc.nextLine();
        System.out.println("기한 : 형식(2025-10-20)");
        String eventEndAt = sc.nextLine();
        Board board = new Board(member.getId(), content, eventEndAt);
        boardController.writeBoard(member, board);
    }


    public void updateBoardView(Member member){
        System.out.println("수정할 글 번호");
        long boardId = Long.parseLong(sc.nextLine());
        Board currentBoard = boardController.searchOneBoard(boardId);
        System.out.println("현재 내용 : " + currentBoard.getContent());
        System.out.println("수정할 내용 :(수정하지 않을 시 Enter)");
        System.out.print(">");
        String content = sc.nextLine();
        System.out.println("현재 기한 : " + currentBoard.getEventEndAt());
        System.out.println("수정할 기한 : 형식(2025-10-20)(수정하지 않을 시 Enter)");
        System.out.print(">");
        String eventEndAt = sc.nextLine();
        Board board = new Board();
        board.setId(currentBoard.getId());
        board.setContent(content);
        board.setEventEndAt(eventEndAt);
        boardController.updateBoard(member, board);
    }

    public void deleteBoardView(Member member){
        System.out.println("삭제할 글 번호");
        long boardId = Long.parseLong(sc.nextLine());
        Board currentBoard = boardController.searchOneBoard(boardId);
        System.out.println("내용 : " + currentBoard.getContent());
        System.out.println("기한 : " + currentBoard.getEventEndAt());
        System.out.println("삭제하시겠습니까? y/n");
        System.out.print(">");
        String result = sc.nextLine();
        if(result.equals("y") | result.equals("Y")){
            boardController.deleteBoard(member, boardId);
        }
    }

}
