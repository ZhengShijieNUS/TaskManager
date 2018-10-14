package com.company;

public class Converter {

    static boolean convertBoolean(String s) {
        return s.equals("1");
    }

    static String convertTaskToString(Task task) {
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
