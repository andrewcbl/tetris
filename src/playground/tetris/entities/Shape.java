package playground.tetris.entities;

import playground.tetris.listener.ShapeListener;
import playground.tetris.util.Global;

import java.awt.*;

/**
 * Created by Andrew on 9/15/2015.
 */
public class Shape {
    public static final int ROTATE = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;

    private int[][] body;
    private int status;
    private int left;
    private int top;


    private ShapeListener listener;

    public void moveLeft() {
        System.out.println("Shape moveLeft");
        left--;
    }

    public void moveRight() {
        System.out.println("Shape moveRight");
        left++;
    }

    public void moveDown() {
        System.out.println("Shape moveDown");
        top++;
    }

    public void rotate() {
        System.out.println("Shape rotate");
        status = (status + 1) % body.length;
    }

    public void drawMe(Graphics g) {
        System.out.println("Shape drawme");

        g.setColor(Color.BLUE);

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (getFlagByPoint(x, y))
                    g.fill3DRect((left + x) * Global.CELL_SIZE,
                            (top + y) * Global.CELL_SIZE,
                            Global.CELL_SIZE, Global.CELL_SIZE,
                            true);
            }
        }
    }

    private boolean getFlagByPoint(int x, int y) {
        return body[status][y * 4 + x] == 1;
    }

    public boolean isMember(int x, int y, boolean rotate) {
        int tempStatus = status;

        if (rotate) {
            tempStatus = (status + 1) % body.length;
        }

        return body[tempStatus][y * 4 + x] == 1;
    }

    private class ShapeDriver implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p/>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            while (listener.isShapeMoveDownable(Shape.this)) {
                moveDown();
                listener.shapeMoveDown(Shape.this);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addShapeListener(ShapeListener l) {
        if (l != null)
            this.listener = l;
    }

    public Shape() {
        new Thread(new ShapeDriver()).start();
    }

    public void setBody(int body[][]) {
        this.body = body;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }
}
