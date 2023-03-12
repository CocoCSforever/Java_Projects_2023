package edu.neu.cs5004.lab2.exceptions;

public class ItemNotFoundException extends Exception{
    public ItemNotFoundException() {
    }
    public ItemNotFoundException(String message) {
        super(message);
    }
}
