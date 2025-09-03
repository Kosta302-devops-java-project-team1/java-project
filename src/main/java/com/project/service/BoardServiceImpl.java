package main.java.com.project.service;

import main.java.com.project.dto.Board;
import main.java.com.project.dto.Member;
import main.java.com.project.exception.AccessDeniedException;
import main.java.com.project.repository.BoardDao;
import main.java.com.project.repository.BoardDaoImpl;
import main.java.com.project.repository.MemberDao;
import main.java.com.project.repository.MemberDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class BoardServiceImpl implements BoardService{
    private static final BoardServiceImpl instance = new BoardServiceImpl();
    private BoardServiceImpl() {
    }
    public static BoardServiceImpl getInstance(){
        return instance;
    }
    BoardDao boardDao = new BoardDaoImpl();

    @Override
    public List<Board> searchNotClosedBoard() throws SQLException {
        return boardDao.findNotClosedBoard();
    }

    @Override
    public void writeBoard(Member member, Board board) throws SQLException, AccessDeniedException {
        if(!member.isAdmin()){
            throw new AccessDeniedException("관리자가 아닙니다.");
        }
        boardDao.insertBoard(member.getId(), board);
    }
}
