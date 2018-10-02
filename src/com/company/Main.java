package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    static Scanner in = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printWelcome();
        String line;
        line = getInput();
        if(line.equals("Key-in")){
            key_in();
        }
        else if(line.equals("File Read")){
            System.out.println("Input the file path: ");
            String path = getInput();
            //loading all tasks from txt file into task list
            if(loading_file(path)) {
                //updating tasks list until exit
                key_in();
                //saving all result into file
                try {
                    update_file(path);
                } catch (IOException e) {
                    printError("Problem encountered while updating file" + e.getMessage());
                }
            }
        }
        exit();

    }

    //***********************************Print method**********************************//

    private static void printWelcome() {
        System.out.println("Welcome to TaskManager-Level5!");
        System.out.println("Mode(File Read/Key-in/exit): ");
    }

    private static void printTasks() {
        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i).getDescription());
        }
    }

    private static void printError(){
        System.out.println("Unknown command! please try again");
    }

    private static void printError(String message){
        System.out.println("Error: " + message);
    }

    private static void TaskInList(){
        System.out.println("Task in the list: "+tasks.size());
    }

    private static void exit(){
        System.out.println("Bye!");
    }

    //************************************Procedure method******************************//
    private static void key_in(){
        String line;
        boolean isExit = false;
        while (!isExit) {
            try {
                System.out.print("Your task?");
                line = getInput();
                String command = line.split(" ")[0]; //extract the first word of the user input
                switch (command) {
                    case "exit":
                    case "": // exit if user input is empty
                        isExit = true;
                        break;
                    case "todo":
                        // todo: add code here
                        addTodo(line);
                        break;
                    case "deadline":
                        addDeadline(line);
                        break;
                    case "print":
                        // print all task
                        printTasks();
                        break;
                    case "done":
                        setCompleted(line);
                        break;
                    default:
                        printError();
                }
            } catch(TaskManagerException e){
                printError(e.getMessage());
            }
        }
    }


    private static void update_file(String path)throws IOException{
        //Files.delete(Paths.get(path));
        //FileWriter fw = new FileWriter(path,true);
        FileWriter fw = new FileWriter(path);
        for(Task task:tasks){
            String s=convertTaskToString(task);
            fw.write(s);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
    //************************************function method*******************************//

    //*******File read mode method
    private static boolean loading_file(String path){
        try {
            List<String> lines = getLines(path);
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                tasks.add(createTask(line)); //convert the line to a task and add to the list
            }
            return true;
        } catch (FileNotFoundException e) {
            printError("problem encountered while loading data: " + e.getMessage());
            return false;
        }
    }

    private static String convertTaskToString(Task task){
        String type;
        String isDone;
        String content;
        String doBy;

        if(task.getIsDone()){
            isDone = "1";
        }else{
            isDone = "0";
        }

        content = task.getDescription(true);

        String s = null;

        if(task.getType().equals("todo")){
            type = "T";
            s = type+" | "+isDone+" | "+content;
        }else if(task.getType().equals("deadline")){
            type = "D";
            doBy = task.getDoBy();
            s = type+" | "+isDone+" | "+content+" | "+doBy;
        }

        return s;
    }

    private static List<String> getLines(String path) throws FileNotFoundException {
        List<String> store = new ArrayList<>();
        File f = new File(path);
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            store.add(s.nextLine());
        }
        return store;
    }

    private static Task createTask(String line){
        String[] content=line.split("\\|");
        for(int i=0;i<content.length;i++){
            content[i]=content[i].trim();
        }
        switch(content[0]){
            case "T":
                return FileRead_createTodo(content[1],content[2]);
            case "D":
                return FileRead_createDeadline(content[1],content[2],content[3]);
        }

        return null;
    }

    private static Task FileRead_createTodo(String status,String content){
        return new todo(convertBoolean(status),content);
    }

    private static Task FileRead_createDeadline(String status,String content,String doBy){
        return new deadline(convertBoolean(status),content,doBy);
    }


    //**********************************
    private static String getInput(){
        return in.nextLine();
    }

    private static boolean convertBoolean(String s){
        if(s.equals("1")){
            return true;
        }
        else{
            return false;
        }
    }

    private static void setCompleted(String line){
        int index = Integer.parseInt(line.substring("done".length()).trim());
        tasks.get(index - 1).setDone(true);
        TaskInList();
    }

    private static void addTodo(String line) throws TaskManagerException {
        String description = line.substring("todo".length()).trim();
        if(description.isEmpty()){
            throw new TaskManagerException("Empty description for TODO");
        }else {
            tasks.add(new todo(description));
            TaskInList();
        }
    }

    private static void addDeadline(String line) throws TaskManagerException {
        int length_1="deadline".length();
        int length_2="/by".length();

        String description = line.substring("deadline".length()).trim();

        if(description.isEmpty()){
            throw new TaskManagerException("Empty description for DEADLINE");
        }
        else {
            if(description.contains("/by")) {
                tasks.add(new deadline(line.substring(line.indexOf("deadline") + length_1, line.indexOf("/by")).trim(), line.substring(line.indexOf("/by") + length_2).trim()));
                TaskInList();
            }
            else{
                throw new TaskManagerException("Empty description for /by");
            }
        }
    }
}
