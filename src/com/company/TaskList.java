package com.company;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList(){
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void addTask(Task t){
        this.tasks.add(t);
    }

    Task getTasksAtIndex(int n) {
        return tasks.get(n);
    }

    Task removeTask(int n){
        return tasks.remove(n);
    }

    void removeAllTask(){
        tasks.clear();
    }

    boolean isEmpty(){
        return tasks.isEmpty();
    }

    int getSize(){
        return tasks.size();
    }

}
