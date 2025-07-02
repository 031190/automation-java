package com.testapi;

import io.restassured.internal.util.IOUtils;

import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        File file = new File("src/main/java");
        func(file.listFiles());

        System.out.println("TEST2:");
        String test2 = "aaaabbc444&&&&&aazccc";
        String result = "";
        int count;
        for (int i = 0; i<test2.length(); i+=count) {
            count = 0;
            String x = "";
            for (int j = i; j<test2.length();j++) {
                if (test2.charAt(i) == test2.charAt(j) ) {
                    count++;
                    if (j == test2.length()-1) {
                        x = test2.charAt(i) + String.valueOf(count);
                        result += x;
                    }
                } else {
                    x = test2.charAt(i)  + String.valueOf(count);
                    result += x;
                    break;
                }
            }
        }
        System.out.println(result);

        System.out.println("TEST3:");
        String test3 = "aaaabbc444&&&&&aazccc";
        String resultString = "";
        int poz = 0;
        int count2 = 0;
        while (poz < test3.length()) {
            for (int i = poz ;i<test3.length();i++) {
                if (test3.charAt(poz) == test3.charAt(i)) {
                    count2++;
                } else {
                    break;
                }
            }
            resultString += test3.charAt(poz) + String.valueOf(count2);
            poz += count2;
            count2 = 0;
        }
        System.out.println(resultString);
    }
    public static void func(File[] files) {

        for (File x : files) {
            if (x.isDirectory() && x.listFiles() != null) {
                func(x.listFiles());
            } else {
                System.out.println(x.getName());
                try {
                    FileInputStream fileInputStream = new FileInputStream(x);
                    System.out.println(new String(fileInputStream.readAllBytes()));
                    fileInputStream.close();
                } catch (IOException e){
                    System.out.println("FILE NOT FOUND");
                }
            }
        }
    }
}