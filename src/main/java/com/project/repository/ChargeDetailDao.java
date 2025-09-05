package main.java.com.project.repository;

import main.java.com.project.dto.CreditHistory;

import java.sql.SQLException;
import java.util.List;

public interface ChargeDetailDao {
    List<CreditHistory> selectByMemberId(long memberId) throws SQLException;
}
