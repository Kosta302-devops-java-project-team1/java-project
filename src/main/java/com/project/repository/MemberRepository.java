package main.java.com.project.repository;

import main.java.com.project.dto.Member;

import java.sql.SQLException;

public interface MemberRepository {
    /**
     * insert into members(email, password) values(?,?)
     * @throws SQLException
     */
    int registerMember(Member meber) throws SQLException;

    /**
     * select * from members where email = ?
     * @param email
     * @return
     * @throws SQLException
     */
    Member findByEmail(String email) throws SQLException;
    Member login(Member member);
}
