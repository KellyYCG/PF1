package br.ol.smb.infra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
    
    public static boolean[] keyDown = new boolean[256];
    public static boolean[] keyDownConsumed = new boolean[256];
    
    
    //Temporal
    public static boolean isKeyDown(int keyCode) {
        return keyCode >= 0 && keyCode < keyDown.length && keyDown[keyCode];
    }
    //temporal
    public static boolean isKeyPressed(int keyCode) {
        if (keyCode >= 0 && keyCode < keyDown.length && !keyDownConsumed[keyCode] && keyDown[keyCode]) {
            keyDownConsumed[keyCode] = true;
            return true;
        }
        return false;
    }

    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    /*
    public static boolean isKeyPressed(int keyCode) {
        if (!keyDownConsumed[keyCode] && keyDown[keyCode]) {
            keyDownConsumed[keyCode] = true;
            return true;
        }
        return false;
    }
    public static boolean isKeyDown(int keyCode) {
        return keyDown[keyCode];
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keyDown[e.getKeyCode()] = true;
    }
    
    
    @Override
    public void keyReleased(KeyEvent e) {
        keyDown[e.getKeyCode()] = false;
        keyDownConsumed[e.getKeyCode()] = false;
    }
    
    */
    
    //Temporal
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keyDown.length) {
            keyDown[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keyDown.length) {
            keyDown[keyCode] = false;
            keyDownConsumed[keyCode] = false;
        }
    }
    
}
