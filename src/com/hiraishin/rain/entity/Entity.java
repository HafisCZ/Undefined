package com.hiraishin.rain.entity;

import java.util.Objects;

import com.hiraishin.rain.graphics.Drawable;
import com.hiraishin.rain.graphics.Sprite;
import com.hiraishin.rain.level.Level;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entity implements Drawable {

	/*
	 * Instance final variables
	 */
	protected final Level level;

	protected final double width;
	protected final double height;

	protected final Sprite sprite;
	protected final double spriteOffsetX;
	protected final double spriteOffsetY;

	/*
	 * Instance variables
	 */
	protected double x;
	protected double y;

	protected double dx = 0;
	protected double dy = 0;

	protected boolean dead = false;

	/*
	 * Constructors
	 */
	protected Entity(double x, double y, double width, double height, Sprite sprite, double offsetX, double offsetY,
			Level level) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;

		this.sprite = sprite;
		this.spriteOffsetX = offsetX;
		this.spriteOffsetY = offsetY;

		this.level = level;
	}

	protected Entity(double x, double y, double width, double height, Level level) {
		this(x, y, width, height, null, 0, 0, level);
	}

	/*
	 * Instance Functions
	 */
	@Override
	public void draw(GraphicsContext gc) {
		if (Objects.nonNull(this.sprite)) {
			this.sprite.draw(gc, this.x + this.spriteOffsetX, this.y + this.spriteOffsetY);
		}
	}

	public final boolean collidesAABB(Entity entity) {
		final boolean a = entity.x + entity.width > this.x;
		final boolean b = this.x + this.width > entity.x;
		final boolean c = entity.y + entity.height > this.y;
		final boolean d = this.y + this.height > entity.y;
		return a && b && c && d;
	}

	public final void kill() {
		this.dead = true;
	}

	public final boolean isDead() {
		return this.dead;
	}

	/*
	 * Getters & Setters
	 */
	public final double getX() {
		return this.x;
	}

	public final double getY() {
		return this.y;
	}

	public final double getWidth() {
		return this.width;
	}

	public final double getHeight() {
		return this.height;
	}

	public final Rectangle2D getBounds() {
		return new Rectangle2D(this.x, this.y, this.width, this.height);
	}

	public final double getDistance(double x, double y) {
		return Math.sqrt((x - (this.x + this.width / 2)) * (x - (this.x + this.width / 2))
				+ (y - (this.y + this.height / 2)) * (y - (this.y + this.height / 2)));
	}

	/*
	 * Abstract functions
	 */
	public abstract void tick();

}
