package com.hiraishin.rain.input;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Keyboard implements EventHandler<KeyEvent> {

	/*
	 * Instance final variables
	 */
	private final Map<KeyCode, Boolean> currentMap = new HashMap<>();
	private final Map<KeyCode, Boolean> previousMap = new HashMap<>();

	/*
	 * Constructors
	 */
	public Keyboard(Stage stage) {
		synchronized (stage) {
			stage.addEventHandler(KeyEvent.KEY_PRESSED, this);
			stage.addEventHandler(KeyEvent.KEY_RELEASED, this);
		}
	}

	/*
	 * Instance functions
	 */
	public void update() {
		previousMap.putAll(currentMap);
	}

	@Override
	public void handle(KeyEvent keyEvent) {
		final KeyCode keyCode = keyEvent.getCode();
		final EventType<KeyEvent> eventType = keyEvent.getEventType();

		if (eventType == KeyEvent.KEY_PRESSED) {
			currentMap.put(keyCode, true);
		} else if (eventType == KeyEvent.KEY_RELEASED) {
			currentMap.put(keyCode, false);
		}

		keyEvent.consume();
	}

	/*
	 * Getters & Setters
	 */
	public boolean isHeld(KeyCode keyCode) {
		return currentMap.getOrDefault(keyCode, false);
	}

	public boolean wasHeld(KeyCode keyCode) {
		return previousMap.getOrDefault(keyCode, false);
	}

	public boolean isPressed(KeyCode keyCode) {
		return isHeld(keyCode) && !wasHeld(keyCode);
	}

	public boolean wasPressed(KeyCode keyCode) {
		return !isHeld(keyCode) && wasHeld(keyCode);
	}

}
