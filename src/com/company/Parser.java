package com.company;

public class Parser {

    public static String getCommandWord(String fullCommand) {
        String command = fullCommand.split(" ")[0];//extract the first word of the user input
        return command;
    }

    //Key-in method
    public static todo createTodo(String fullCommand)throws TaskManagerException{
        String description = fullCommand.substring(getCommandWord(fullCommand).length()).trim();
        if(description.isEmpty()){
            throw new TaskManagerException("Empty description for TODO");
        }
        else {
            return new todo(description);
        }
    }


    public static deadline createDeadline(String fullCommand)throws TaskManagerException{
        int length_1="deadline".length();
        int length_2="/by".length();

        String description = fullCommand.substring(getCommandWord(fullCommand).length()).trim();
        if(description.isEmpty()){
            throw new TaskManagerException("Empty description for DEADLINE");
        }
        else {
            if (description.contains("/by")){
                return new deadline(fullCommand.substring(fullCommand.indexOf("deadline") + length_1, fullCommand.indexOf("/by")).trim(), fullCommand.substring(fullCommand.indexOf("/by") + length_2).trim());
            }
            else{
                throw new TaskManagerException("Empty description for /by");
            }
        }


    }


    //File-Read mode method
    public static Task createTask(String line){
        String[] content=line.split("\\|");
        for(int i=0;i<content.length;i++){
            content[i]=content[i].trim();
        }
        switch(content[0]){
            case "T":
                return FileRead_createTodo(content[1],content[2]);
            case "D":
                return FileRead_createDeadline(content[1],content[2],content[3]);
        }

        return null;
    }

    public static Task FileRead_createTodo(String status,String content){
        return new todo(Converter.convertBoolean(status),content);
    }

    public static Task FileRead_createDeadline(String status,String content,String doBy){
        return new deadline(Converter.convertBoolean(status),content,doBy);
    }

}
