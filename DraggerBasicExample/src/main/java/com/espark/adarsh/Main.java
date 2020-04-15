package com.espark.adarsh;

import javax.inject.Inject;

public class Main {

    @Inject
    BackendService backendService; //

    private MyComponent component;

    private Main() {
        component = DaggerMyComponent.builder().build();
        component.inject(this);
    }

    private void callServer() {
        boolean callServer = backendService.callServer();
        if (callServer) {
            System.out.println("Server call was successful. ");
        } else {
            System.out.println("Server call failed. ");
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.callServer();
    }
}