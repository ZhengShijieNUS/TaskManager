package com.company;

import java.io.IOException;

public class TaskManager {
    private String mode;

    public TaskManager() {
        this.mode = null;
    }

    public void run(){
        TaskList tasks = new TaskList();
        Ui UI = new Ui();
        UI.showMessage("Welcome to TaskManager-Level6!");
        UI.showMessage("Mode(File Read/Key-in/exit): ");
        if(UI.readUserCommand().equals("File Read")){
            Process.FileReadMode(UI,tasks);
        }else if(UI.readUserCommand().equals("Key-in")){
            Process.key_in(UI,tasks);
        }

        UI.showMessage("Bye!");

    }

    public void setMode(String mode){
        this.mode = mode;
    }

}
