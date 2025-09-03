package main.java.com.project.repository;

import main.java.com.project.dto.Board;

import java.sql.SQLException;
import java.util.List;

public interface BoardDao {
    /**
     * 이벤트 기간이 끝나지 않은 이벤트 게시글을 가지고 오는 메서드
     * select시 이벤트 기간과 현재시간을 비교해서 기간이 끝나면 is_closed를 1로 변경함(종료시킴)
     * 변경 후 is_closed가 0인 게시글만 select해서 list로 리턴
     */
    List<Board> findNotClosedBoard() throws SQLException;

    /**
     * 이벤트 글 작성 메서드
     * @param memberId
     * @param board
     * @throws SQLException
     */
    void insertBoard(long memberId, Board board) throws SQLException;
}
