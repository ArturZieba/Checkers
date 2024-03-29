package siwy;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static siwy.Checkers.TILE_SIZE;

public class Piece extends StackPane {

    private PieceType type;

    private double mouseX, mouseY;
    private double oldMouseX, oldMouseY;

    public PieceType getType() {
        return type;
    }

    public double getOldMouseX() {
        return oldMouseX;
    }

    public double getOldMouseY() {
        return oldMouseY;
    }

    public Piece(PieceType type, int x, int y) {
        this.type = type;

        move(x , y);

        Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3, TILE_SIZE * 0.26);
        ellipse.setFill(type == PieceType.RED ? Color.WHITE : Color.DARKRED);

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(TILE_SIZE * 0.03);

        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3 *2) / 2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 *2) / 2);

        Ellipse bg = new Ellipse(TILE_SIZE * 0.3, TILE_SIZE * 0.26);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * 0.03);

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3 *2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 *2) / 2 + TILE_SIZE * 0.07);

        getChildren().addAll(bg, ellipse);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldMouseX, e.getSceneY() - mouseY + oldMouseY);
        });
    }

    public void move(int x, int y) {
        oldMouseX = x * TILE_SIZE;
        oldMouseY = y * TILE_SIZE;
        relocate(oldMouseX, oldMouseY);
    }

    public void abortMove(){
        relocate(oldMouseX, oldMouseY);
    }

}
