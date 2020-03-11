package com.Gui;

import java.util.ArrayList;
import java.util.Observable;

import com.Board.Board;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.util.Duration;

public class Game extends Application {
	private static final String name = "Risk";
	private static final int width = 55 * 16;
	private static final int height = 55 * 9;
	private static final Image mapImage = new Image("Risk_Map.png");
	private Board board;

	private int numOfPlayers = 0;
	private ArrayList<String> names;

	private boolean selected = true;

	

	@Override
	public void start(Stage primaryStage) throws Exception {
		board = new Board();
		ImageView imageview = new ImageView(mapImage);		
		
		
		Group root = new Group(imageview);
		Scene scene = new Scene(root);

		Button start = new Button();
		Button next = new Button();
		Button secondNext = new Button();
		Button play = new Button();
		
		names = new ArrayList<String>();

		Label greeting = new Label("Welcome to Risk, a Game of War");
		Label playerLabel = new Label("Please enter number of players (between 0 and 4):");
		Label numPlayerLabel = new Label();
		Label playerNames = new Label("Enter player names: ");
		Label welcome = new Label("Welcome");
		Label goodLuck = new Label("May the best strategist win. Good luck");

		TextField numPlayers = new TextField();

		Popup startPopUp = new Popup();
		Popup nextPopUp = new Popup();
		Popup finalPopUp = new Popup();

		BorderPane borderPane = new BorderPane();

		VBox vbox = new VBox(10);
		VBox vbox2 = new VBox(10);
		VBox vbox3 = new VBox(10);
		VBox textVBox = new VBox(10);

		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85);");

		vbox2.setPadding(new Insets(10, 10, 10, 10));
		vbox2.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85);");
		
		vbox3.setPadding(new Insets(10, 10, 10, 10));
		vbox3.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85);");

		startPopUp.getContent().add(vbox);
		nextPopUp.getContent().add(vbox2);
		finalPopUp.getContent().add(vbox3);

		vbox.getChildren().add(greeting);
		vbox.getChildren().add(playerLabel);
		vbox.getChildren().add(numPlayers);
		vbox.getChildren().add(next);
		vbox.setMargin(next, new Insets(0, 0, 0, 265));

		vbox2.getChildren().add(numPlayerLabel);
		vbox2.getChildren().add(playerNames);
		vbox2.getChildren().add(textVBox);
		vbox2.getChildren().add(secondNext);
		vbox2.setMargin(secondNext, new Insets(0, 0, 0, 265));
		
		vbox3.getChildren().add(welcome);
		vbox3.getChildren().add(goodLuck);
		vbox3.getChildren().add(play);
		vbox3.setMargin(play, new Insets(0, 0, 0, 265));

		start.setText("Start Game");
		next.setText("Next");
		secondNext.setText("Next");
		play.setText("Play");

		start.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) {
				if (!startPopUp.isShowing()) {
					startPopUp.show(primaryStage);
					start.setVisible(false);

				} else {
				}

			}
		});
		
		
		next.setOnAction(new EventHandler<ActionEvent>()
				{
					
					
					
					@Override
					public void handle(ActionEvent event) 
					{
						
						String text = numPlayers.getText();
						
						if (text.equals("1")|| text.equals("2") || text.equals("3")|| text.equals("4"))
						{
							try {
								setPlayerNum(Integer.parseInt(text));
								startPopUp.hide();
								numPlayerLabel.setText("You have selected " + numOfPlayers + " players");
								for (int counter = 0; counter < numOfPlayers; counter++) {

									TextField nameField = new TextField();
									textVBox.getChildren().add(nameField);
									
								}								
								
								nextPopUp.show(primaryStage);
								
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
			
				});
		
		secondNext.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event)
			{
				String welcomeString = "Welcome strategists";
				String nameString = "Hell yea";
				ObservableList<Node>fieldNames = textVBox.getChildren();
				//System.out.print(names.get(counter));
				for (int counter = 0; counter < fieldNames.size(); counter++)
				{
					
					TextField tf = (TextField)fieldNames.get(counter);
					nameString = tf.getText();
					names.add(nameString);
				}
				
				welcome.setText(welcomeString);
				nextPopUp.hide();
				finalPopUp.show(primaryStage);
				
				
			}
		});
		
		
		play.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event)
			{
				finalPopUp.hide();
			}
		});

		StackPane stack = new StackPane();
		for (Pane p : board.getPanes()) {
			stack.getChildren().add(p);
		}


		borderPane.setPrefSize(width, height);
		borderPane.setCenter(start);
		stack.getChildren().add(borderPane);

		// InteractivePane interactivePane = new InteractivePane(new Map());
		// stack.getChildren().add(interactivePane);
//		Pane welcomePane = new Pane();
//		Pane invisibleLayer = new Pane();
//		Pane mapLayer = new Pane();		
//		Pane topLayer = new Pane();
//		invisibleLayer.setOpacity(0.5);



		root.getChildren().add(stack);

		// Adds Countries shape objects to the invisibleLayer

		// Scene scene = new Scene(root, width, height);

		primaryStage.setTitle(name);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void setPlayerNum(int i) {
		numOfPlayers = i;
	}

	public static void main(String args[]) {
		launch(args);
	}
}
