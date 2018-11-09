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
    void printTasks(Ui UI, TaskList tasks) {
        UI.showMessage("Tasks:");
        for (int i = 0; i < tasks.getSize(); i++) {
            UI.showMessage("[" + (i + 1) + "] " + tasks.getTasksAtIndex(i).getDescription());
        }
    }

    /**
     * The PrintError with Ui parameter intake is method to print message when command is unknown.
     * @param UI is the Ui which interact with user.
     */
    void printError(Ui UI) {
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
    void printTaskInList(TaskList tasks) {
        System.out.println("Task in the list: " + tasks.getSize());
    }

    /**
     * The PrintPath is method that show the path that output is stored successfully.
     * @param storage is the storage that output stored.
     */
    void printPath(Storage storage) {
        System.out.println("The file is successfully save to" + storage.getOutputPath());
    }

    void printHelp(Ui UI){
        UI.showMessage("Operation command:");
        UI.showMessage("    exit: 'exit'.");
        UI.showMessage("    print all tasks in task list:'print'");
        UI.showMessage("Create tasks command:");
        UI.showMessage("    create a todo task: 'todo + (content)'.");
        UI.showMessage("    create a deadline task:'deadline + (content) + /by + (content)'");
        UI.showMessage("Tasks modification command:");
        UI.showMessage("    remove tasks at index n: 'remove + (index)'.");
        UI.showMessage("    remove all completed tasks:'removeCompletedTask'");
        UI.showMessage("    remove all tasks:'removeAll'");
        UI.showMessage("    modify a task content: 'modify + (index) + with + (content)'.");
        UI.showMessage("    set a task with a certain priority:'priority + (index) + with + (priority)'");
        UI.showMessage("    sort the task list by ascending order:'sortByASC'");
        UI.showMessage("    sort the task list by descending order:'sortByDSC'");
    }

}
