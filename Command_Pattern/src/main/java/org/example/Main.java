package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.HashMap;
import java.util.Map;



/* Lombok builder


 */
public class Main {


    public static void main(String[] args) {
        Vehicle car = Vehicle.builder().type(Type.CAR).build();
        Vehicle bike = Vehicle.builder().type(Type.BIKE).build();
        Vehicle plane = Vehicle.builder().type(Type.PLANE).brand("Boeing").build();
        Vehicle boat = Vehicle.builder().type(Type.BOAT).build();

        StartVehicle commandStart = new StartVehicle();
        AccelerateVehicle commandAccelerate = new AccelerateVehicle();
        SlowDownVehicle commandSlowDown = new SlowDownVehicle();

        SwitchCommand commandStore = new SwitchCommand();

        commandStore.registerCommand("start", commandStart);
        commandStore.registerCommand("accelerate", commandAccelerate);
        commandStore.registerCommand("slow down", commandSlowDown);

        System.out.println("CAR");
        car.setCommand(commandStore);
        car.executeCommand("start");
        car.executeCommand("accelerate");
        car.executeCommand("slow down");

        System.out.println("BIKE");
        bike.setCommand(commandStore);
        bike.executeCommand("start");
        bike.executeCommand("accelerate");
        bike.executeCommand("slow down");

        System.out.println("PLANE");
        plane.setCommand(commandStore);
        plane.executeCommand("start");
        plane.executeCommand("accelerate");
        plane.executeCommand("slow down");

        System.out.println("BOAT");
        boat.setCommand(commandStore);
        boat.executeCommand("start");
        boat.executeCommand("accelerate");
        boat.executeCommand("slow down");
    }
}


@AllArgsConstructor
@Data
@Builder
class Vehicle{
    private Type type;
    private String brand;
    private SwitchCommand command;
    public void executeCommand(String commandType){
        command.execute(commandType);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type=" + type +
                ", brand='" + brand + '\'' +
                '}';
    }
}

enum Type{
    BIKE, CAR, BOAT, PLANE;
}

interface Command{
    void execute();
}

class StartVehicle implements Command{
    private Vehicle vehicle;

    @Override
    public void execute(){
        System.out.println("The vehicle is starting");
    }
}

class AccelerateVehicle implements Command{
    private Vehicle vehicle;

    @Override
    public void execute(){
        System.out.println("The vehicle is accelerating");
    }
}

class SlowDownVehicle implements Command{
    private Vehicle vehicle;

    @Override
    public void execute(){
        System.out.println("The vehicle is slowing down");
    }
}

class SwitchCommand{
    private Map<String,Command> commandMap;
    {
        commandMap = new HashMap<>();
    }
    public void registerCommand(String commandName,Command command){
        commandMap.put(commandName,command);
    }
    public void execute(String commandName){
        if(commandMap.get(commandName)==null){
            throw new IllegalArgumentException("Command is not registered");
        }
        commandMap.get(commandName).execute();

    }
}