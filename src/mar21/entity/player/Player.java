package mar21.entity.player;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import mar21.entity.Entity;
import mar21.game.Game;
import mar21.game.Upgrades;
import mar21.game.Upgrades.UpgradeType;
import mar21.input.InputHandler;
import mar21.input.bind.KeyStroke;
import mar21.resources.ShatteredImageView;

public final class Player extends Entity {

	public static final double HEIGHT = 80, WIDTH = 66;
	private final Timeline walking = new Timeline();
	{
		walking.setCycleCount(Timeline.INDEFINITE);
		walking.getKeyFrames().addAll(
			new KeyFrame(Duration.millis(000), e -> view.shatter(1, 0)), 
			new KeyFrame(Duration.millis(100), e -> view.shatter(1, 1)), 
			new KeyFrame(Duration.millis(200), e -> view.shatter(1, 2)), 
			new KeyFrame(Duration.millis(300), e -> view.shatter(1, 3))
		);
	}

	private Upgrades upgrades = Upgrades.getInstance();
	private boolean onGround, isWalking;
	
	private int health = 1 + upgrades.get(UpgradeType.LIFE);
	private double speed = 5 + upgrades.get(UpgradeType.SPEED);
	private double jump = -12 - upgrades.get(UpgradeType.JUMP_HEIGHT);
	
	public Player(double x, double y, InputHandler input) {
		super(x, y, new ShatteredImageView("player", 4 * WIDTH, 2 * HEIGHT, 2, 4));
		
		input.bind(KeyCode.SPACE, KeyStroke.KEY_HELD, () -> {
			if (onGround) {
				dy = jump;
				onGround= false;
			}
		});

		input.bind(KeyCode.A, KeyStroke.KEY_HELD, () -> {
			move(-speed, 0);
			isWalking = true;
			view.setScaleX(-1);
		});
		
		input.bind(KeyCode.D, KeyStroke.KEY_HELD, () -> {
			move(speed, 0);
			isWalking = true;
			view.setScaleX(1);
		});
	}
	
	@Override
	public void update() {
		dy += (onGround ? (dy >= 0.5 ? -dy : 0) : 0.5);
		move(dx, dy);

		if (getX() < 0) {
			setPosition(0D, null);
		} else if (getX() > Game.SCREEN_WIDTH - WIDTH) {
			setPosition(Game.SCREEN_WIDTH - WIDTH, null);
		}
		
		if (getY() < 0) {
			setPosition(null, 0D);	
		} else if (getY() > Game.GROUND) {
			setPosition(null, Game.GROUND);
			onGround = true;
		}
		
		if (isWalking) {
			walking.play();
			isWalking = false;
		} else {
			walking.stop();
			view.shatter(0, 0);
		}
	}
	
	public void applyDamage() {
		this.health -= 1;
	}
	
	public int getHealth() {
		return this.health;
	}
}
