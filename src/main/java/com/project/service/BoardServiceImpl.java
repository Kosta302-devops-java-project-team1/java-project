package main.java.com.project.service;

import main.java.com.project.dto.Board;
import main.java.com.project.dto.Member;
import main.java.com.project.exception.AccessDeniedException;
import main.java.com.project.repository.BoardDao;
import main.java.com.project.repository.BoardDaoImpl;

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

    @Override
    public List<Board> searchAllBoard() throws SQLException {
        return boardDao.findAllBoard();
    }

    @Override
    public Board searchOneBoard(long boardId) throws SQLException {
        return boardDao.findOneBoard(boardId);
    }

    @Override
    public void deleteBoard(Member member, long boardId) throws SQLException, AccessDeniedException {
        if(!member.isAdmin()){
            throw new AccessDeniedException("관리자가 아닙니다.");
        }
        boardDao.deleteBoard(boardId);
    }

    @Override
    public void updateBoard(Member member, Board board) throws SQLException, AccessDeniedException {
        if(!member.isAdmin()){
            throw new AccessDeniedException("관리자가 아닙니다.");
        }
        Board searched = boardDao.findOneBoard(board.getId());
        if(board.getContent().isEmpty()){
            board.setContent(searched.getContent());
        }
        if(board.getEventEndAt().isEmpty()){
            board.setEventEndAt(searched.getEventEndAt());
        }
        boardDao.updateBoard(board);
    }
}
