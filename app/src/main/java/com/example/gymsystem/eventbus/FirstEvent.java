package com.example.gymsystem.eventbus;

public class FirstEvent {
    private int type;
    private String message;

    public FirstEvent(int type, String message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public String toString() {

        return "type="+type+"--message= "+message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
