package playground.tetris.listener;

import playground.tetris.entities.Shape;

/**
 * Created by Andrew on 9/16/2015.
 */
public interface ShapeListener {
    void shapeMoveDown(Shape shape);

    boolean isShapeMoveDownable(Shape shape);
}
