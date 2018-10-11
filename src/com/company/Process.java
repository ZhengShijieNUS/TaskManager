package com.company;

import java.io.IOException;

public class Process {

    public static void setCompleted(TaskList tasks,String line){
        int index = Integer.parseInt(line.substring("done".length()).trim());
        tasks.getTasksAtIndex(index - 1).setDone(true);
        Print.TaskInList(tasks);
    }

    //key_in mode
    public static void key_in(Ui UI,TaskList tasks){
        String line;
        boolean isExit = false;
        while (!isExit) {
            try {
                UI.showMessage("Your task?");
                line = UI.readUserCommand();
                switch (Parser.getCommandWord(line)) {
                    case "exit":
                    case "": // exit if user input is empty
                        isExit = true;
                        break;
                    case "todo":
                        // todo: add code here
                        tasks.addTask(Parser.createTodo(line));
                        Print.TaskInList(tasks);
                        break;
                    case "deadline":
                        tasks.addTask(Parser.createDeadline(line));
                        Print.TaskInList(tasks);
                        break;
                    case "print":
                        // print all task
                        Print.printTasks(UI,tasks);
                        break;
                    case "done":
                        Process.setCompleted(tasks,line);
                        break;
                    default:
                        Print.printError(UI);
                }
            } catch(TaskManagerException e){
                Print.printError(e.getMessage());
            }
        }
    }

    //File Read mode
    public static void FileReadMode(Ui UI,TaskList tasks){
        UI.showMessage("Please enter the input file path: ");
        Storage storage = new Storage(UI.readUserCommand());
        UI.showMessage("Save to same file?(Y/N)  ");
        if(UI.readUserCommand().equals("N")) {
            UI.showMessage("Please enter the output file path: ");
            storage.setOutput_path(UI.readUserCommand());
        }

        if(storage.load(tasks)){
            Process.key_in(UI,tasks);
            try {
                storage.save(tasks);
            }catch(IOException e){
                UI.showMessage("Problem encountered while updating file"+e.getMessage());
            }
        }
    }
}
