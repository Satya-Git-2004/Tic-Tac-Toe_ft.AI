package crosso;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Grid implements IGameObject {

    private final Marker[][] markers;
    private int turn; // 0 = X, 1 = O
    private boolean gameEnd;

    public Grid() {
        markers = new Marker[Main.ROWS][Main.ROWS];
        turn = 0;
        gameEnd = false;
    }

    @Override
    public void update(float deltaTime) {
        // Nothing special here for now
    }

    @Override
    public void render(Graphics2D g) {
        int size = Main.WIDTH / Main.ROWS;

        // Draw grid lines
        g.setColor(Color.WHITE);
        for (int i = 1; i < Main.ROWS; i++) {
            g.drawLine(i * size, 0, i * size, Main.HEIGHT); // vertical
            g.drawLine(0, i * size, Main.WIDTH, i * size);  // horizontal
        }

        // Draw placed markers
        for (int x = 0; x < Main.ROWS; x++) {
            for (int y = 0; y < Main.ROWS; y++) {
                if (markers[x][y] != null) {
                    markers[x][y].render(g);
                }
            }
        }
    }

    /** Called when AI or Player makes a move */
    public void placeMarker(int index) {
        int x = index % Main.ROWS;
        int y = index / Main.ROWS;

        if (markers[x][y] == null && !gameEnd) {
            markers[x][y] = new Marker(x, y, turn);
            turn = (turn + 1) % 2; // switch turn
        }
    }

    public Marker[][] getMarkers() {
        return markers;
    }

    public int getTurn() {
        return turn;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void mouseMoved(MouseEvent e) {
        // optional: highlight squares
    }

    public void mouseReleased(MouseEvent e) {
        if (gameEnd) return;

        int size = Main.WIDTH / Main.ROWS;
        int x = e.getX() / size;
        int y = e.getY() / size;

        if (x >= 0 && x < Main.ROWS && y >= 0 && y < Main.ROWS) {
            placeMarker(y * Main.ROWS + x);
        }
    }

    public void reset() {
        for (int x = 0; x < Main.ROWS; x++) {
            for (int y = 0; y < Main.ROWS; y++) {
                markers[x][y] = null;
            }
        }
        turn = 0;
        gameEnd = false;
    }
}