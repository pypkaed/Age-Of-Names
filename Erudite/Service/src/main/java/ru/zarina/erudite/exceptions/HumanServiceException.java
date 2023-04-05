package ru.zarina.erudite.exceptions;

public class HumanServiceException extends Exception {
    private HumanServiceException(String msg) { super(msg); }
    public static HumanServiceException EntityNotFound(String name) {
        return new HumanServiceException("Couldn't find entity with name " + name);
    }

    public static HumanServiceException EntityDuplicate(String name) {
        return new HumanServiceException("Entity with name " + name + " already exists.");
    }
}
