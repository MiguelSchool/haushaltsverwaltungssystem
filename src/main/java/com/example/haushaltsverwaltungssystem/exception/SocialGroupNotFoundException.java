package com.example.haushaltsverwaltungssystem.exception;

public class SocialGroupNotFoundException  extends RuntimeException {
    public SocialGroupNotFoundException(final Long id) {
        super("social group with id: " + id + " not found!");
    }
}
