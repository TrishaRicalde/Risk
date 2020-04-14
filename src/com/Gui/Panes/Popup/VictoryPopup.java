package com.Gui.Panes.Popup;
import com.Board.Board;
import com.Player.Player;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class VictoryPopup extends Stage { 
	
	public VictoryPopup(Board b, Player p) {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10.0);

		Scene scene = new Scene(vBox, 300, 150);
		
		
		b.getInteractivePane().setMouseTransparent(true);		
		
		Label victory = new Label("Victory");
		victory.setFont(Font.font(40.0));
		victory.setTextFill(p.getPlayerColour());
		
		Label congrat = new Label(p.getPlayerName() + " has conquered the World!");
		congrat.setFont(Font.font(18.0));
		congrat.setTextFill(p.getPlayerColour());
		congrat.setWrapText(true);
		congrat.setAlignment(Pos.BOTTOM_CENTER);
		
		vBox.getChildren().add(victory);
		vBox.getChildren().add(congrat);		
		
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setScene(scene);
		this.show();
	}
}
