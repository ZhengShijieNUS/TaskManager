package com.company.Tasks;

/**
 * todo class is create a todo task.It's inheriting from Task class
 */
public class todo extends Task {
    protected boolean isDone;

    /**
     * Construct a todo task object
     *
     * @param content is the content that this todo task has.
     */
    public todo(String content) {
        super(content);
        this.isDone = false;
        this.type = "todo";
    }

    /**
     * Construct a todo task when the status of this todo task is given
     * @param status is the status of todo task
     * @param content is the content of the todo task.
     */
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
