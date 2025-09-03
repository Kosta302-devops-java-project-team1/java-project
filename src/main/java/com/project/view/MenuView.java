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
            System.out.println("1.항공편 검색\t2.개인정보수정\t3.예매내역확인\t4.크레딧충전\t5.결제내역\t6.로그아웃");
            System.out.println("---------------------------------");
            String menu = sc.nextLine();
            switch (menu){
                case "1" :
                    break;
                case "2" :
                    member = updatePassword(member);
                    break;
                case "3" :
                    break;
                case "4" :

                    break;
                case "5" :
                    break;
                case "6" :
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
        System.out.println("이메일 : ");
        System.out.println("0. 뒤로가기");
        System.out.print(">");
        String email = emailChk();
        if(email.equals("0")){
            return;
        }
        System.out.println("비밀번호 : ");
        System.out.println("0. 뒤로가기");
        System.out.print(">");
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
        if(login == null){
            return;
        }
        if(login.isAdmin()){
            adminView(login);
        }else {
            memberView(login);
        }
    }

    public void logoutView(Member member){
        memberController.logout(member);
    }

    /**
     * 패스워드 업데이트. 먼저 입력한 패스워드가 현재 패스워드와 맞는지 검증
     * 이후 변경할 패스워드를 입력받아 db에 update후 sessionSet에 있는 기존 session을 삭제, 새로 받은 객체를 추가
     * 그리고 memberView의 파라미터 member객체를 업데이트.
     * @param member
     * @return
     */
    public Member updatePassword(Member member){
        while(true){
            System.out.println("현재 비밀번호를 입력");
            System.out.println("0.뒤로가기");
            System.out.print(">");
            String currentPassword = sc.nextLine();
            if(currentPassword.equals("0")){
                return null;
            }
            if(memberController.passwordChk(member, currentPassword)){ //현재 패스워드가 맞는지 체크 후 맞으면 break
                break;
            }
            System.out.println("비밀번호가 맞지 않음");
        }
        System.out.println("변경할 비밀번호를 입력");
        System.out.println("0.뒤로가기");
        System.out.print(">");
        String password = passwordChk(); //비밀번호가 4자리 이상일때 password로 받아옴
        Member updated = memberController.updatePassword(member, password);// 실제 패스워드 수정후 member객체 받아옴
        return updated;
    }

    public Member chargeBalance(long id){
        System.out.println("충전할 금액을 입력하세요");
        System.out.println(">");
        long charge = Long.parseLong(sc.nextLine());
        Member member = new Member(id, null, null, null, charge, false);

        return null;
    }


    /**
     * email이 중복인지 혹은 형식에 맞는지 체크하는 메서드
     * @return
     */
    private String emailChk(){
        String email = null;
        while(true){
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

    /**
     * email중복체크
     * @param email
     * @return
     */
    private boolean isValidEmail(String email) {
        if(email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches(); // 전체 일치 체크
    }
}
