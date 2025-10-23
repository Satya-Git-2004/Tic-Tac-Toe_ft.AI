package crosso;

import java.awt.Graphics2D;

public class AI implements IGameObject {

    private final Minimax minimax;
    private final Grid grid;

    private int currentTime;
    private boolean startTimer;

    public AI(Grid grid) {
        this.grid = grid;
        minimax = new Minimax();
    }

    public void makeMove() {
        currentTime = 0;
        startTimer = true;
    }

    @Override
    public void update(float deltaTime) {
        if(!startTimer) {
            return;
        }

        currentTime += (int) deltaTime;
        int timeInterval = 10;
        if(currentTime >= timeInterval) {
            grid.placeMarker(minimax.getBestMove(grid.getMarkers(), grid.getTurn()));
            startTimer = false;
        }
    }

    @Override
    public void render(Graphics2D graphicsRender) {

    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isMoving() {
        return startTimer;
    }

}