package com.company;

public class Print {

    public static void printTasks(Ui UI,TaskList tasks) {
        UI.showMessage("Tasks:");
        for (int i = 0; i < tasks.getSize(); i++) {
            UI.showMessage("[" + (i + 1) + "] " + tasks.getTasksAtIndex(i).getDescription());
        }
    }

    public static void printError(Ui UI){
        UI.showMessage("Unknown command! please try again");
    }

    public static void printError(String message){
        System.out.println("Error: " + message);
    }

    public static void TaskInList(TaskList tasks){
        System.out.println("Task in the list: "+tasks.getSize());
    }

}
