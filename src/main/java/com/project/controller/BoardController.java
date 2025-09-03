package main.java.com.project.controller;

import main.java.com.project.dto.Board;
import main.java.com.project.service.BoardService;
import main.java.com.project.service.BoardServiceImpl;
import main.java.com.project.service.MemberService;
import main.java.com.project.service.MemberServiceImpl;
import main.java.com.project.view.FailView;

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



}
