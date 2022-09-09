package com.Marych.LabClass;

public class House {
    private static int nextId = 1300;
    private final int id;
    private int apartmentNumber;
    private int area;
    private int floor;
    private int numberOfRooms;
    private String address;

    public House(int apartmentNumber, int area,
                 int floor, int numberOfRooms, String address) {
        this.id = nextId;
        nextId++;
        this.apartmentNumber = apartmentNumber;
        this.area = area;
        this.floor = floor;
        this.numberOfRooms = numberOfRooms;
        this.address = address;
    }

    public House(int apartmentNumber, int floor, String address) {
        this(apartmentNumber, 0, floor, 0, address);
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public int getArea() {
        return area;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    @Override
    public String toString() {
        return " | House id - " + id +
                " | Apartment number : " + apartmentNumber +
                " | Area : " + area + " m²" +
                " | Floor : " + floor +
                " | Number of rooms : " + numberOfRooms +
                " | Address : " + address + " |";
    }
}
