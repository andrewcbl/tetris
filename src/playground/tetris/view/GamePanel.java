package playground.tetris.view;

import playground.tetris.entities.Ground;
import playground.tetris.entities.Shape;
import playground.tetris.util.Global;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andrew on 9/15/2015.
 */
public class GamePanel extends JPanel {
    private Ground ground;
    private Shape shape;

    public void display(Ground ground, Shape shape) {
        System.out.println("Gamepanel's display");
        this.ground = ground;
        this.shape = shape;
        this.repaint();
    }

    /**
     * Calls the UI delegate's paint method, if the UI delegate
     * is non-<code>null</code>.  We pass the delegate a copy of the
     * <code>Graphics</code> object to protect the rest of the
     * paint code from irrevocable changes
     * (for example, <code>Graphics.translate</code>).
     * <p/>
     * If you override this in a subclass you should not make permanent
     * changes to the passed in <code>Graphics</code>. For example, you
     * should not alter the clip <code>Rectangle</code> or modify the
     * transform. If you need to do these operations you may find it
     * easier to create a new <code>Graphics</code> from the passed in
     * <code>Graphics</code> and manipulate it. Further, if you do not
     * invoker super's implementation you must honor the opaque property,
     * that is
     * if this component is opaque, you must completely fill in the background
     * in a non-opaque color. If you do not honor the opaque property you
     * will likely see visual artifacts.
     * <p/>
     * The passed in <code>Graphics</code> object might
     * have a transform other than the identify transform
     * installed on it.  In this case, you might get
     * unexpected results if you cumulatively apply
     * another transform.
     *
     * @param g the <code>Graphics</code> object to protect
     * @see #paint
     * @see ComponentUI
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0xcfcfcf));
        g.fillRect(0, 0, Global.WIDTH * Global.CELL_SIZE,
                Global.HEIGHT * Global.CELL_SIZE);

        if (ground != null && shape != null) {
            ground.drawMe(g);
            shape.drawMe(g);
        }
    }

    public GamePanel() {
        this.setSize(Global.WIDTH * Global.CELL_SIZE,
                Global.HEIGHT * Global.CELL_SIZE);
    }
}
