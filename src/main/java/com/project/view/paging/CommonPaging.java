package main.java.com.project.view.paging;

import java.util.List;
import java.util.Scanner;

public class CommonPaging {

    public static void paging(int size, int page, List<?> list) {
        int pageSize = size;
        int currentPage = page;
        int totalPages = (int) Math.ceil((double) list.size() / pageSize);


        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, list.size());

        System.out.println("\n=== 페이지 " + (currentPage + 1) + " / " + totalPages + "===");
        for (int i = start; i < end; i++) {
            System.out.println(list.get(i).toString());
        }
    }
}