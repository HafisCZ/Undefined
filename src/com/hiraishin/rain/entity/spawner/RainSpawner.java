/*
 * Copyright (c) 2017 - 2018 Hiraishin Software. All Rights Reserved.
 */

package com.hiraishin.rain.entity.spawner;

import com.hiraishin.rain.entity.particle.RainParticle;
import com.hiraishin.rain.level.Level;

public final class RainSpawner extends Spawner {

    public static final int WIDTH = 1;
    public static final int HEIGHT_MIN = 10;
    public static final int HEIGHT_MAX = 40;
    public static final int SPEED_X = 0;
    public static final int SPEED_Y_MIN = 10;
    public static final int SPEED_Y_MAX = 40;

    public RainSpawner(double x, double y, double width, double height, Level level, int rate,
                       int variation, int count) {
        super(x, y, width, height, level, rate, variation, count);
    }

    @Override
    public void spawn() {
        this.level.add(new RainParticle(getRandomX(), getRandomY(), WIDTH,
                                        HEIGHT_MIN + RANDOM.nextInt(HEIGHT_MAX - HEIGHT_MIN + 1),
                                        SPEED_X,
                                        SPEED_Y_MIN + RANDOM.nextInt(SPEED_Y_MAX - SPEED_Y_MIN + 1),
                                        this.level));
    }
}
