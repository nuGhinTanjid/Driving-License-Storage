package dataClass;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class DrivingLicenseData implements Serializable {
  
  @Serial
  private static final long serialVersionUID = 1827472349320230664L;
  private String fullName, fathersName, gender, address, licenseNo, bloodGroup, profilePicture;
  private LocalDate dateOfBirth;
  
  public DrivingLicenseData(String fullName, String fathersName, LocalDate dateOfBirth, String gender,
                            String address, String licenseNo, String bloodGroup, String profilePicture) {
    this.fullName = fullName;
    this.fathersName = fathersName;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.address = address;
    this.licenseNo = licenseNo;
    this.bloodGroup = bloodGroup;
    this.profilePicture = profilePicture;
  }
  
  public String getFullName() {
    return fullName;
  }
  
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  
  public String getFathersName() {
    return fathersName;
  }
  
  public void setFathersName(String fathersName) {
    this.fathersName = fathersName;
  }
  
  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }
  
  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
  
  public String getGender() {
    return gender;
  }
  
  public void setGender(String gender) {
    this.gender = gender;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getLicenseNo() {
    return licenseNo;
  }
  
  public void setLicenseNo(String licenseNo) {
    this.licenseNo = licenseNo;
  }
  
  public String getBloodGroup() {
    return bloodGroup;
  }
  
  public void setBloodGroup(String bloodGroup) {
    this.bloodGroup = bloodGroup;
  }
  
  public String getProfilePicture() {
    return profilePicture;
  }
  
  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }
  
  @Override
  public String toString() {
    return "Name: " + fullName + ", License No: " + licenseNo;
  }
}
