package ch.makery.controller;

import ch.makery.MainApp;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 *
 * @author Marco Jakob
 */
public class RootLayoutController {
    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");

        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }else{
            System.out.println("the file self is null!!!!!!!!!!!!!!!!!!");
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }


    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
@FXML
    private void handleSaveAs(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle(" 保存");
        //设置文件过滤器，只显示某类文件。
        FileChooser.ExtensionFilter exFilter4Mp4 = new FileChooser.ExtensionFilter("avi 文件","*.mp4");
        FileChooser.ExtensionFilter exFilter4Xml = new FileChooser.ExtensionFilter("xml 文件","*.xml");
        chooser.getExtensionFilters().addAll(exFilter4Mp4,exFilter4Xml);
        //显示弹出的另存为窗口
        File file = chooser.showSaveDialog(mainApp.getPrimaryStage());
        //System.out.println(file);
        if(file != null){
            //如果文件路劲结尾不是xml
            if (!file.getPath().endsWith(".xml")){
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }


    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void handleShowBirthdayStatistics() {
        mainApp.showBirthdayStatistics();
    }



}
