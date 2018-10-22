package com.company.ProcessMethodClass;

import org.junit.jupiter.api.Test;
import com.company.Tasks.TaskList;
import com.company.Tasks.Task;
import com.company.Tasks.todo;
import com.company.Tasks.deadline;

import static org.junit.jupiter.api.Assertions.*;

class ProcessTest {
    TaskList tasks = new TaskList();
    todo task1= new todo("watch a movie");
    deadline task2=new deadline("see a doctor","this friday");

    @Test
    void checkValidity() throws Exception{
        tasks.addTask(task1);
        tasks.addTask(task2);
        assertEquals(true,Process.checkValidity(tasks,1));
        assertEquals(true,Process.checkValidity(tasks,0));

    }

    @Test
    void checkValidity_exception(){
        Throwable exception = assertThrows(Exception.class, () -> {
            Process.checkValidity(tasks,2);
        });
        assertEquals("The task list is currently empty.", exception.getMessage());

        tasks.addTask(task1);
        tasks.addTask(task2);
        exception = assertThrows(Exception.class, () -> {
            Process.checkValidity(tasks,4);
        });
        assertEquals("The content is invalid.It may caused by invalid format,content and etc.", exception.getMessage());

    }
}