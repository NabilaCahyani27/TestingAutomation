package demo;

import java.util.HashMap;
import java.util.Map;

public class Main {
    // public static void main(String[] args) {
    //     System.out.println("Hello world!");
    // }

     public static void main(String[] args) {
        final Map<String, Object> data = new HashMap<>();

        data.put("token", "eakfaeufbaeuifbeau");
        data.put("baseUrl", "https://whitesmokehouse.com");

        System.out.println("Token: " + data.get("token"));
        System.out.println("Base URL: " + data.get("baseUrl"));

    }
}