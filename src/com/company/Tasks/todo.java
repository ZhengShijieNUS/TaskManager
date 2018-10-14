package com.company.Tasks;

public class todo extends Task {
    protected boolean isDone;

    public todo(String content) {
        super(content);
        this.isDone = false;
        this.type = "todo";
    }

    public todo(boolean status, String content) {
        super(content);
        this.isDone = status;
        this.type = "todo";
    }

    @Override
    public String getDescription() {
        String done;
        if (isDone) {
            done = "Yes";
        } else {
            done = "No";
        }
        return super.getDescription() + System.lineSeparator() + "is done? " + done;
    }

    public String getDescription(boolean onlyDescription) {
        if (onlyDescription) {
            return this.content;
        } else {
            return getDescription();
        }
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getType() {
        return this.type;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDoBy() {
        return null;
    }
}