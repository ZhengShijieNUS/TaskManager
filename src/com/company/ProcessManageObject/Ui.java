package com.company.ProcessManageObject;

import java.util.Scanner;

public class Ui {
    private static Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public void showMessage(String line){
        System.out.println(line);
    }

    public String readUserCommand(){
        return in.nextLine().trim();
    }

}
