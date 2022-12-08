package DroneConsole;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;

public class DroneGUI extends Application {
	private int xCanvasSize = 400, yCanvasSize = 500;	// size of canvas
	private VBox rtPane;								// pane in which info on ball listed
	 /**
	  * Function to show a message, 
	  * @param TStr		title of message block
	  * @param CStr		content of message
	  */
	private void showMessage(String TStr, String CStr) {
		    Alert alert = new Alert(AlertType.INFORMATION);
		    alert.setTitle(TStr);
		    alert.setHeaderText(null);
		    alert.setContentText(CStr);

		    alert.showAndWait();
	}
   /**
	 * function to show in a box ABout the programme
	 */
	 private void showAbout() {
		 showMessage("About", "Henry's GUI Drone Simulation");
	 }

	/**
	 * function to show in a box Help the programme
	 */
	 private void showHelp() {
		 showMessage("Help", "A program that shows a ball that moves and can start/stop animation." + "\n" +
	                         "You can click on the canvas to put the ball there" + "\n" +
                   			 "You can press a button to place the ball randomly");
	 }
	public MenuBar setMenu() {
		MenuBar menuBar = new MenuBar();		// create menu

		Menu mHelp = new Menu("Help");			// have entry for help
					// then add sub menus for About and Help
					// add the item and then the action to perform
		MenuItem mAbout = new MenuItem("About");
		mAbout.setOnAction(new EventHandler<ActionEvent>() {
	           @Override
	           public void handle(ActionEvent actionEvent) {
	            	showAbout();				// show the about message
	           }	
		});
		MenuItem mfHelp = new MenuItem("Help");
		mfHelp.setOnAction(new EventHandler<ActionEvent>() {
	           @Override
	           public void handle(ActionEvent actionEvent) {
	            	showHelp();				// show the about message
	           }	
		});
		mHelp.getItems().addAll(mAbout, mfHelp); 	// add submenu to Help
			
				// now add File menu, which here only has Exit
		Menu mFile = new Menu("File");				// create File Menu
		MenuItem mExit = new MenuItem("Exit");		// and Exit submenu
		mExit.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {		// and add handler
		        System.exit(0);						// quit program
		    }
		});
		mFile.getItems().addAll(mExit);	// add Exit submenu to File
			
		menuBar.getMenus().addAll(mFile, mHelp);	// menu has File and Help
			
		return menuBar;					// return the menu, so can be added
	}

	public void DisplayDroneInformation() {
		rtPane.getChildren().clear();					// clear rtpane
				// now create label
		//Label l = new Label(obw.toString());
		//rtPane.getChildren().add(l);				// add label to pane	
	}
	
	@Override
	public void start(Stage stage) {
		String javaVersion = System.getProperty("java.version");
		String javafxVersion = System.getProperty("javafx.version");
		stage.setTitle("Testing!");
	    BorderPane bp = new BorderPane();			// create border pane
	    bp.setTop(setMenu());						// create menu, add to top
	    
		
	    Label l = new Label("Hello, JavaFX " + javafxVersion
		 + ", running on Java " + javaVersion + ".");
		//Scene scene = new Scene(new StackPane(l), 640, 480);
		//stage.setTitle("Testing!");
		//stage.setScene(scene);
		bp.setCenter(new StackPane(l));
	    Scene scene = new Scene(bp, xCanvasSize*1.5, yCanvasSize*1.2);
	    stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		launch();
	}
}