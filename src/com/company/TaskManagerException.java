package com.company;

public class TaskManagerException extends Exception{
    public TaskManagerException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
