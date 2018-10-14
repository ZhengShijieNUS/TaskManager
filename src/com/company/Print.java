package com.company;

public class Print {

    static void printTasks(Ui UI, TaskList tasks) {
        UI.showMessage("Tasks:");
        for (int i = 0; i < tasks.getSize(); i++) {
            UI.showMessage("[" + (i + 1) + "] " + tasks.getTasksAtIndex(i).getDescription());
        }
    }

    static void printError(Ui UI) {
        UI.showMessage("Unknown command! please try again");
    }

    static void printError(String message) {
        System.out.println("Error: " + message);
    }

    static void TaskInList(TaskList tasks) {
        System.out.println("Task in the list: " + tasks.getSize());
    }

    static void PrintPath(Storage storage) {
        System.out.println("The file is successfully save to" + storage.getOutputPath());
    }

}
