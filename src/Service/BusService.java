package src.Service;
import java.util.*;
import src.Model.*;
import src.Utils.IdGenerator;

public class BusService{

    private ArrayList<Bus> buses=new ArrayList<>();

    public void createBus(String name,String source,String destination,int seats,double fare){
        int busId = IdGenerator.generateBusId();
        Bus bus = new Bus(busId, name, source, destination, seats, fare);
        buses.add(bus);
        System.out.println("bus added ");
    }

    public void updateBus(String name,String source,String destination,int seats,double fare){
        for(Bus bus : buses){
            if(bus.getName().equalsIgnoreCase(name)){
                bus.setSource(source);
                bus.setDestination(destination);
                bus.setFare(fare);
                bus.setAvailableSeats(seats);
                System.out.println(bus);
            }else{
                System.out.println("No bus found with the name "+name);
            }
        }
    }

    public void displayAllBuses(){

        for(Bus bus:buses){
            System.out.println("---------------------------");
            System.out.println("Bus Id : "+bus.getBusId());
            System.out.println("Bus Name : "+bus.getName());
            System.out.println("Bus Source : "+bus.getSource());
            System.out.println("Bus Destination : "+bus.getDestination());
            System.out.println("Bus Available seats : "+bus.getAvailableSeats());
            System.out.println("Bus fare : "+bus.getFare());
        }
    }

    public void displayBusAsPerSourceAndDestination(String Source,String Destination){

        for(Bus bus:buses){
            String source=bus.getSource();
            String destination=bus.getDestination();

            if((Source.equalsIgnoreCase(source)) && Destination.equalsIgnoreCase(destination)){
                System.out.println("Bus Name : "+bus.getName());
                System.out.println("Available Seats : "+bus.getAvailableSeats());
                System.out.println("Bus Fare : "+bus.getFare());
            }
        }
    }

    public Bus getBusAsPerSourceAndDestinationtoBook(String Name,String Source,String Destination){

        for(Bus bus:buses){
            String name=bus.getName();
            String source=bus.getSource();
            String destination=bus.getDestination();

            if((Name.equalsIgnoreCase(name)) && (Source.equalsIgnoreCase(source)) && (Destination.equalsIgnoreCase(destination))){
                return bus;
            }
        }

        return null;
    }


}