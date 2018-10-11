package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
        return in.nextLine();
    }

    public static List<String> getLines(String path) throws FileNotFoundException {
        List<String> store = new ArrayList<>();
        File f = new File(path);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            store.add(s.nextLine());
        }
        return store;
    }

}
