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
                    loginView();
                    break;
                case "3" :
                    registerMemberView();
                    break;
                case "9" :
                    System.exit(0);
            }
        }

    }

    public void memberView(Member member){
        while(true){
            System.out.println("-----"+member.getEmail()+"-----");
            System.out.println("1.항공편 검색\t2.개인정보수정\t3.예매내역확인\t4.결제내역\t5.로그아웃");
            System.out.println("---------------------------------");
            String menu = sc.nextLine();
            switch (menu){
                case "1" :
                    break;
                case "2" :
                    break;
                case "3" :
                    break;
                case "4" :
                    break;
                case "5" :
                    logoutView(member);
                    return;
            }
        }
    }

    public void adminView(Member member){
        System.out.println("adminView");
        System.out.println(member.getEmail());
    }

    public void registerMemberView(){
        String email = emailChk();
        if(email.equals("0")){
            return;
        }
        String password = passwordChk();
        if(password.equals("0")){
            return;
        }
        Member member = new Member(email, password);
        memberController.registerMember(member);
    }

    public void loginView(){
        System.out.println("이메일 : ");
        String email = sc.nextLine();
        System.out.println("비밀번호 : ");
        String password = sc.nextLine();
        Member member = new Member(email, password);
        Member login = memberController.login(member);
        if(login.isAdmin()){
            adminView(login);
        }else {
            memberView(login);
        }
    }

    public void logoutView(Member member){
        memberController.logout(member);
    }

    public void updatePassword(Member member){
        while(true){
            System.out.println("현재 비밀번호를 입력");
            System.out.println("0.뒤로가기");
            System.out.print(">");
            String currentPassword = sc.nextLine();
            if(currentPassword.equals("0")){
                return;
            }
            if(memberController.passwordChk(member, currentPassword)){
                break;
            }
            System.out.println("비밀번호가 맞지 않음");
        }
        String password = passwordChk();
        memberController.updatePassword(member, password);
    }

    /**
     * email이 중복인지 혹은 형식에 맞는지 체크하는 메서드
     * @return
     */
    private String emailChk(){
        String email = null;
        while(true){
            System.out.println("이메일 : ");
            System.out.println("0. 뒤로가기");
            System.out.print(">");
            email = sc.nextLine();
            if(email.equals("0")){
                return email;
            }
            if(memberController.emailDuplicateChk(email)){
                System.out.println("중복된 이메일이 있음");
            } else if(!isValidEmail(email)){
                System.out.println("잘못된 이메일 형식");
            } else {
                break;
            }
        }
        return email;
    }

    /**
     * 비밀번호가 4자리 이상인지 체크하는 메서드
     * @return
     */
    private String passwordChk(){
        String password = null;
        while(true){
            System.out.println("비밀번호 : ");
            System.out.println("0. 뒤로가기");
            System.out.print(">");
            password = sc.nextLine();
            if(password.equals("0")){
                return password;
            }
            if(password.length() >= 4){
                break;
            }
            System.out.println("비밀번호는 4자리 이상");
        }
        return password;
    }

    private boolean isValidEmail(String email) {
        if(email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches(); // 전체 일치 체크
    }
}
