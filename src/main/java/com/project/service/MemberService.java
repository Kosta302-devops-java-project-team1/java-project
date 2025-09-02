package main.java.com.project.service;

import main.java.com.project.dto.Member;
import main.java.com.project.exception.EmailDuplicateException;
import main.java.com.project.exception.MemberNotFoundException;

import java.sql.SQLException;

public interface MemberService {
    void registerMember(Member member) throws SQLException, EmailDuplicateException;

    /**
     * 기존 DB에 같은 이메일이 있는지 체크하는 메서드.
     * true = 있음. false = 없음
     * @param email
     * @return
     * @throws SQLException
     */
    boolean emailDuplicateChk(String email) throws SQLException;

    boolean passwordChk(Member member, String password);
    /**
     * id, password로 로그인하는 메서드
     * @param member
     */
    Member login(Member member) throws SQLException, MemberNotFoundException;

    /**
     * SessionSet에서 현제 session 삭제. 즉. 로그아웃메서드
     * @param member
     */
    void logout(Member member);



}
