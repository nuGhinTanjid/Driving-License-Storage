package utilities;

import dataClass.DrivingLicenseData;

import java.io.*;
import java.util.ArrayList;

public class Serializer {
    public static final String databasePath = "./database.bin";

    public static boolean serialize(String filePath, ArrayList<DrivingLicenseData> drivingLicenseDataArrayList) {
        File databaseFile = new File(filePath);
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        boolean success;
        try {
            fileOutputStream = new FileOutputStream(databaseFile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(drivingLicenseDataArrayList);
            success = true;
        } catch (Exception exception) {
            success = false;
        }
        return success;
    }

    public static ArrayList<DrivingLicenseData> deserialize(String filePath) {
        File databaseFile = new File(filePath);
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        ArrayList<DrivingLicenseData> list = null;
        try {
            fileInputStream = new FileInputStream(databaseFile);
            objectInputStream = new ObjectInputStream(fileInputStream);
            list = (ArrayList<DrivingLicenseData>) objectInputStream.readObject();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
        return list;
    }
}
