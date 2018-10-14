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

public class Storage {
    private String input_path;
    private String output_path;

    public Storage(String input_path) {
        this.input_path = input_path;
        this.output_path = input_path;
    }

    public String getOutputPath() {
        return this.output_path;
    }

    public void setOutput_path(String output_path) {
        this.output_path = output_path;
    }

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

    public void save(TaskList tasks) throws IOException {
        //Files.delete(Paths.get(path));
        //FileWriter fw = new FileWriter(path,true);
        FileWriter fw = new FileWriter(output_path);
        for (int i = 0; i < tasks.getSize(); i++) {
            String s = Converter.convertTaskToString(tasks.getTasksAtIndex(i));
            fw.write(s);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    //helper function on read files
    public static List<String> getLines(String path) throws FileNotFoundException {
        List<String> store = new ArrayList<>();
        File f = new File(path);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            store.add(s.nextLine());
        }
        return store;
    }
}
