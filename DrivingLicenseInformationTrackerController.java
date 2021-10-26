package drivingLicenseTracker;

import dataClass.DrivingLicenseData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utilities.DataValidator;
import utilities.Serializer;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class DrivingLicenseInformationTrackerController {
  
  @FXML
  private TextField fullNameTextField;
  
  @FXML
  private TextField fathersNameTextField;
  
  @FXML
  private DatePicker dateOfBirthDatePicker;
  
  @FXML
  private ComboBox<String> genderComboBox;
  
  @FXML
  private TextField addressTextField;
  
  @FXML
  private TextField licenseTextField;
  
  @FXML
  private ComboBox<String> bloodGroupComboBox;
  
  @FXML
  private Button choosePhotoButton;
  
  @FXML
  private ImageView PhotoImageView;
  
  @FXML
  private Button saveTextField;
  
  @FXML
  private Button clearFormButton;
  
  @FXML
  private ListView<DrivingLicenseData> listView;
  
  @FXML
  private Button viewButton;
  
  @FXML
  private Button editSelectedButton;
  
  @FXML
  private Button deleteButton;
  
  private String profilePhotoPath = null;
  
  private ArrayList<DrivingLicenseData> drivingLicenseDataArrayList = null;
  
  private ObservableList<DrivingLicenseData> drivingLicenseDataObservableList = null;
  
  private int indexOfSelectedPersonFromListView = -1;
  
  private void updateProfileViewImageNode() {
    Image profileImage = new Image("file://" + this.profilePhotoPath);
    this.PhotoImageView.setImage(profileImage);
  }
  
  @FXML
  void handleChoosePhotoClick(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = (Stage) this.choosePhotoButton.getScene().getWindow();
    File selectedFile = fileChooser.showOpenDialog(primaryStage);
    
    if (selectedFile != null) {
      this.profilePhotoPath = selectedFile.toURI().getPath();
      this.updateProfileViewImageNode();
    }
  }
  
  @FXML
  void handleEditSelectedClick(ActionEvent event) {
    this.indexOfSelectedPersonFromListView = this.listView.getSelectionModel().getSelectedIndex();
    
    if (this.indexOfSelectedPersonFromListView != -1) {
      DrivingLicenseData savedPerson = this.listView.getItems().get(this.indexOfSelectedPersonFromListView);
      /**
       Setting license no. null so that when the data is updated data validator does not throw "License No. is already taken"
       */
      String tempLicenseNo = drivingLicenseDataArrayList.get(this.indexOfSelectedPersonFromListView).getLicenseNo();
      drivingLicenseDataArrayList.get(this.indexOfSelectedPersonFromListView).setLicenseNo("");
      
      String fullName = savedPerson.getFullName();
      String fathersName = savedPerson.getFathersName();
      LocalDate dateOfBirth = savedPerson.getDateOfBirth();
      String gender = String.valueOf(savedPerson.getGender());
      String address = savedPerson.getAddress();
      String bloodGroup = savedPerson.getBloodGroup();
      this.profilePhotoPath = savedPerson.getProfilePicture();
      
      this.updateUIWithSavedPersonData(fullName, fathersName, dateOfBirth, gender, address, tempLicenseNo, bloodGroup);
    }
  }
  
  private void updateUIWithSavedPersonData(String fullName, String fathersName, LocalDate dateOfBirth, String gender,
                                           String address, String licenseNo, String bloodGroup) {
    this.fullNameTextField.setText(fullName);
    this.fathersNameTextField.setText(fathersName);
    this.dateOfBirthDatePicker.setValue(dateOfBirth);
    this.genderComboBox.setValue(gender);
    this.addressTextField.setText(address);
    this.licenseTextField.setText(licenseNo);
    this.bloodGroupComboBox.setValue(bloodGroup);
    this.updateProfileViewImageNode();
  }
  
  @FXML
  void handleClearFormClick(ActionEvent event) {
    this.resetUI();
  }
  
  @FXML
  void handleDeleteButton(ActionEvent event) {
    this.indexOfSelectedPersonFromListView = this.listView.getSelectionModel().getSelectedIndex();
    if (this.indexOfSelectedPersonFromListView != -1) {
      drivingLicenseDataArrayList.remove(this.indexOfSelectedPersonFromListView);
      drivingLicenseDataObservableList.remove(this.indexOfSelectedPersonFromListView);
      try {
        boolean binaryFileSaveSuccessful = Serializer.serialize(Serializer.databasePath, this.drivingLicenseDataArrayList);
        if (!binaryFileSaveSuccessful) {
          throw new Exception("Could not save to the database file");
        }
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }
  }
  
  @FXML
  void handleSaveClick(ActionEvent event) {
    
    String fullName = this.fullNameTextField.getText();
    String fathersName = this.fathersNameTextField.getText();
    LocalDate dateOfBirth = dateOfBirthDatePicker.getValue();
    String gender = this.genderComboBox.getSelectionModel().getSelectedItem();
    String address = this.addressTextField.getText();
    String licenseNo = this.licenseTextField.getText();
    String bloodGroup = this.bloodGroupComboBox.getSelectionModel().getSelectedItem();
    String profilePicture = this.profilePhotoPath;
    
    try {
      if (DataValidator.isValidData(fullName, fathersName, dateOfBirth, gender, address, licenseNo, bloodGroup, profilePicture, drivingLicenseDataArrayList)) {
        System.out.println("Info: ");
        System.out.println("Full Name: " + fullName);
        System.out.println("Fathers Name: " + fathersName);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender :" + gender);
        System.out.println("Address: " + address);
        System.out.println("License: " + licenseNo);
        System.out.println("bloodGroup: " + bloodGroup);
        
        DrivingLicenseData drivingLicenseData = new DrivingLicenseData(fullName, fathersName, dateOfBirth, gender,
          address, licenseNo, bloodGroup, profilePicture);
        
        System.out.println(drivingLicenseData);
        
        if (this.indexOfSelectedPersonFromListView != -1) {
          this.drivingLicenseDataArrayList.set(this.indexOfSelectedPersonFromListView, drivingLicenseData);
          this.drivingLicenseDataObservableList.set(this.indexOfSelectedPersonFromListView, drivingLicenseData);
          this.listView.refresh();
        } else {
          this.drivingLicenseDataArrayList.add(drivingLicenseData);
          this.drivingLicenseDataObservableList.add(drivingLicenseData);
        }
        
        boolean binaryFileSaveSuccessful = Serializer.serialize(Serializer.databasePath, this.drivingLicenseDataArrayList);
        if (!binaryFileSaveSuccessful) {
          throw new Exception("Could not save to the database file");
        }
        this.resetUI();
      }
      
    } catch (Exception exception) {
      Stage primaryStage = (Stage) this.saveTextField.getScene().getWindow();
      ErrorView.showErrorMessageDialogueBox(exception.getMessage(), primaryStage);
    }
  }
  
  private void resetUI() {
    this.fullNameTextField.setText("");
    this.fathersNameTextField.setText("");
    this.dateOfBirthDatePicker.setValue(null);
    this.genderComboBox.setValue(null);
    this.addressTextField.setText("");
    this.licenseTextField.setText("");
    this.bloodGroupComboBox.setValue(null);
    this.profilePhotoPath = null;
    this.PhotoImageView.setImage(null);
  }
  
  @FXML
  void handleTextViewClicked(MouseEvent event) {
  
  }
  
  @FXML
  void handleViewClick(ActionEvent event) {
    this.indexOfSelectedPersonFromListView = this.listView.getSelectionModel().getSelectedIndex();
    
    if (this.indexOfSelectedPersonFromListView != -1) {
      DrivingLicenseData drivingLicenseData = this.listView.getItems().get(this.indexOfSelectedPersonFromListView);
      
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DrivingLicenseDetailView.fxml"));
        Pane root = fxmlLoader.load();
        
        DetailViewController detailViewController = fxmlLoader.getController();
        detailViewController.transferDrivingLicenseDataObject(drivingLicenseData);
        
        Scene detailViewScene = new Scene(root);
        Stage primaryStage = (Stage) this.viewButton.getScene().getWindow();
        primaryStage.setScene(detailViewScene);
        primaryStage.setTitle("Driving License Data for " + drivingLicenseData.getFullName());
        primaryStage.show();
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }
  }
  
  public void initialize() {
    System.out.println("From initialize method");
    this.drivingLicenseDataArrayList = Serializer.deserialize(Serializer.databasePath);
    
    if (this.drivingLicenseDataArrayList == null) {
      this.drivingLicenseDataArrayList = new ArrayList<>();
    }
    
    this.drivingLicenseDataObservableList = FXCollections.observableArrayList(drivingLicenseDataArrayList);
    this.listView.setItems(drivingLicenseDataObservableList);
    
    ArrayList<String> gender = new ArrayList<>();
    gender.add("Male");
    gender.add("Female");
    gender.add("Other");
    
    ObservableList<String> genderObservableList = FXCollections.observableArrayList(gender);
    this.genderComboBox.setItems(genderObservableList);
    
    ArrayList<String> bloodGroup = new ArrayList<>();
    bloodGroup.add("A+");
    bloodGroup.add("O+");
    bloodGroup.add("B+");
    bloodGroup.add("AB+");
    bloodGroup.add("A-");
    bloodGroup.add("O-");
    bloodGroup.add("B-");
    bloodGroup.add("AB-");
    
    ObservableList<String> bloodObservableList = FXCollections.observableArrayList(bloodGroup);
    this.bloodGroupComboBox.setItems(bloodObservableList);
  }
}
