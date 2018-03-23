package com.wynprice.chemistry;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class KeyPressedHandler 
{
	 public static void main() {
	        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

	            public boolean dispatchKeyEvent(KeyEvent ke) {
	                synchronized (KeyPressedHandler.class) {
	                    switch (ke.getID()) {
	                    case KeyEvent.KEY_PRESSED:
	                        if (ke.getKeyCode() == KeyEvent.VK_ENTER) 
	                        	Main.calc();
	                        if(ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_DOWN)
	                        	Main.zoom(ke.getKeyCode() == KeyEvent.VK_DOWN);
	                        break;
	                    }
	                    return false;
	                }
	            }
	        });
	    }
}
