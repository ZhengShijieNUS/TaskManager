package com.company;

import java.io.IOException;

public class TaskManager {
    private String mode;

    public TaskManager() {
        this.mode = null;
    }

    void run(){
        TaskList tasks = new TaskList();
        Ui UI = new Ui();
        String line;
        UI.showMessage("Welcome to TaskManager-Level6!");
        UI.showMessage("Mode(File Read/Key-in/exit): ");
        line = UI.readUserCommand();
        if(line.equals("File Read")){
            UI.showMessage("You enter the File Read mode successfully");
            Process.FileReadMode(UI,tasks);
        }else if(line.equals("Key-in")){
            UI.showMessage("You enter the key-in mode successfully");
            Process.key_in(UI,tasks);
        }

        UI.showMessage("Bye!");

    }

    public void setMode(String mode){
        this.mode = mode;
    }

}
