package com.Marych.LabMain;

import com.Marych.LabClass.House;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter the number of apartments:");
        Scanner in = new Scanner(System.in);
        int numberOfApartments = in.nextInt();
        House[] arrayOfObjects = createArrayOfObjects(numberOfApartments);
        System.out.println("List of entered apartments");
        displayArray(arrayOfObjects);
        System.out.println("-".repeat(120));
        selectHouseNumbersOfRooms(arrayOfObjects,4);
        System.out.println("-".repeat(120));
        selectHouseNumbersAndFloor(arrayOfObjects,1,4);
        System.out.println("-".repeat(120));
        selectHouseMoreArea(arrayOfObjects,50);
        System.out.println("-".repeat(120));
    }
    public static void selectHouseNumbersOfRooms(House[] array, int numberOfRooms){
        ArrayList<House> houseArrayList = new ArrayList<>();
        for (House apartment:array){
            if(apartment.getNumberOfRooms() == numberOfRooms)
                houseArrayList.add(apartment);
        }
        if(houseArrayList.size() != 0){
            System.out.println("List of apartments with a specified number of rooms : " + numberOfRooms);
            displayArray(houseArrayList.toArray(new House[0]));
        }else{
            System.out.println("There are no apartments with a specified number of rooms");
        }
    }
    public static void selectHouseNumbersAndFloor(House[] array, int numberOfRooms, int floor){
        ArrayList<House> houseArrayList = new ArrayList<>();
        for (House apartment:array){
            if(apartment.getNumberOfRooms() == numberOfRooms && apartment.getFloor() == floor)
                houseArrayList.add(apartment);
        }
        if(houseArrayList.size() != 0){
            System.out.println("List of apartments with a specified number of rooms : " + numberOfRooms +
                    ", and which are located on the " + floor + " floor:");
            displayArray(houseArrayList.toArray(new House[0]));
        }else{
            System.out.println("There are no apartments with a specified number of rooms : " + numberOfRooms +
                    ", and which are located on the " + floor + " floor");
        }
    }
    public static void selectHouseMoreArea(House[] array, int area) {
        ArrayList<House> houseArrayList = new ArrayList<>();
        for (House apartment : array) {
            if (apartment.getArea() > area)
                houseArrayList.add(apartment);
        }
        if (houseArrayList.size() != 0) {
            System.out.println("List of apartments, the area of which exceeds " + area +
                    " m² :");
            displayArray(houseArrayList.toArray(new House[0]));
        } else {
            System.out.println("There are no apartments  the area of which exceeds " + area + " m²");
        }
    }
    public static House[] createArrayOfObjects(int number) {
        House[] array = new House[number];
        int apartmentNumber;
        int area;
        int floor;
        int numberOfRooms;
        String address;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < number; i++) {
            System.out.println("Enter the apartment number:");
            apartmentNumber = in.nextInt();
            System.out.println("Enter the Area:");
            area = in.nextInt();
            System.out.println("Enter the floor:");
            floor = in.nextInt();
            System.out.println("Enter the number of rooms:");
            numberOfRooms = in.nextInt();
            System.out.println("Enter the address:");
            address = in.nextLine();
            array[i] = new House(apartmentNumber, area, floor, numberOfRooms, address);

        }
        return array;
    }
    public static void displayArray(House[] array){
        for(House object : array){
            System.out.println(object.toString());
        }
    }
}
