package main.java.com.project.view;

import main.java.com.project.dto.Member;

public class AdminView {
    public void run(Member member){
        System.out.println("adminView");
        System.out.println(member.getEmail());
    }
}
