package com.company.ProcessMethodClass;

import com.company.ProcessManageObject.TaskManagerException;
import com.company.Tasks.Task;
import com.company.Tasks.deadline;
import com.company.Tasks.todo;

/**
 * Parser class is used to parse the command/input and give the correct output that we want.
 * It is used to get command/index/content and also used to create tasks under certain mode(FileRead/Key-in).
 */
public class Parser {

    /**
     * The getCommand method is to read the first word of user's input as command.
     *
     * @param fullCommand is the input that user entered.
     * @return the first word as command key word.
     */
    public static String getCommandWord(String fullCommand) {
        return fullCommand.split(" ")[0];//extract the first word of the user input
    }

    /**
     * The getIndex method is to get the index that user key-in. It's used for command such as "done","remove"
     * and "modify".
     * @param fullCommand is the input that user entered.
     * @return integer of the index
     * @throws Exception if the content entered is illegal.Eg:"done    ", "remove ad" etc.
     */
    public static int getIndex(String fullCommand) throws Exception{
        try {
            return Integer.parseInt(fullCommand.split(" ")[1]);
        } catch (Exception e) {
            throw new Exception("The content format is wrong.");
        }
    }

    /**
     * The getContent is specially method used for "modify" command.Check whether input includes keyword "with",if so,
     * will return the content that want to modify.
     * @param fullCommand is the input that user entered.
     * @return the string of content
     * @throws TaskManagerException if the command is without "with" keyword.
     */
    public static String getContent(String fullCommand) throws TaskManagerException {
        if (fullCommand.contains("with")) {
            return fullCommand.substring(fullCommand.indexOf("with") + "with".length()).trim();
        } else {
            throw new TaskManagerException("Command is without keyword with");
        }

    }

    //Key-in method

    /**
     * The createTodo method is specially used under key-in mode that to create a "todo" task.
     * @param fullCommand is the input that user entered.
     * @return a new todo task if successfully.
     * @throws TaskManagerException if there is no content with users' input.
     */
    public static todo createTodo(String fullCommand) throws TaskManagerException {
        String description = fullCommand.substring(getCommandWord(fullCommand).length()).trim();
        if (description.isEmpty()) {
            throw new TaskManagerException("Empty description for TODO");
        } else {
            return new todo(description);
        }
    }

    /**
     * The createDeadline method is specially used under key-in mode to create a "deadline" task.
     * @param fullCommand is the input that user entered.
     * @return a new deadline task if successfully.
     * @throws TaskManagerException if there is no content or there is no keyword "/by".
     */
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

    /**
     * The createTask method is specially used under "File Read" mode.It will convert the String that read from a file
     * and convert it to a targeted task.
     * @param line is the string that read from a file.
     * @return a specialized task(either todo or deadline).
     */
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

    /**
     * The FileRead_createTodo method is used to create a todo task under "File Read" mode
     * @param status is the "have done" status that read from a file(either true or false)
     * @param content is the content that read from a file
     * @return a new todo task.
     */
    public static Task FileRead_createTodo(String status, String content) {
        return new todo(Converter.convertBoolean(status), content);
    }

    /**
     * The FileRead_createTodo method is used to create a deadline task under "File Read" mode
     * @param status is the "have done" status that read from a file(either true or false)
     * @param content is the content that read from a file
     * @param doBy is the deadline time that specified.
     * @return a new deadline task.
     */
    static Task FileRead_createDeadline(String status, String content, String doBy) {
        return new deadline(Converter.convertBoolean(status), content, doBy);
    }

}
