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
	private static final int width = 55 * 16;
	private static final int height = 55 * 9;
	private static final Image mapImage = new Image("Risk_Map.png");
	private Board board;
	private boolean selected = true;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		board = new Board();
		ImageView imageview = new ImageView(mapImage);
		
		
		Group root = new Group(imageview);
		Scene scene = new Scene(root);
		
		StackPane stack = new StackPane();
		for (Pane p : board.getPanes()) {
			stack.getChildren().add(p);
		}


		root.getChildren().add(stack);

		//Adds Countries shape objects to the invisibleLayer
				
		//Scene scene = new Scene(root, width, height);
		
		
		primaryStage.setTitle(name);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String args[]){ 
	      launch(args); 
	   } 
}
