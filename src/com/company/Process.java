package com.company;

import java.io.IOException;

class Process {

    private static void setCompleted(TaskList tasks, String line) throws NumberFormatException {
        try {
            int index = Integer.parseInt(line.substring("done".length()).trim());
            tasks.getTasksAtIndex(index - 1).setDone(true);
            Print.TaskInList(tasks);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("The content format is wrong.");
        }
    }

    //key_in mode
    static void key_in(Ui UI, TaskList tasks) {
        String line;
        boolean isExit = false;
        while (!isExit) {
            try {
                UI.showMessage("Your task?(todo/deadline/print/exit/remove (index)/removeAll/modify(with)");
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
                        Print.printTasks(UI, tasks);
                        break;
                    case "done":
                        if (checkValidity(tasks, Parser.getIndex(line))) {
                            Process.setCompleted(tasks, line);
                        }
                        break;
                    case "remove":
                        if (checkValidity(tasks, Parser.getIndex(line))) {
                            tasks.removeTask(Parser.getIndex(line) - 1);
                        }
                        break;
                    case "removeAll":
                        tasks.removeAllTask();
                        break;
                    case "modify":
                        if (checkValidity(tasks, Parser.getIndex(line))) {
                            tasks.modifyDescription(Parser.getIndex(line) - 1, Parser.getContent(line));
                        }
                        break;
                    default:
                        Print.printError(UI);
                }
            } catch (TaskManagerException e) {
                Print.printError(e.getMessage());
            } catch(Exception e){
                Print.printError(e.getMessage());
            }
        }
    }

    //File Read mode
    static void FileReadMode(Ui UI, TaskList tasks) {
        UI.showMessage("Please enter the input file path: ");
        Storage storage = new Storage(UI.readUserCommand());
        UI.showMessage("Save to same file?(Enter 'N' to save in different files,default will be save to the same file) ");
        String command = UI.readUserCommand();
        if (command.equals("N") || command.equals("n")) {
            UI.showMessage("Please enter the output file path: ");
            storage.setOutput_path(UI.readUserCommand());
        }

        if (storage.load(tasks)) {
            Process.key_in(UI, tasks);
            try {
                storage.save(tasks);
                Print.PrintPath(storage);
            } catch (IOException e) {
                UI.showMessage("Problem encountered while updating file" + e.getMessage());
            }
        }
    }

    static boolean checkValidity(TaskList tasks, int n) throws TaskManagerException {
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
