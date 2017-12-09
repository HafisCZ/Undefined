package com.hiraishin.rain.layout;

import com.hiraishin.rain.event.StateEvent;
import com.hiraishin.rain.util.ImageLoader;

import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class BackButton extends HBox {

	/*
	 * Constructors
	 */
	public BackButton(EventType<StateEvent> eventType) {
		ImageView imageView = new ImageView(ImageLoader.DEFAULT.requestImage("gui/icons/back"));
		imageView.setOpacity(0.1);

		this.setOnMouseClicked(event -> {
			fireEvent(new StateEvent(eventType));
		});

		this.setOnMouseEntered(event -> {
			imageView.setOpacity(0.8);
		});

		this.setOnMouseExited(event -> {
			imageView.setOpacity(0.3);
		});

		this.getChildren().add(imageView);
		this.setAlignment(Pos.TOP_LEFT);
	}
}
