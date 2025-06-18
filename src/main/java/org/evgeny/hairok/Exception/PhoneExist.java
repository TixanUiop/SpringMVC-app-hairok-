package org.evgeny.hairok.Exception;

public class PhoneExist extends RuntimeException{
    public PhoneExist() {
        super("Phone already exists");
    }
}
