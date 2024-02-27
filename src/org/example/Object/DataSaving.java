package org.example.Object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DataSaving extends Runnable{
    public void SetUp() throws IOException;
    public void SaveData(List<Animal> data) throws IOException;
    public void Close() throws IOException;
}
