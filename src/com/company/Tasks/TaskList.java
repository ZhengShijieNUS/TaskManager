package com.company.Tasks;

import com.company.Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task getTasksAtIndex(int n) {
        return tasks.get(n);
    }

    public Task removeTask(int n) {
        return tasks.remove(n);
    }

    public void removeAllTask() {
        tasks.clear();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getSize() {
        return tasks.size();
    }

    public void modifyDescription(int n, String content) {
        getTasksAtIndex(n).setDescription(content);
    }

}
