package com.company.ProcessManageObject;

import java.util.Scanner;

/**
 * The Ui class that will be responsible for interacting with the user.
 */
public class Ui {
    private static Scanner in;

    /**
     * Construct a UI object.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * To show a message that wants to show to user.
     *
     * @param line is the message
     */
    public void showMessage(String line){
        System.out.println(line);
    }

    /**
     * Get the user's input from keyboard.
     * @return a string of user's input.
     */
    public String readUserCommand(){
        return in.nextLine().trim();
    }

}
