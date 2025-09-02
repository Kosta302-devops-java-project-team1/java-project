package main.java.com.project.controller;

import main.java.com.project.dto.Member;
import main.java.com.project.exception.EmailDuplicateException;
import main.java.com.project.service.MemberService;
import main.java.com.project.service.MemberServiceImpl;
import main.java.com.project.view.FailView;
import main.java.com.project.view.SuccessView;

import java.sql.SQLException;

public class MemberController {
    private static final MemberController instance = new MemberController();
    private final MemberService memberService = MemberServiceImpl.getInstance();
    private MemberController() {

    }
    public static MemberController getInstance(){
        return instance;
    }

    public void registerMember(Member member){
        try {
            memberService.registerMember(member);
            SuccessView.printMessage("회원가입 완료");
        } catch (SQLException | EmailDuplicateException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public boolean emailDuplicateChk(String email){
        boolean result = false;
        try {
            result = memberService.emailDuplicateChk(email);
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
        return result;
    }

}
