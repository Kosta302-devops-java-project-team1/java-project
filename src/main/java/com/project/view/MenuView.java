package main.java.com.project.view;

import main.java.com.project.controller.MemberController;
import main.java.com.project.dto.Member;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MenuView {
    MemberController memberController = MemberController.getInstance();
    Scanner sc = new Scanner(System.in);
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    public void start(){
        System.out.println("---------------------------------");
        guestView();
    }

    public void guestView(){
        while(true){
            System.out.println("---------------------------------");
            System.out.println("1. 항공편검색\t2.로그인\t3.회원가입\t9.종료");
            System.out.println("---------------------------------");
            String menu = sc.nextLine();
            switch (menu){
                case "1" :
                    break;
                case "2" :
                    break;
                case "3" :
                    registerMemberView();
                    break;
                case "4" :
                    System.exit(0);
            }
        }

    }

    public void memberView(){

    }

    public void adminView(){

    }

    public void registerMemberView(){
        String email = null;
        String password = null;
        while(true){
            System.out.println("이메일 : ");
            email = sc.nextLine();
            if(memberController.emailDuplicateChk(email)){
                System.out.println("중복된 이메일이 있음");
            } else if(!isValidEmail(email)){
                System.out.println("잘못된 이메일 형식");
            } else {
                break;
            }
        }
        while(true){
            System.out.println("비밀번호 : ");
            password = sc.nextLine();
            if(password.length() >= 4){
                break;
            }
            System.out.println("비밀번호는 4자리 이상");
        }
        Member member = new Member(email, password);
        memberController.registerMember(member);
    }



    private boolean isValidEmail(String email) {
        if(email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches(); // 전체 일치 체크
    }
}
