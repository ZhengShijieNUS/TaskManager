package com.company;

public abstract class Task {
    protected String content;
    protected String type;

    public Task(String content) {
        this.content = content;
    }

    public String getDescription(){
        return this.content;
    }

    public String getDescription(boolean onlyDescription){
        if(onlyDescription) {
            return this.content;
        }
        else{
            return getDescription();
        }
    }

    public abstract void setDone(boolean isDone);

    public abstract String getType();

    public abstract boolean getIsDone();

    public abstract String getDoBy();
}
