package GUIDroneSim;


import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Drones GUI application
 */
public class DroneGUI extends Application {
	private MyCanvas mc;								//Canvas used to display graphics
	private AnimationTimer timer;								// timer used for animation
	private VBox rtPane;										// vertical box for putting info
	private DroneArena arena;					//Drone Arena used to perform the calculations of drone movement on canvas

	/**
	 * function to show in a box About the programme
	 */
	private void showAbout() {
	    Alert alert = new Alert(AlertType.INFORMATION);				// define what box is
	    alert.setTitle("About");									// say is About
	    alert.setHeaderText(null);
	    alert.setContentText("Henry's Drone GUI");			// give text
	    alert.showAndWait();										// show box and wait for user to close
	}
	/**
	 * function to show in a box Instructions the programme
	 */
	private void showInstructions() {
		Alert alert = new Alert(AlertType.INFORMATION);				// define what box is
		alert.setTitle("About");									// say is About
		alert.setHeaderText(null);
		alert.setContentText("The Following Program is a simulation of multiple different drones." + "\n" +
				"Click the buttons at the bottom of the screen to add drones" + "\n" +
				"Click on the screen itself to add food which drones can eat for points"+ "\n" +
				"C - Means Spawn the drone from the center"+ "\n" +
				"R- Means Spawn the drone from a random location" + "\n" +
				"Clear - Means Clear all drones" + "\n" +
				"Start - Means Start Animation" + "\n" +
				"Pause - Means Stop Animation" + "\n" +
				"Note: You cannot add food on top of drones or in the middle to block drones spawning in");		// give text
		alert.showAndWait();										// show box and wait for user to close
	}

	/**
	 * function to show in a box Colour Codes the programme
	 */
	private void showColourCodes() {
		Alert alert = new Alert(AlertType.INFORMATION);				// define what box is
		alert.setTitle("About");									// say is About
		alert.setHeaderText(null);
		alert.setContentText("Blue - Simple Drone" + "\n" +
				"Orange - Reflection Drone" + "\n" +
				"Black - Object"+ "\n" +
				"Green - Food");		// give text
		alert.showAndWait();										// show box and wait for user to close
	}
	 /**
	  * set up the mouse event - when mouse pressed, put Drone there
	  * @param canvas
	  */
	void setMouseEvents (Canvas canvas) {
	       canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 		// for MOUSE PRESSED event
	    	       new EventHandler<MouseEvent>() {
	    	           @Override
	    	           public void handle(MouseEvent e) {
						    arena.addDroneFood(e.getX(), e.getY());
	  		            	drawWorld();							// redraw world
	  		            	drawStatus();
	    	           }
	    	       });
	}
	/**
	 * set up the menu of commands for the GUI
	 * @return the menu bar
	 */
	MenuBar setMenu() {
		MenuBar menuBar = new MenuBar();						// create main menu
	
		Menu mFile = new Menu("File");							// add File main menu
		MenuItem mExit = new MenuItem("Exit");					// whose sub menu has Exit
		mExit.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {					// action on exit is
	        	timer.stop();									// stop timer
		        System.exit(0);									// exit program
		    }
		});
		mFile.getItems().addAll(mExit);							// add exit to File menu
		
		Menu mHelp = new Menu("Help");							// create Help menu
		MenuItem mAbout = new MenuItem("About");				// add About sub men item
		mAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	showAbout();									// and its action to print about
            }	
		});
		mHelp.getItems().addAll(mAbout);						// add About to Help main item

		MenuItem mInformation = new MenuItem("Instructions");				// add About sub men item
		mInformation.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				showInstructions();									// and its action to print about
			}
		});
		mHelp.getItems().addAll(mInformation);

		MenuItem mColourCodes = new MenuItem("Colour Codes for Drones");				// add About sub men item
		mColourCodes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				showColourCodes();									// and its action to print about
			}
		});
		mHelp.getItems().addAll(mColourCodes);
		menuBar.getMenus().addAll(mFile, mHelp);				// set main menu with File, Help
		return menuBar;											// return the menu
	}

	/**
	 * set up the horizontal box for the bottom with relevant buttons
	 * @return
	 */
	private HBox setButtons() {
	    Button btnStart = new Button("Start");					// create button for starting
	    btnStart.setOnAction(new EventHandler<ActionEvent>() {	// now define event when it is pressed
	        @Override
	        public void handle(ActionEvent event) {
	        	timer.start();									// its action is to start the timer
	       }
	    });

	    Button btnStop = new Button("Pause");					// now button for stop
	    btnStop.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	           	timer.stop();									// and its action to stop the timer
	       }
	    });

	    Button btnAddC = new Button(" C");				// now button for Add drone center
	    btnAddC.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	           	arena.addDroneSimple();								// Add a simple drone
	           	drawWorld();
	       }
	    });

		Button btnAddR = new Button(" R");				// now button for add random simple
		btnAddR.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena.addDroneRandomSimple();							// Add a simple drone
				drawWorld();
			}
		});



		Button btnAddCR = new Button("C");				// now button for Add drone center
		btnAddCR.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena.addDroneReflect();								// Add a reflect drone
				drawWorld();
			}
		});

		Button btnAddRR = new Button("R");				// now button for random reflect
		btnAddRR.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena.addDroneRandomReflect();							// Add a reflect drone
				drawWorld();
			}
		});

		Button btnAddRO = new Button("R");				// now button for Random object
		btnAddRO.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena.addDroneObject();							// Add a object drone
				drawWorld();
			}
		});



		Button btnClear = new Button("Clear");				// now button for Clear Drone
		btnClear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena.ClearDrones();							// Clears drones
				drawWorld();
			}
		});														// now add these buttons + labels to a HBox
	    return new HBox(new Label(" Run: "),
				btnStart,
				btnStop,
				new Label("  Add Simple: "),
				btnAddC,btnAddR,
				new Label("  Add Reflect: "),
				btnAddCR,btnAddRR,
				new Label("  Add Object: "),
				btnAddRO,
				new Label("  Clear: "),
				btnClear);
	}

	/** 
	 * draw the world (Drones and Canvas)
	 */
	public void drawWorld () {
	 	mc.clearCanvas();						// set beige colour
	 	arena.drawArena(mc);
	}
	
	/**
	 * Status of each drone is displayed on the side
	 */
	public void drawStatus() {
		rtPane.getChildren().clear();					// clear rtpane
		rtPane.getChildren().add(new Label("Drone Information"));
		rtPane.getChildren().add(new Label("Food Eaten: "+arena.getScore()));
		ArrayList<String> allStats= arena.describeAll();
		for (String s : allStats) {
			Label l = new Label(s); 		// turn description into a label
			rtPane.getChildren().add(l);	// add label	
		}	
	}


	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Henry's Drone Program");
	    BorderPane bp = new BorderPane();
	    bp.setPadding(new Insets(10, 20, 10, 20));

	    bp.setTop(setMenu());											// put menu at the top

	    Group root = new Group();										// create group with canvas
	    Canvas canvas = new Canvas( 400, 500 );
	    root.getChildren().add( canvas );
	    bp.setLeft(root);												// load canvas to left area
	
	    mc = new MyCanvas(canvas.getGraphicsContext2D(), 400, 500);

	    setMouseEvents(canvas);											// set up mouse events

	    arena = new DroneArena(400, 500);								// set up arena
	    drawWorld();
	    
	    timer = new AnimationTimer() {									// set up timer
	        public void handle(long currentNanoTime) {					// and its action when on
	        		arena.checkDrones();									// check the angle of all Drones
		            arena.adjustDrones();								// move all Drones
					arena.removeDrones();
		            drawWorld();										// redraw the world
		            drawStatus();										// indicate where Drones are
	        }
	    };

	    rtPane = new VBox();											// set vBox on right to list items
		rtPane.setAlignment(Pos.TOP_LEFT);								// set alignment
		rtPane.setPadding(new Insets(5, 75, 75, 5));					// padding
 		bp.setRight(rtPane);											// add rtPane to borderpane right
		  
	    bp.setBottom(setButtons());										// set bottom pane with buttons

	    Scene scene = new Scene(bp, 700, 600);							// set overall scene
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());

        primaryStage.setScene(scene);
        primaryStage.show();
	  

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    Application.launch(args);			// launch the GUI

	}

}