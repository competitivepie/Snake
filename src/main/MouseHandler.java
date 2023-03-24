package main;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game.State;

/*
 * @author Alfredo Londono
 * @since 6/10/2020
 */

public class MouseHandler extends MouseAdapter {

    private final Game game;

    /*	The amount of times a player has played
     *  a snake game.
     */
    private long gameSession;

    public MouseHandler(Game game) {
        this.game = game;
        gameSession = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        switch (Game.gameState) {
            case MENU -> {
                // Play
                if (mouseOver(mx, my, 92, 205, 300, 50)) {
                    gameSession++;
                    Game.gameState = State.PLAYING;
                }

                // Options
                if (mouseOver(mx, my, 92, 285, 300, 50)) {
                    Game.gameState = State.OPTIONS;
                }

                // Quit
                if (mouseOver(mx, my, 92, 365, 300, 50)) {
                    System.exit(-1);
                }
            }
            case OPTIONS -> {
                // FPS Toggle
                if (mouseOver(mx, my, 300, 170, 25, 25)) {
                    game.setFPSOption(false);
                } else if (mouseOver(mx, my, 400, 170, 25, 25)) {
                    game.setFPSOption(true);
                }

                // Snake color
                if (mouseOver(mx, my, 68, 340, 15, 15))
                    game.setSnakeColor(Color.BLUE);
                if (mouseOver(mx, my, 176, 340, 15, 15))
                    game.setSnakeColor(new Color(204, 0, 0));
                if (mouseOver(mx, my, 285, 340, 15, 15))
                    game.setSnakeColor(Color.BLACK);
                if (mouseOver(mx, my, 405, 340, 15, 15))
                    game.setSnakeColor(Color.WHITE);

                // Back
                if (mouseOver(mx, my, 143, 400, 200, 50)) {
                    if (game.isPaused()) {
                        Game.gameState = State.PAUSE;
                    } else {
                        Game.gameState = State.MENU;
                    }
                }
            }
            case GAME_OVER -> {
                // Retry
                if (mouseOver(mx, my, 220, 40, 115, 50)) {
                    game.resetGame();
                    gameSession++;
                    Game.gameState = State.PLAYING;
                }

                // Quit
                if (mouseOver(mx, my, 350, 40, 115, 50)) {
                    game.resetGame();
                    Game.gameState = State.MENU;
                }
            }
            case PAUSE -> {
                // Continue
                if (mouseOver(mx, my, 92, 205, 300, 50)) {
                    Game.gameState = State.PLAYING;
                    game.setPaused(false);
                }

                // Options
                if (mouseOver(mx, my, 92, 285, 300, 50)) {
                    Game.gameState = State.OPTIONS;
                }

                // Return to main menu
                if (mouseOver(mx, my, 92, 365, 300, 50)) {
                    Game.gameState = State.MENU;
                    game.setPaused(false);
                    game.resetGame();
                }
            }
            default -> {
            }
        }
    }

    // Check if mouse cursor is in a particular rectangular region of the window.
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if(mx > x && mx < x + width) {
            return my > y && my < y + height;
        }
        return false;
    }

    public long getGameSession() {
        return gameSession;
    }
}
