package org.example;

import netscape.javascript.JSObject;
import org.example.Object.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        List<Animal> animals =  new ArrayList<>();
        List<DataSaving> dataSavings = new ArrayList<>();

        dataSavings.add(new FileSaving());
        dataSavings.add(new MySQLSaver());
        try{
            for (int i = 0; i <dataSavings.size(); i++){
                dataSavings.get(i).SetUp();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Random rand = new Random();
        // Create animal
        for (int i = 0; i< 1000; i++){
            animals.add(AnimalFactory.CreateAnimal(rand.nextInt()));
        }
        // create data to save
        List<Thread> threads = new ArrayList<>();
        // Save Data to file
        for (int i = 0; i <dataSavings.size(); i++){
            try {
                dataSavings.get(i).SaveData(animals);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Thread t = new Thread(dataSavings.get(i));
            threads.add(t);
            t.start();
        }
        boolean isDone = false;
        while (!isDone){
            isDone = true;
            for (Thread t: threads) {
                isDone = isDone && t.getState()== Thread.State.TERMINATED;
            }
        }
        System.out.println("Finish");
        // Close stream
        try{
            for (int i = 0; i <dataSavings.size(); i++){
                dataSavings.get(i).Close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void ConnectDatabase(){

    }
}