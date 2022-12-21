package org.example;

import java.util.ArrayList;
import java.util.List;

public class Undo {
    private static Undo undoInstance;
    private List<String> commandStorage;
    {
        commandStorage = new ArrayList<>();
    }
    public void lookAllCommands(){
        if(commandStorage.size() == 0){
            throw new IllegalArgumentException("Index not found");
        }
        commandStorage.forEach(System.out::println);
    }


    //this method has to show the last commands
    public void lookXCommands(int number) {
        if (commandStorage.size() == 0) {
            throw new IllegalArgumentException("Index not found");
        } else if (commandStorage.size() < number) {
            commandStorage.forEach(System.out::println);
            System.out.println("You have only " + commandStorage.size() +
                    " commands and the introduced number is greater than total amount of commands");
        } else if (commandStorage.size() == number) {
            commandStorage.forEach(System.out::println);
        } else if (commandStorage.size() > number) {
            for (int i = (commandStorage.size() - number); i != commandStorage.size(); i++) {
                System.out.println(commandStorage.get(i));
            }
        } else {
            throw new IllegalArgumentException("Index not found");
        }
    }


    public  void addCommand(String command){
        commandStorage.add(command);
    }

    public void deleteCommand(int index){
        if(commandStorage.remove(index)==null){
            throw new IllegalArgumentException("Index not found");
        }
    }
    public void executeCommand(int index){
        if(commandStorage.get(index)==null){
            throw new IllegalArgumentException("Index not found");
        }
        System.out.println(commandStorage.get(index));
    }
    public static Undo getInstance(){
        if(undoInstance == null){
            undoInstance = new Undo();
        }
        return undoInstance;
    }

}
