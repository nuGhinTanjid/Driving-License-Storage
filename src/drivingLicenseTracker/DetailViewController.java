package drivingLicenseTracker;

/**
 * Sample Skeleton for 'DrivingLicenseDetailView.fxml' Controller Class
 */

import dataClass.DrivingLicenseData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DetailViewController {
  
  @FXML // fx:id="PhotoImageView"
  private ImageView PhotoImageView; // Value injected by FXMLLoader
  
  @FXML // fx:id="fullNameLabel"
  private Label fullNameLabel; // Value injected by FXMLLoader
  
  @FXML // fx:id="fathersNameLabel"
  private Label fathersNameLabel; // Value injected by FXMLLoader
  
  @FXML // fx:id="dateOfBirthLabel"
  private Label dateOfBirthLabel; // Value injected by FXMLLoader
  
  @FXML // fx:id="genderLabel"
  private Label genderLabel; // Value injected by FXMLLoader
  
  @FXML // fx:id="addressLabel"
  private Label addressLabel; // Value injected by FXMLLoader
  
  @FXML // fx:id="licenseLabel"
  private Label licenseLabel; // Value injected by FXMLLoader
  
  @FXML // fx:id="bloodGroupLabel"
  private Label bloodGroupLabel; // Value injected by FXMLLoader
  
  @FXML // fx:id="closeButton"
  private Button closeButton; // Value injected by FXMLLoader
  
  private void resetUI() {
    this.fullNameLabel.setText("");
    this.fathersNameLabel.setText("");
    this.dateOfBirthLabel.setLabelFor(null);
    this.genderLabel.setLabelFor(null);
    this.addressLabel.setText("");
    this.licenseLabel.setText("");
    this.bloodGroupLabel.setLabelFor(null);
    this.PhotoImageView.setImage(null);
  }
  
  @FXML
  void handleCloseButtonClicked(ActionEvent event) {
    this.resetUI();
    this.switchToController();
  }
  
  private void switchToController() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DrivingLicenseInformationTracker.fxml"));
      Pane root = fxmlLoader.load();
      
      Scene drivingLicenseDataScene = new Scene(root);
      Stage primaryStage = (Stage) this.closeButton.getScene().getWindow();
      primaryStage.setScene(drivingLicenseDataScene);
      primaryStage.setTitle("Driving License Information Tracker");
      primaryStage.show();
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }
  
  public void transferDrivingLicenseDataObject(DrivingLicenseData drivingLicenseData) {
    String fullName = drivingLicenseData.getFullName();
    String fathersName = drivingLicenseData.getFathersName();
    String dateOfBirthString = String.valueOf(drivingLicenseData.getDateOfBirth());
    String genderString = drivingLicenseData.getGender();
    String address = drivingLicenseData.getAddress();
    String licenseNo = drivingLicenseData.getLicenseNo();
    String bloodGroupString = String.valueOf(drivingLicenseData.getBloodGroup());
    String pathToProfilePicture = "file://" + drivingLicenseData.getProfilePicture();
    
    this.fullNameLabel.setText(fullName);
    this.fathersNameLabel.setText(fathersName);
    this.dateOfBirthLabel.setText(dateOfBirthString);
    this.genderLabel.setText(genderString);
    this.addressLabel.setText(address);
    this.licenseLabel.setText(licenseNo);
    this.bloodGroupLabel.setText(bloodGroupString);
    this.PhotoImageView.setImage(new Image(pathToProfilePicture));
  }
}
