package com.company.ProcessMethodClass;

import com.company.ProcessManageObject.TaskManagerException;
import com.company.Tasks.Task;
import com.company.Tasks.deadline;
import com.company.Tasks.todo;

public class Parser {

    public static String getCommandWord(String fullCommand) {
        return fullCommand.split(" ")[0];//extract the first word of the user input
    }

    public static int getIndex(String fullCommand) throws Exception{
        try {
            return Integer.parseInt(fullCommand.split(" ")[1]);
        } catch (Exception e) {
            throw new Exception("The content format is wrong.");
        }
    }

    public static String getContent(String fullCommand) throws TaskManagerException {
        if (fullCommand.contains("with")) {
            return fullCommand.substring(fullCommand.indexOf("with") + "with".length()).trim();
        } else {
            throw new TaskManagerException("Command is without keyword with");
        }

    }

    //Key-in method
    public static todo createTodo(String fullCommand) throws TaskManagerException {
        String description = fullCommand.substring(getCommandWord(fullCommand).length()).trim();
        if (description.isEmpty()) {
            throw new TaskManagerException("Empty description for TODO");
        } else {
            return new todo(description);
        }
    }

    public static deadline createDeadline(String fullCommand) throws TaskManagerException {
        String description = fullCommand.substring(getCommandWord(fullCommand).length()).trim();
        if (description.isEmpty()) {
            throw new TaskManagerException("Empty description for DEADLINE");
        } else {
            if (description.contains("/by")) {
                return new deadline(fullCommand.substring(fullCommand.indexOf("deadline") + "deadline".length(), fullCommand.indexOf("/by")).trim(), fullCommand.substring(fullCommand.indexOf("/by") + "/by".length()).trim());
            } else {
                throw new TaskManagerException("Empty description for /by");
            }
        }
    }


    //File-Read mode method
    public static Task createTask(String line) {
        String[] content = line.split("\\|");
        for (int i = 0; i < content.length; i++) {
            content[i] = content[i].trim();
        }
        switch (content[0]) {
            case "T":
                return FileRead_createTodo(content[1], content[2]);
            case "D":
                return FileRead_createDeadline(content[1], content[2], content[3]);
        }

        return null;
    }

    public static Task FileRead_createTodo(String status, String content) {
        return new todo(Converter.convertBoolean(status), content);
    }

    static Task FileRead_createDeadline(String status, String content, String doBy) {
        return new deadline(Converter.convertBoolean(status), content, doBy);
    }

}
