package com.ruffian7.sevenclicker.listener;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.ruffian7.sevenclicker.AutoClicker;

public class KeyListener implements NativeKeyListener {

	@Override
	public void nativeKeyPressed(NativeKeyEvent event) {
		
		if (NativeKeyEvent.getKeyText(event.getKeyCode()).equals(AutoClicker.toggleKey)) {
			AutoClicker.toggle();
		}
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent event) {
		
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent event) {
		// NO-OP
		
	}
	
}
