package com.company.ProcessMethodClass;

import com.company.ProcessManageObject.Storage;
import com.company.ProcessManageObject.TaskManagerException;
import com.company.ProcessManageObject.Ui;
import com.company.Tasks.TaskList;

import java.io.IOException;

/**
 * Process class stores all process that involved.Key-in mode and File Read mode implementation is defined here.
 */
public class Process {

    public Process() {
    }

    /**
     * The setCompleted method is a process that set the status of a specified task to be done.
     *
     * @param tasks is the specified task that wish to set to completed
     * @param line  is the command,it should be like "done 5","done 3" etc.
     * @throws NumberFormatException if the input command is illegal
     */
    private static void setCompleted(TaskList tasks, String line) throws NumberFormatException {
        try {
            int index = Integer.parseInt(line.substring("done".length()).trim());
            tasks.getTasksAtIndex(index - 1).setDone(true);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("The content format is wrong.");
        }
    }

    //key_in mode

    /**
     * The key_in method is the main process of the "Key-in" mode, it intakes a Ui object and TaskList object to process
     * the key in mode.
     * User can key in different command to modify the TaskList.
     * The command keyword including: exit,todo,deadline,print,done,remove,removeAll,modify
     * It will keep repeating until user enter "exit" or nothing entered.
     * @param UI is the Ui that interact with user
     * @param tasks is the TaskList that store all the tasks
     */
    public void keyInMode(Ui UI, TaskList tasks) {
        Print print = new Print();
        String line;
        boolean isExit = false;
        while (!isExit) {
            try {
                UI.showMessage("Your task?");
                UI.showMessage("Command List:todo/deadline/print/exit/remove (index)/removeAll/modify(with)/priority(with)/sortByASC/sortByDSC/removeCompletedTask");
                line = UI.readUserCommand();
                switch (Parser.getCommandWord(line)) {
                    case "exit":
                    case "": // exit if user input is empty
                        isExit = true;
                        break;
                    case "todo":
                        // todo: add code here
                        tasks.addTask(Parser.createTodo(line));
                        print.printTaskInList(tasks);
                        break;
                    case "deadline":
                        tasks.addTask(Parser.createDeadline(line));
                        print.printTaskInList(tasks);
                        break;
                    case "print":
                        // print all task
                        print.printTasks(UI, tasks);
                        break;
                    case "done":
                        if (checkValidity(tasks, Parser.getIndex(line))) {
                            setCompleted(tasks, line);
                        }
                        print.printTaskInList(tasks);
                        break;
                    case "remove":
                        if (checkValidity(tasks, Parser.getIndex(line))) {
                            tasks.removeTask(Parser.getIndex(line) - 1);
                        }
                        print.printTaskInList(tasks);
                        break;
                    case "removeAll":
                        tasks.removeAllTask();
                        UI.showMessage("All tasks have been successfully removed.");
                        print.printTaskInList(tasks);
                        break;
                    case "removeCompletedTask":
                        tasks.removeAllCompletedTask();
                        UI.showMessage("Remove all completed task successfully");
                        print.printTaskInList(tasks);
                        break;
                    case "modify":
                        if (checkValidity(tasks, Parser.getIndex(line))) {
                            tasks.modifyDescription(Parser.getIndex(line) - 1, Parser.getContent(line));
                        }
                        UI.showMessage("The content of task at "+Integer.toString(Parser.getIndex(line))+" has been modified");
                        break;
                    case"priority":
                        if(checkValidity(tasks,Parser.getIndex(line))) {
                            tasks.setPriority(Parser.getIndex(line) - 1, Integer.parseInt(Parser.getContent(line)));
                        }
                        UI.showMessage("The priority of task at "+Integer.toString(Parser.getIndex(line))+" has been changed to "+Parser.getContent(line));
                        break;
                    case"sortByASC":
                        tasks.orderByPriority(Parser.getCommandWord(line));
                        print.printTasks(UI, tasks);
                        break;
                    case"sortByDSC":
                        tasks.orderByPriority(Parser.getCommandWord(line));
                        print.printTasks(UI, tasks);
                        break;
                    default:
                        print.printError(UI);
                }
                UI.showMessage(System.lineSeparator());
            } catch(Exception e){
                Print.printError(e.getMessage());
            }
        }
    }

    //File Read mode

    /**
     * The FileReadMode is the method that intakes a Ui and TaskList that created.
     * User should enter the path of input file.
     * User may also choose whether to save the output into the same input file or into another file.
     * After load all the tasks into the TaskList, user will enter the key-in mode and modify TaskList accordingly.
     * All the content in the TaskList will be stored in the output file path after exit the key-in mode.
     * @param UI is the Ui that interact with user
     * @param tasks is the TaskList that store all the tasks
     */
    public void readFileMode(Ui UI, TaskList tasks) {
        Process process = new Process();
        Print print = new Print();
        UI.showMessage("Please enter the input file path: ");
        Storage storage = new Storage(UI.readUserCommand());
        UI.showMessage("Save to same file?(Enter 'N' to save in different files,default will be save to the same file) ");
        String command = UI.readUserCommand();
        if (command.equals("N") || command.equals("n")) {
            UI.showMessage("Please enter the output file path: ");
            storage.setOutput_path(UI.readUserCommand());
        }

        if (storage.load(tasks)) {
            process.keyInMode(UI, tasks);
            try {
                storage.save(tasks);
                print.printPath(storage);
            } catch (IOException e) {
                UI.showMessage("Problem encountered while updating file" + e.getMessage());
            }
        }
    }

    /**
     * The checkValidity is a process method that to check the input by user is legal.
     * It's used for the command "done","remove","modify"
     * @param tasks is the TaskList need to modified.
     * @param n is the index that user wish to make changes.
     * @return true if the input is valid.
     * @throws TaskManagerException if the input is illegal.
     */
    public static boolean checkValidity(TaskList tasks, int n) throws TaskManagerException {
        if (tasks.isEmpty()) {
            throw new TaskManagerException("The task list is currently empty.");
        } else {
            if ((n >= 0) && (n <= tasks.getSize())) {
                return true;
            } else {
                throw new TaskManagerException("The content is invalid.It may caused by invalid format,content and etc.");
            }
        }
    }
}
