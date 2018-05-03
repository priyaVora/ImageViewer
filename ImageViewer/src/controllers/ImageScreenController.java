package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class ImageScreenController implements Initializable {

	@FXML
	Label imageName;
	@FXML
	ChoiceBox<String> operationChoiceBox;

	public ImageScreenController() {
		resetControllers();
	}

	private void resetControllers() {
		imageName = new Label();
		operationChoiceBox = new ChoiceBox<>();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		operationChoiceBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println(
						operationChoiceBox.getItems().get(operationChoiceBox.getSelectionModel().getSelectedIndex()));

				// try {
				// Process p = new ProcessBuilder("explorer.exe",
				// "/select,C:\\directory\\selectedFile").start();
				//
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }

				FileChooser chooser = new FileChooser();
				chooser.setTitle("Open File");

				File f = chooser.showOpenDialog(((Node) (event.getSource())).getScene().getWindow());
				System.out.println(f.getName());
				f.renameTo(new File(f.getAbsolutePath()));
				System.out.println(f.getName());
			}
		});

	}

}
