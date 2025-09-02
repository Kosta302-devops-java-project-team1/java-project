package main.java.com.project.service;

import main.java.com.project.dto.Member;
import main.java.com.project.exception.EmailDuplicateException;
import main.java.com.project.exception.MemberNotFoundException;
import main.java.com.project.repository.MemberRepository;
import main.java.com.project.repository.MemberRepositoryImpl;
import main.java.com.project.session.Session;
import main.java.com.project.session.SessionSet;

import java.sql.SQLException;

public class MemberServiceImpl implements MemberService{
    private static final MemberServiceImpl instance = new MemberServiceImpl();
    private final MemberRepository memberRepository = new MemberRepositoryImpl();
    private MemberServiceImpl() {
    }
    public static MemberService getInstance(){
        return instance;
    }

    @Override
    public void registerMember(Member member) throws SQLException, EmailDuplicateException {
        int result = memberRepository.registerMember(member);
        if(result == 0){
            throw new SQLException("등록 실패");
        }
    }

    @Override
    public boolean emailDuplicateChk(String email) throws SQLException {
        Member byEmail = memberRepository.findByEmail(email);
        return byEmail != null;
    }

    @Override
    public boolean passwordChk(Member member, String password) {
        return member.getPassword().equals(password);
    }

    @Override
    public Member login(Member member) throws SQLException, MemberNotFoundException {
        Member login = memberRepository.login(member);
        if(login == null){
            throw new MemberNotFoundException("일치하는 계정정보가 없습니다.");
        }
        Session session = new Session(member.getEmail(), member);
        SessionSet sessionSet = SessionSet.getInstance();
        sessionSet.add(session);

        return login;
    }

    @Override
    public void logout(Member member) {
        Session session = new Session(member.getEmail(), member);
        SessionSet sessionSet = SessionSet.getInstance();
        sessionSet.remove(session);
    }
}
