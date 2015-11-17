package playground.tetris.entities;

import playground.tetris.util.Global;

import java.awt.*;

/**
 * Created by Andrew on 9/15/2015.
 */
public class Ground {
    private int[][] obstacles = new int[Global.WIDTH][Global.HEIGHT];

    public void accept(Shape shape) {
        System.out.println("Ground's accept method");

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (shape.isMember(x, y, false))
                    obstacles[shape.getLeft() + x][shape.getTop() + y] = 1;
            }
        }

        deleteFullLine();
    }

    private void deleteFullLine() {
        for (int y = Global.HEIGHT - 1; y >= 0; y--) {
            boolean full = true;

            for (int x = 0; x < Global.WIDTH; x++) {
                if (obstacles[x][y] == 0) {
                    full = false;
                    break;
                }
            }

            if (full) {
                deleteLine(y);
            }
        }
    }

    private void deleteLine(int lineNum) {
        for (int y = lineNum; y > 0; y--) {
            for (int x = 0; x < Global.WIDTH; x++) {
                obstacles[x][y] = obstacles[x][y-1];
            }
        }

        for (int x = 0; x < Global.WIDTH; x++)
            obstacles[x][0] = 0;
    }

    public void drawMe(Graphics g) {
        System.out.println("Ground's drawMe");

        for (int x = 0; x < Global.WIDTH; x++) {
            for (int y = 0; y < Global.HEIGHT; y++) {
                if (obstacles[x][y] == 1)
                    g.fill3DRect(x * Global.CELL_SIZE,
                            y * Global.CELL_SIZE,
                            Global.CELL_SIZE,
                            Global.CELL_SIZE,
                            true);
            }
        }
    }

    public boolean isMovable(Shape shape, int action) {
        int left = shape.getLeft();
        int top = shape.getTop();

        switch (action) {
            case Shape.LEFT:
                left--;
                break;
            case Shape.RIGHT:
                left++;
                break;
            case Shape.DOWN:
                top++;
                break;
        }

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (shape.isMember(x, y, action == shape.ROTATE)) {
                    if (top + y >= Global.HEIGHT ||
                            left + x < 0 ||
                            left + x >= Global.WIDTH ||
                            obstacles[left + x][top + y] == 1)
                        return false;
                }
            }
        }

        return true;
    }

    public boolean isFull() {
        for (int x = 0; x < Global.WIDTH; x++) {
            if (obstacles[x][0] == 1)
                return true;
        }

        return false;
    }
}
