package main.java.com.project.controller;

import main.java.com.project.dto.Board;
import main.java.com.project.dto.Member;
import main.java.com.project.exception.AccessDeniedException;
import main.java.com.project.service.BoardService;
import main.java.com.project.service.BoardServiceImpl;
import main.java.com.project.service.MemberService;
import main.java.com.project.service.MemberServiceImpl;
import main.java.com.project.view.FailView;
import main.java.com.project.view.SuccessView;

import java.sql.SQLException;
import java.util.List;

public class BoardController {
    private static final BoardController instance = new BoardController();
    private BoardController() {
    }
    public static BoardController getInstance(){
        return instance;
    }
    BoardService boardService = BoardServiceImpl.getInstance();



    public List<Board> searchNotClosedBoard(){
        List<Board> list = null;
        try {
            list = boardService.searchNotClosedBoard();
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
        return list;
    }

    public void writeBoard(Member member, Board board){
        try {
            boardService.writeBoard(member, board);
            SuccessView.printMessage("게시글이 등록되었습니다.");
        } catch (SQLException | AccessDeniedException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public Board searchOneBoard(long boardId){
        Board board = null;
        try {
            board = boardService.searchOneBoard(boardId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return board;

    }

    public List<Board> searchAllBoard(){
        List<Board> list = null;
        try {
            list = boardService.searchAllBoard();
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
        return list;
    }

    public void updateBoard(Member member, Board board){
        try {
            boardService.updateBoard(member, board);
            SuccessView.printMessage("게시글이 수정되었습니다.");
        } catch (SQLException | AccessDeniedException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public void deleteBoard(Member member, long boardId){
        try {
            boardService.deleteBoard(member, boardId);
            SuccessView.printMessage("게시글이 삭제되었습니다.");
        } catch (SQLException | AccessDeniedException e) {
            FailView.errorMessage(e.getMessage());
        }
    }



}
