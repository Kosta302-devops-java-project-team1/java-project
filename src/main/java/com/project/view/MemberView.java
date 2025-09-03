package main.java.com.project.view;

import main.java.com.project.controller.MemberController;
import main.java.com.project.dto.Member;

import java.util.Scanner;

public class MemberView {
    MemberController memberController = MemberController.getInstance();
    Scanner sc = new Scanner(System.in);
    public void run(Member member){
        while(true){
            System.out.println("-----"+member.getEmail()+"-----");
            System.out.println("1.항공편 검색\t2.개인정보수정\t3.예매내역확인\t4.크레딧\t5.결제내역\t6.로그아웃");
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
                    member = checkBalance(member);
                    break;
                case "5" :
                    break;
                case "6" :
                    logoutView(member);
                    return;
            }
        }
    }

    public void logoutView(Member member){
        memberController.logout(member);
    }

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
    public Member checkBalance(Member member){
        System.out.println("현재 크레딧");
        System.out.println(">" + member.getBalance());
        System.out.println("1. 충전\t9.뒤로가기");
        while(true){
            String menu = sc.nextLine();
            if(menu.equals("1")){
                return chargeBalance(member);
            } else if (menu.equals("9")) {
                return member;
            } else {
                System.out.println("잘못 입력함");
            }
        }
    }

    public Member chargeBalance(Member member){
        System.out.println("충전할 금액을 입력하세요");
        System.out.println(">");
        long charge = 0;
        while(true){
            charge = Long.parseLong(sc.nextLine());
            if(charge > 0){
                break;
            }
            System.out.println("0이거나 음수임");
        }
        return updateBalance(member.getId(), member.getEmail(), charge);
    }

    private Member updateBalance(long id, String email, long charge){
        Member member = new Member(id, email, null, null, charge, false);
        return memberController.updateBalance(member);
    }

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
}
