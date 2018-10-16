package com.company.ProcessManageObject;

import com.company.Tasks.TaskList;

/**
 * TaskManager class is the main class that contain the procedure of how the TaskManager works.
 */
public class TaskManager {
    private String mode;

    public TaskManager() {
        this.mode = null;
    }

    /**
     * run() is the main method that contains the logic of how the program working.It will ask user to choose either File
     * Read mode or Key-in mode so that it proceed accordingly.After exit the mode, it will ask user whether to continue
     * or exit the program.
     */
    public void run() {
        TaskList tasks = new TaskList();
        Ui UI = new Ui();
        String line;
        boolean isContinue = true;
        UI.showMessage("Welcome to TaskManager-Level6!");
        UI.showMessage("Mode(File Read/Key-in): ");
        while (isContinue) {
            line = UI.readUserCommand();
            switch (line.trim()) {
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
            switch (UI.readUserCommand().trim()) {
                case "N":
                    isContinue = false;
                    break;
                default:
                    UI.showMessage("Do you want to remove all content in the previous TaskList?(Enter 'Y' to remove all");
                    line = UI.readUserCommand();
                    if (line.trim().equals("Y") || line.trim().equals("y")) {
                        tasks.removeAllTask();
                    }
                    UI.showMessage("Mode(File Read/Key-in): ");
            }
        }

        UI.showMessage("ByeBye!");

    }
}
