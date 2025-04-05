package com.bloxxdev.breakout.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHandler {

    public static void writeFile(File file, Object... data){
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

            for (int i = 0; i < data.length; i++) {
                oos.writeObject(data[i]);
            }

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Object readFile(File file){
        Object data = 0;

        if (file.exists()) {
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));    
                data = ois.readObject();
                ois.close();
            }catch (ClassNotFoundException | IOException e){
                e.printStackTrace();
            }
        }

        return data;
    }
}
