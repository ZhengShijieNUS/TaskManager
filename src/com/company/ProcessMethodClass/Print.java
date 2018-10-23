package com.company.ProcessMethodClass;

import com.company.ProcessManageObject.Storage;
import com.company.ProcessManageObject.Ui;
import com.company.Tasks.TaskList;

/**
 * Print method is mainly in charge of printing output.
 */
public class Print {
    /**
     * Print all the tasks in the task list.
     *
     * @param UI    is the Ui which interact with user.
     * @param tasks is the TaskList that user wish to print out.
     */
    public static void printTasks(Ui UI, TaskList tasks) {
        UI.showMessage("Tasks:");
        for (int i = 0; i < tasks.getSize(); i++) {
            UI.showMessage("[" + (i + 1) + "] " + tasks.getTasksAtIndex(i).getDescription());
        }
    }

    /**
     * The PrintError with Ui parameter intake is method to print message when command is unknown.
     * @param UI is the Ui which interact with user.
     */
    public static void printError(Ui UI) {
        UI.showMessage("Unknown command! please try again");
    }

    /**
     * The PrintError with a String intake is method that print message when exception is throw.
     * @param message is the message that exception throws.
     */
    public static void printError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * The TaskInList is the method to print how many tasks are stored in the TaskList.
     * @param tasks is the TaskList that user interact with.
     */
    public static void printTaskInList(TaskList tasks) {
        System.out.println("Task in the list: " + tasks.getSize());
    }

    /**
     * The PrintPath is method that show the path that output is stored successfully.
     * @param storage is the storage that output stored.
     */
    public static void printPath(Storage storage) {
        System.out.println("The file is successfully save to" + storage.getOutputPath());
    }

}
