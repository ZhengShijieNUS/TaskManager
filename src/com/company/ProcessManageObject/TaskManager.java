package com.company.ProcessManageObject;

import com.company.Tasks.TaskList;

public class TaskManager {
    private String mode;

    public TaskManager() {
        this.mode = null;
    }

    public void run() {
        TaskList tasks = new TaskList();
        Ui UI = new Ui();
        String line;
        boolean isContinue = true;
        UI.showMessage("Welcome to TaskManager-Level6!");
        UI.showMessage("Mode(File Read/Key-in): ");
        while (isContinue) {
            line = UI.readUserCommand();
            switch (line) {
                case "File Read":
                    UI.showMessage("You enter the File Read mode successfully");
                    com.company.ProcessMethodClass.Process.FileReadMode(UI, tasks);
                    break;
                case "Key-in":
                    UI.showMessage("You enter the key-in mode successfully");
                    com.company.ProcessMethodClass.Process.key_in(UI, tasks);
                    break;
            }

            //To confirm with user whether to continue the program.
            UI.showMessage("Do you want to continue?(enter 'N' to exit or enter any value to continue)");
            switch (UI.readUserCommand()) {
                case "N":
                    isContinue = false;
                    break;
                default:
                    UI.showMessage("Mode(File Read/Key-in): ");
            }
        }

        UI.showMessage("ByeBye!");

    }
}
