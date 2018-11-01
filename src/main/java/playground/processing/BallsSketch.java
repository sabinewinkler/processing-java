package playground.processing;

import org.pmw.tinylog.Logger;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * Example taken from <a href="https://happycoding.io/tutorials/java/processing-in-java">HappyCoding - Processing in Java</a>
 */
public class BallsSketch extends PApplet {

    private final List<Ball> balls = new ArrayList<>();

    public static void main(String[] args) {
        final BallsSketch ballsSketch = new BallsSketch();
        PApplet.runSketch(new String[]{BallsSketch.class.getName()}, ballsSketch);
    }

    @Override
    public void settings() {
        super.settings();
        size(1000, 1000);
        balls.add(new Ball(this, (float) width / 2, (float) height / 2));
    }

    @Override
    public void draw() {
        background(5555);
        balls.forEach(ball -> {
            ball.step();
            ball.render();
        });
    }

    @Override
    public void mouseDragged() {
        balls.add(new Ball(this, mouseX, mouseY));
    }
}

class Ball {

    private final PApplet sketch;
    private final float size;

    private float x;
    private float y;
    private float xSpeed;
    private float ySpeed;

    Ball(final PApplet sketch, float x, float y) {

        this.sketch = sketch;
        this.size = sketch.random(10, 100);

        this.x = x;
        this.y = y;
        this.xSpeed = sketch.random(-10, 10);
        this.ySpeed = sketch.random(-10, 10);

        Logger.debug("Created new {}" + toString());
    }

    void step() {
        x += xSpeed;
        if (x < 0 || x > sketch.width) {
            xSpeed *= -1;
        }

        y += ySpeed;
        if (y < 0 || y > sketch.height) {
            ySpeed *= -1;
        }

        Logger.debug("Next step with x = {}, y = {}, xSpeed = {}, ySpeed = {}", x, y, xSpeed, ySpeed);
    }

    void render() {
        sketch.ellipse(x, y, size, size);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "sketch=" + sketch +
                ", x=" + x +
                ", y=" + y +
                ", size=" + size +
                ", xSpeed=" + xSpeed +
                ", ySpeed=" + ySpeed +
                '}';
    }
}