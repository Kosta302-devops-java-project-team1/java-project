package main.java.com.project.service;

import main.java.com.project.dto.Board;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    List<Board> searchNotClosedBoard() throws SQLException;
}
