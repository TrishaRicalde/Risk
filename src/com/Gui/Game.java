package com.Gui;


import com.Board.Board;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
	private static final String name = "Risk";
	private static final int width = 54 * 16;
	private static final int height = 54 * 9;
	private static final Image mapImage = new Image("Risk_Map.png");
	private Board board;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		board = new Board(width, height);
		ImageView imageview = new ImageView(mapImage);
		
		
		Group root = new Group(imageview);
		Scene scene = new Scene(root, width, height);
		

		
		StackPane stack = new StackPane();
		for (Pane p : board.getPanes()) {
			stack.getChildren().add(p);
		}


		root.getChildren().add(stack);

		//Adds Countries shape objects to the invisibleLayer
				
		//Scene scene = new Scene(root, width, height);
		
		board.startGame();
		primaryStage.setTitle(name);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String args[]){ 
	      launch(args); 
	   } 
}
