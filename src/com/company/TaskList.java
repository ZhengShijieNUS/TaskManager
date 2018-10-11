package com.company;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(){

    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t){
        this.tasks.add(t);
    }

    public Task getTasksAtIndex(int n) {
        return tasks.get(n);
    }

    public Task removeTask(int n){
        return tasks.remove(n);
    }

    public boolean removeTask(Task T){
        return tasks.remove(T);
    }

    public void removeAllTask(){
        tasks.clear();
    }

    public boolean isEmpty(){
        return tasks.isEmpty();
    }

    public int getSize(){
        return tasks.size();
    }


}
