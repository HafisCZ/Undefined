package com.hiraishin.rain.entity.particle;

import java.util.Objects;

import com.hiraishin.rain.level.Level;
import com.hiraishin.rain.util.Commons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RainParticle extends Particle {

	/*
	 * Definitions
	 */
	private static final Color COLOR_1 = Color.rgb(100, 149, 237, 0.2);
	private static final Color COLOR_2 = Color.rgb(173, 216, 230, 0.2);

	/*
	 * Instance final variables
	 */
	private final Color color;

	/*
	 * Constructors
	 */
	public RainParticle(double x, double y, double width, double height, double dx, double dy, Level level) {
		super(x, y, width, height, level);

		this.dx = dx;
		this.dy = dy;

		this.color = Commons.RANDOM.nextBoolean() ? COLOR_1 : COLOR_2;
	}

	/*
	 * Instance functions
	 */
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(this.color);
		gc.fillRect(this.x, this.y, this.width,
				this.y + this.height > Commons.SCENE_GROUND ? this.y - Commons.SCENE_GROUND : this.height);
	}

	@Override
	public void tick() {
		this.x += this.dx;
		this.y += this.dy;

		if (Objects.nonNull(this.level.getPlayer())) {
			if (this.level.getPlayer().isCollidingAABB(this)) {
				kill();
			}
		}

		if (this.y > Commons.SCENE_GROUND) {
			kill();
		}
	}

}
