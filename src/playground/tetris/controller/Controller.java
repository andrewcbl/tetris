package playground.tetris.controller;

import playground.tetris.entities.Ground;
import playground.tetris.entities.Shape;
import playground.tetris.entities.ShapeFactory;
import playground.tetris.listener.ShapeListener;
import playground.tetris.view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Andrew on 9/16/2015.
 */
public class Controller extends KeyAdapter implements ShapeListener {
    private Shape shape;
    private ShapeFactory shapeFactory;
    private Ground ground;
    private GamePanel gamePanel;

    /**
     * Invoked when a key has been pressed.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (ground.isMovable(shape, Shape.ROTATE))
                    shape.rotate();
                break;
            case KeyEvent.VK_DOWN:
                if (isShapeMoveDownable(shape))
                    shape.moveDown();
                break;
            case KeyEvent.VK_LEFT:
                if (ground.isMovable(shape, Shape.LEFT))
                    shape.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                if (ground.isMovable(shape, Shape.RIGHT))
                    shape.moveRight();
                break;
        }

        gamePanel.display(ground, shape);
    }

    @Override
    public void shapeMoveDown(Shape shape) {
        gamePanel.display(ground, shape);
    }

    @Override
    public synchronized boolean isShapeMoveDownable(Shape shape) {
        if (this.shape != shape)
            return false;

        if (ground.isMovable(shape, shape.DOWN))
            return true;
        ground.accept(shape);

        if (!ground.isFull())
            this.shape = shapeFactory.getShape(this);

        return false;
    }

    public void newGame() {
        shape = shapeFactory.getShape(this);
    }

    public Controller(ShapeFactory shapeFactory,
                           Ground ground,
                           GamePanel gamePanel) {
        this.shapeFactory = shapeFactory;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }
}
