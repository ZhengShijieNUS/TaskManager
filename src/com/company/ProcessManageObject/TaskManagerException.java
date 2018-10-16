package com.company.ProcessManageObject;

/**
 * TaskManagerException class is used to manage all the exceptions and customize the report messages.
 */
public class TaskManagerException extends Exception {
    public TaskManagerException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
