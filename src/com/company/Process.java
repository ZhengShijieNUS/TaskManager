package com.company;

import java.io.IOException;

class Process {

    private static void setCompleted(TaskList tasks,String line){
        int index = Integer.parseInt(line.substring("done".length()).trim());
        tasks.getTasksAtIndex(index - 1).setDone(true);
        Print.TaskInList(tasks);
    }

    //key_in mode
    static void key_in(Ui UI,TaskList tasks){
        String line;
        boolean isExit = false;
        while (!isExit) {
            try {
                UI.showMessage("Your task?(todo/deadline/print/exit/remove index/removeAll");
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
                        if((Parser.getIndex(line)<1)||(Parser.getIndex(line)>tasks.getSize())){
                            throw new TaskManagerException("The range is out of tasks size,please re-enter the value.");
                        }
                        Process.setCompleted(tasks,line);
                        break;
                    case "remove":
                        if(tasks.isEmpty()){
                            throw new TaskManagerException("Task list is empty, you are not able to remove.");
                        }
                        if((Parser.getIndex(line)<1)||(Parser.getIndex(line)>tasks.getSize())){
                            throw new TaskManagerException("The range is out of tasks size,please re-enter the value.");
                        }
                        tasks.removeTask(Parser.getIndex(line)-1);
                        break;
                    case"removeAll":
                        if(tasks.isEmpty()){
                            throw new TaskManagerException("Task list is empty, you are not able to remove.");
                        }
                        tasks.removeAllTask();
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
    static void FileReadMode(Ui UI,TaskList tasks){
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
                Print.Printpath(storage);
            }catch(IOException e){
                UI.showMessage("Problem encountered while updating file"+e.getMessage());
            }
        }
    }
}
