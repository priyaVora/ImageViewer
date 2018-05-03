package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
	ScrollPane scrollPane;
	@FXML
	Label imageName;
	@FXML
	ChoiceBox<String> operationChoiceBox;
	@FXML
	Pane mainPane;

	@FXML
	ImageView imageView;

	public ImageScreenController() {
		resetControllers();
	}

	private void resetControllers() {
		imageName = new Label();
		imageView = new ImageView();
		operationChoiceBox = new ChoiceBox<>();
		mainPane = new Pane();
		anchorPane = new AnchorPane();
		scrollPane = new ScrollPane();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		operationChoiceBox.getSelectionModel().selectFirst();
		operationChoiceBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println(
						operationChoiceBox.getItems().get(operationChoiceBox.getSelectionModel().getSelectedIndex()));
				if (operationChoiceBox.getItems().get(1) == operationChoiceBox.getSelectionModel().getSelectedItem()) {
					System.out.println("yes!");
					Window currentWindow = ((Node) (event.getSource())).getScene().getWindow();

					FileChooser chooser = new FileChooser();
					chooser.setTitle("Open File");

					File f = chooser.showOpenDialog(((Node) (event.getSource())).getScene().getWindow());
					if (f != null) {
						operationChoiceBox.getSelectionModel().selectFirst();
						String mimetype = new MimetypesFileTypeMap().getContentType(f);
						String type = mimetype.split("/")[0];
						System.out.println("Type: " + type);
						boolean valid = true;
						try {
							BufferedImage image = ImageIO.read(f);
							if (image == null) {
								valid = false;
								System.out
										.println("The file" + f.getName() + "could not be opened , it is not an image");
							}
						} catch (IOException ex) {
							valid = false;
							System.out.println("The file" + f.getName() + "could not be opened , an error  occurred.");
						}

						if (valid) {

							System.out.println(f.getName());
							f.renameTo(new File(f.getAbsolutePath()));
							System.out.println(f.getName());
							System.out.println("File URl: " + f.getPath());
							Stage stageP = (Stage) ((ChoiceBox) event.getSource()).getScene().getWindow();

							String title = f.getName();
							stageP.setTitle("Image Viewer: " + title + "");
							final ImageView imv = new ImageView();
							imv.fitWidthProperty().bind(scrollPane.widthProperty());
							imv.fitHeightProperty().bind(scrollPane.heightProperty());
							imageView.fitHeightProperty().bind(stageP.heightProperty());

							// anchorPane.setPrefSize(anchorPane.getPrefWidth() * 2,
							// anchorPane.getPrefHeight() * 2);

							mainPane.setPrefSize(anchorPane.getPrefWidth(), anchorPane.getPrefHeight());
							scrollPane.setPrefSize(mainPane.getPrefWidth(), mainPane.getHeight());

							imv.fitHeightProperty().bind(stageP.heightProperty());

							final Image image2 = new Image("file:///" + f.getPath());

							imv.setImage(image2);
							imv.autosize();
							imageView = imv;
							imageView.autosize();
							mainPane.getChildren().add(imageView);

							currentWindow.getScene().getFill();
						}

						else {
							System.out.println("It's NOT an image");
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Alert!");
							alert.setHeaderText("Incorrect File Format!");
							alert.setContentText("Please select an image format.");

							alert.showAndWait();

						}

					}

				}
			}

		});

	}

}
