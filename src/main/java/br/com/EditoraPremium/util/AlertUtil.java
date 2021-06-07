package br.com.EditoraPremium.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public abstract class AlertUtil {

	public static void displayMessage(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText("");
		alert.setContentText(message);

		alert.showAndWait();
	}

	public static boolean displayConfirmationMessage(String title, String message, String yesLabel, String noLabel) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText("");
		alert.setContentText(message);

		ButtonType btnYes = new ButtonType(yesLabel, ButtonData.OK_DONE);
		ButtonType btnNo = new ButtonType(noLabel, ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(btnYes, btnNo);

		Optional<ButtonType> result = alert.showAndWait();

		return result.get() == btnYes;
	}

	public static void displayErrorMessage(String title, Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText("");
		alert.setContentText(e.getMessage());

		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String exceptionText = sw.toString();

		Label lblFullExceptionStackTrace = new Label("Detalhes da exce��o:");

		TextArea txtFullExceptionStackTrace = new TextArea(exceptionText);
		txtFullExceptionStackTrace.setEditable(false);
		txtFullExceptionStackTrace.setWrapText(true);

		txtFullExceptionStackTrace.setMaxWidth(Double.MAX_VALUE);
		txtFullExceptionStackTrace.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(txtFullExceptionStackTrace, Priority.ALWAYS);
		GridPane.setHgrow(txtFullExceptionStackTrace, Priority.ALWAYS);

		GridPane expandableContent = new GridPane();
		expandableContent.setMaxWidth(Double.MAX_VALUE);
		expandableContent.add(lblFullExceptionStackTrace, 0, 0);
		expandableContent.add(txtFullExceptionStackTrace, 0, 1);

		alert.getDialogPane().setExpandableContent(expandableContent);

		alert.showAndWait();
	}

}
