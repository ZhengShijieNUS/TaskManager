package com.company.ProcessMethodClass;

import com.company.Tasks.Task;

/**
 * Converter is a class that provide all kinds of converting methods,such as task to string, string to boolean etc.
 */
public class Converter {

    /**
     * The convertBoolean method is a helper function that helps to confirm input string is "1".
     *
     * @param s is the input string
     * @return true while s is equal to "1"
     */
    public static boolean convertBoolean(String s) {
        return s.equals("1");
    }


    /**
     * The ConvertTaskToString is a method that converts a task to a readable string and return
     * @param task is the input task
     * @return readable a string of the task
     */
    public static String convertTaskToString(Task task) {
        String type;
        String isDone;
        String content;
        String doBy;

        if (task.getIsDone()) {
            isDone = "1";
        } else {
            isDone = "0";
        }

        content = task.getDescription(true);

        String s = null;

        if (task.getType().equals("todo")) {
            type = "T";
            s = type + " | " + isDone + " | " + content;
        } else if (task.getType().equals("deadline")) {
            type = "D";
            doBy = task.getDoBy();
            s = type + " | " + isDone + " | " + content + " | " + doBy;
        }

        return s;
    }

}
