package utilities;

import dataClass.DrivingLicenseData;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;


public class DataValidator {
    private static final int VALID_NAME_LENGTH = 3;

    public static boolean isValidData(String fullName, String fathersName, LocalDate dateOfBirth, String gender,
                                      String address, String licenseNo, String bloodGroup, String profilePicture,
                                      ArrayList<DrivingLicenseData> drivingLicenseDataArraylist)
            throws Exception {

        if (fullName.length() == 0) {
            throw new Exception("Full name can not be null. Please enter a full name.");
        } else if (fullName.charAt(0) < 'A' || fullName.charAt(0) > 'Z') {
            throw new Exception("First letter of full name must be capital");
        } else if (fullName.length() < VALID_NAME_LENGTH) {
            throw new Exception("Full name must be greater than 3 characters.");
        } else {
            for (int i = 0; i < fullName.length(); i++) {
                char c = fullName.charAt(i);
                if (!Character.isAlphabetic(c) && c != ' ') {
                    throw new Exception("Full name can only contain alphabets and space");
                }
            }
        }

        if (fathersName.length() == 0) {
            throw new Exception("Father's name can not be null. Please enter a father's name.");
        } else if (fathersName.charAt(0) < 'A' || fathersName.charAt(0) > 'Z') {
            throw new Exception("First letter of father's name must be capital");
        } else if (fathersName.length() < VALID_NAME_LENGTH) {
            throw new Exception("Father's name must greater than 3 characters.");
        } else {
            for (int i = 0; i < fathersName.length(); i++) {
                char c = fathersName.charAt(i);
                if (!Character.isAlphabetic(c) && c != ' ') {
                    throw new Exception("Father's name can only contain alphabets and space");
                }
            }
        }

        if (dateOfBirth == null) {
            throw new Exception("Please select a date.");
        } else {
            int age = (Period.between(dateOfBirth, LocalDate.now()).getYears());
            if (age < 18)
                throw new Exception("Age must be greater than 18.");
        }

        if (gender == null) {
            throw new Exception("Please select a gender.");
        }

        if (address.length() == 0) {
            throw new Exception("Please enter an address.");
        } else if (address.length() < 10) {
            throw new Exception("Please enter valid address (at least 10 characters).");
        }

        if (licenseNo.length() == 0) {
            throw new Exception("Please enter a license no.");
        } else if (licenseNo.length() != 10) {
            throw new Exception("License no must be 10 characters long.");
        } else if (licenseNo.charAt(0) < 'A' || licenseNo.charAt(0) > 'Z' || licenseNo.charAt(1) < 'A' || licenseNo.charAt(1) > 'Z') {
            throw new Exception("First two characters must be Capital letters.");
        } else {
            for (int i = 2; i < 10; i++) {
                if (licenseNo.charAt(i) < '0' || licenseNo.charAt(i) > '9') {
                    throw new Exception("License No. last 8 characters must be digits.");
                }
            }

            for (int i = 0; i < drivingLicenseDataArraylist.size(); i++) {
                if (licenseNo.equals(drivingLicenseDataArraylist.get(i).getLicenseNo())) {
                    throw new Exception("License No. is already taken");
                }
            }
        }

        if (bloodGroup == null) {
            throw new Exception("Please select a blood group.");
        }

        if (profilePicture == null) {
            throw new Exception("Please select a profile picture.");
        }

        return true;
    }
}
