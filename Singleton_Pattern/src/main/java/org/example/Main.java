package org.example;

public class Main {


    public static void main(String[] args) {
        Undo u = Undo.getInstance();
        u.addCommand("test1");
        Undo u1 = Undo.getInstance();
        u1.addCommand("test2");
        u.lookAllCommands();
        u.addCommand("test3");
        u1.addCommand("test4");
        u1.lookAllCommands();

        //testing lookXCommands()
        u.lookXCommands(1);
        u1.lookXCommands(5);


        //testing deleteCommand()
        u1.deleteCommand(1);
        u.lookAllCommands();
        u.deleteCommand(4);

    }
}
