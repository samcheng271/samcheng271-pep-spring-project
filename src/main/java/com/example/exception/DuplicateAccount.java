package com.example.exception;

public class DuplicateAccount extends RuntimeException{
    public String duplicateAccount(String msg){
        return "duplicate";
    }
}
