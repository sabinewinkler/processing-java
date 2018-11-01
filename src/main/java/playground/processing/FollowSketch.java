package playground.processing;

import processing.core.PApplet;

/**
 * Example taken from <a href="https://processing.org/examples/follow1.html">Processing 3+ - Examples - Follow 1</a>
 */
public class FollowSketch extends PApplet {

    private float x = 100;
    private float y = 100;
    private static final float SEG_LENGTH = 50;

    public static void main(String[] args) {
        PApplet.main(new String[]{FollowSketch.class.getName()});
    }

    public void settings() {
        size(640, 360);
    }

    public void setup() {
        strokeWeight(20.0f);
        stroke(255, 100);
    }

    public void draw() {
        background(0);

        float dx = mouseX - x;
        float dy = mouseY - y;
        float angle1 = atan2(dy, dx);
        x = mouseX - (cos(angle1) * SEG_LENGTH);
        y = mouseY - (sin(angle1) * SEG_LENGTH);

        segment(x, y, angle1);
        ellipse(x, y, 20, 20);
    }

    private void segment(float x, float y, float a) {
        pushMatrix();
        translate(x, y);
        rotate(a);
        line(0, 0, SEG_LENGTH, 0);
        popMatrix();
    }
}
