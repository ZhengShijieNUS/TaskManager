package com.company;

import java.util.Scanner;

public class Ui {
    private static Scanner in;

    Ui() {
        in = new Scanner(System.in);
    }

    void showMessage(String line){
        System.out.println(line);
    }

    String readUserCommand(){
        return in.nextLine().trim();
    }

}
