package com.sarel.config;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ExampleObject object = new ExampleObject();
        try {
            Config.ConfigObject("ExampleObject.txt",object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(object.getS());
        System.out.println(object.getI());
        System.out.println(object.getF());
    }
}
