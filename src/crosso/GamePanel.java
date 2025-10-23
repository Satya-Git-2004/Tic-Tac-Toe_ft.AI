package crosso;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

public class GamePanel extends Panel implements MouseMotionListener, MouseInputListener {

    private final Grid grid;
    private final AI ai;

    public GamePanel(Color color) {
        super(color);

        grid = new Grid();
        ai = new AI(grid);
    }
    @Override
    public void update(float deltaTime) {
        grid.update(deltaTime);

        // --- AI turn check ---
        if (!ai.isMoving() && grid.getTurn() == 1 && !grid.isGameEnd()) {
            // assuming 0 = human, 1 = AI
            ai.makeMove();
        }
        ai.update(deltaTime);
    }
    @Override
    public void render() {
        super.render();

        grid.render(graphicsRender);

        super.clear();
    }





    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(grid.isGameEnd()) {
            grid.reset();
        }

        if(!ai.isMoving()) {
            grid.mouseReleased(e);
        }

        if(!grid.isGameEnd()) {
            if(grid.getTurn() == 1) {
                // 1 == O
                ai.makeMove();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(!ai.isMoving()) {
            grid.mouseMoved(e);
        }
    }

}