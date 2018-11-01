package com.company.ProcessManageObject;

import com.company.ProcessMethodClass.Converter;
import com.company.ProcessMethodClass.Parser;
import com.company.ProcessMethodClass.Print;
import com.company.Tasks.TaskList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class that will help to load Tasks from the hard disk and save tasks to the hard disk.
 */
public class Storage {
    private String input_path;
    private String output_path;

    /**
     * Construct a Storage object.
     *
     * @param input_path is the input path of file.
     */
    public Storage(String input_path) {
        this.input_path = input_path;
        this.output_path = input_path;
    }

    /**
     * Get the output path that stored in the storage object.
     * @return the output file that user wants to store
     */
    public String getOutputPath() {
        return this.output_path;
    }

    /**
     * To set the output path as what input
     * @param output_path is the path that user wish to save to.
     */
    public void setOutput_path(String output_path) {
        this.output_path = output_path;
    }

    /**
     * The load method is used to load tasks from a file to a TaskList. If the path of input file is not found. It will
     * catch a exception and report to user.
     * @param tasks is the TaskList that stored all tasks.
     * @return true if load all tasks successfully.
     */
    public boolean load(TaskList tasks) {
        try {
            List<String> lines = getLines(input_path);
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                tasks.addTask(Parser.createTask(line));
            }
            return true;
        } catch (FileNotFoundException e) {
            Print.printError("problem encountered while loading data: " + e.getMessage());
            return false;
        }
    }

    /**
     * The save method is to save all the tasks from TaskList to a file.
     * @param tasks is the TaskList that user use.
     * @throws IOException if the the output path is wrong.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(output_path);
        for (int i = 0; i < tasks.getSize(); i++) {
            String s = Converter.convertTaskToString(tasks.getTasksAtIndex(i));
            fw.write(s);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * The getLines is a helper function that retrieve data from file and store all content in a List of String.
     * @param path is the input path that user provide.
     * @return a String list
     * @throws FileNotFoundException if the file is not found.
     */
    //helper function on read files
    private static List<String> getLines(String path) throws FileNotFoundException {
        List<String> store = new ArrayList<>();
        File f = new File(path);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            store.add(s.nextLine());
        }
        return store;
    }
}
