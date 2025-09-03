package main.java.com.project.service;

import main.java.com.project.dto.Board;
import main.java.com.project.dto.Member;
import main.java.com.project.exception.AccessDeniedException;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    /**
     * 이벤트 기간이 끝나지 않은 게시글을 가지고 옴.
     * @return
     * @throws SQLException
     */
    List<Board> searchNotClosedBoard() throws SQLException;

    void writeBoard(Member member, Board board) throws SQLException, AccessDeniedException;
}
