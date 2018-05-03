package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ImageScreenController implements Initializable {
	@FXML
	AnchorPane anchorPane;
	@FXML
	Label imageName;
	@FXML
	ChoiceBox<String> operationChoiceBox;
	@FXML
	Pane mainPane;

	public ImageScreenController() {
		resetControllers();
	}

	private void resetControllers() {
		imageName = new Label();
		operationChoiceBox = new ChoiceBox<>();
		mainPane = new Pane();
		anchorPane = new AnchorPane();
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
				Window currentWindow = ((Node) (event.getSource())).getScene().getWindow();

				FileChooser chooser = new FileChooser();
				chooser.setTitle("Open File");

				File f = chooser.showOpenDialog(((Node) (event.getSource())).getScene().getWindow());
				System.out.println(f.getName());
				f.renameTo(new File(f.getAbsolutePath()));
				System.out.println(f.getName());
				System.out.println("File URl: " + f.getPath());
				Stage stageP = (Stage) ((ChoiceBox) event.getSource()).getScene().getWindow();

				String title = f.getName();
				stageP.setTitle("Image Viewer: " + title + "");
				final ImageView imv = new ImageView();
				// imv.fitWidthProperty().bind(stageP.widthProperty());
				// stageP.setWidth(imv.getFitWidth());
				// anchorPane.setPrefWidth(imv.getFitWidth());
				// anchorPane.setPrefHeight(imv.getFitHeight());
				//
				// stageP.setWidth(anchorPane.getPrefWidth());
				// stageP.setHeight(anchorPane.getPrefHeight());
				// stageP.setWidth(imv.getBaselineOffset());
				// stageP.setWidth(currentWindow.getWidth());
				mainPane.setPrefSize(anchorPane.getPrefWidth(), anchorPane.getPrefHeight());

				final Image image2 = new Image("file:///" + f.getPath());

				imv.setImage(image2);
				imv.autosize();
				mainPane.getChildren().add(imv);

				currentWindow.setHeight(anchorPane.getHeight());
				currentWindow.setWidth(anchorPane.getPrefHeight());
				currentWindow.getScene().getFill();
			}
		});

	}

}
