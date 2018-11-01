package com.company.Tasks;

import java.util.Comparator;

/**
 * Task is a abstract class that define all the common attribute and method of a task.
 */
public abstract class Task{
    protected String content;
    protected String type;
    protected int priority;

    public Task(String content) {
        this.content = content;
    }

    /**
     * Get description of a task
     *
     * @return the description.
     */
    public String getDescription() {
        return this.content;
    }

    public String getDescription(boolean onlyDescription) {
        if (onlyDescription) {
            return this.content;
        } else {
            return getDescription();
        }
    }

    public String setDescription(String content) {
        return this.content = content;
    }

    public abstract void setDone(boolean isDone);

    public abstract String getType();

    public abstract boolean getIsDone();

    public abstract String getDoBy();

    protected void setPriority(int n){
        this.priority = n;
    }

    protected int getPriority(){
        return this.priority;
    }

}
