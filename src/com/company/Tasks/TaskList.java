package com.company.Tasks;

/**
 * The TaskList class that is responsible for keeping the in-memory task list.
 */
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Construct a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add new task into TaskList
     *
     * @param t is the task that user wish to add in.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Get a task at a specified index
     * @param n is the index that user provides
     * @return the task at the index specified.
     */
    public Task getTasksAtIndex(int n) {
        return tasks.get(n);
    }

    /**
     * Remove the task at the index.
     * @param n is the index that user provides.
     * @return the task at the index.
     */
    public Task removeTask(int n) {
        return tasks.remove(n);
    }

    /**
     * Remove all the task in the current TaskList
     */
    public void removeAllTask() {
        tasks.clear();
    }

    /**
     * To check whether the TaskList is empty.
     * @return true if it's empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * To get the size of the TaskList
     * @return the size of the TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * To modify the content at the index provided in the TaskList
     * @param n is the index that user provides
     * @param content is the content that user wish to change.
     */
    public void modifyDescription(int n, String content) {
        getTasksAtIndex(n).setDescription(content);
    }

    public void setPriority(int index,int priority){getTasksAtIndex(index).setPriority(priority);}

    public void orderByPriority(String line){
        if(line.equals("orderByASC")){
            tasks.sort(new SortByASC());
        }else if(line.equals("orderByDSC")){
            tasks.sort(new SortByDSC());
        }
    }

    public void removeAllCompletedTask(){
        for(Task t:tasks){
            if(t.getIsDone()){
                tasks.remove(t);
            }
        }
    }
}
