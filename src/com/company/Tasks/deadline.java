package com.company.Tasks;

public class deadline extends todo {
    protected String doBy;

    public deadline(String content, String doBy) {
        super(content);
        this.doBy = doBy;
        this.type = "deadline";
        this.priority = 0;
    }

    public deadline(Boolean status, String content, String doBy) {
        super(status, content);
        this.doBy = doBy;
        this.type = "deadline";
        this.priority = 0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + System.lineSeparator() + "do by: " + doBy;
    }

    public String getDoBy() {
        return this.doBy;
    }
}
