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
        //BaseTest.sendGetRequest("https://dummy.restapiexample.com/api/v1/employees");
        String misu = "mama,aremere";
        String[] misu2 = misu.split(" ");
        System.out.println(Arrays.toString(misu2));
        Set<Character> misu3 = new HashSet<>();
        for (int i=0;i<misu.length();i++) {
           misu3.add(misu.charAt(i));
        }
        for (Character c:misu3) {
            misu3.add(c);
        }
        String newMisu = misu3.toString();
        System.out.println("Misu: " + misu3);


        File file = new File("src/main/java");
        func(file.listFiles());
        //List<File> file3 = file.listFiles();
//        for (File x : files) {
//            System.out.println(x.getName());
//            FileReader fileReader = new FileReader(x);
//            BufferedReader br = new BufferedReader(fileReader);
//            String line = br.readLine();
//            while ( line != null) {
//                System.out.println(line);
//                line = br.readLine();
//            }
//

        List<String> list = new ArrayList<>();
        list.add(0, "Misu");
        list.add(0, "Misu2");
        System.out.println(list);
        System.out.println(list.get(1));

        System.out.println("TEST:");
        String test = "ccaavbn&dddg&ch";
        String text2 = "";
        for (int i = 0; i<test.length(); i++) {

            int count = 1;
            for (int j = i+1; j<test.length();j++) {
                if (test.charAt(i) == test.charAt(j) ) {
                    count++;
                }
            }
            if (count > 1 && !text2.contains(String.valueOf(test.charAt(i)))) {
                System.out.println("Char : " + test.charAt(i) + " appeared times:" + count);
            }
            if (!text2.contains(String.valueOf(test.charAt(i)))) {
                text2 += test.charAt(i) + String.valueOf(count);
            }
        }
        System.out.println(text2);

        System.out.println("TEST2:");
        String test2 = "aaaabbc444&&&&&aazccc";
        String rezult = "";
        int count = 0;
        //int count2 = 0;
        for (int i = 0; i<test2.length(); i+=count) {
            count = 0;
            String x = "";
            for (int j = i; j<test2.length();j++) {
                if (test2.charAt(i) == test2.charAt(j) ) {
                    //count2++;
                    count++;
                    if (j == test2.length()-1) {
                        x = test2.charAt(i) + String.valueOf(count);
                        rezult += x;
                        //count = count2;
                    }
                } else {
                   // count = count2 ;
                    x = test2.charAt(i)  + String.valueOf(count);
                    rezult += x;
                    //count2 = 0;
                    break;
                    }
            }
        }

        System.out.println(rezult);

        System.out.println("TEST3:");
        String test3 = "aaaabbc444&&&&&aazccc";
        String result = "";
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
            result += test3.charAt(poz) + String.valueOf(count2);
            poz += count2;
            count2 = 0;
        }
        System.out.println(result);
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