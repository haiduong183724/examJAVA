package org.example.Object;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSaving implements DataSaving{

    BufferedWriter bw;
    List<Animal> data;
    @Override
    public void SetUp() throws IOException {
        String fileName = "duong_dan_tep_tin.txt";
        bw = new BufferedWriter(new FileWriter(fileName));
    }

    @Override
    public void SaveData(List<Animal> data) throws IOException {
        this.data =data;
    }
    @Override
    public void Close() throws IOException {
        bw.close();
    }

    @Override
    public void run() {
        try {
            bw.write(data.toString());
            System.out.println("Save file done!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
