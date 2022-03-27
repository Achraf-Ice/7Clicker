package com.ruffian7.sevenclicker.listener;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import com.ruffian7.sevenclicker.AutoClicker;

public class MouseListener implements NativeMouseListener {

	@Override
	public void nativeMouseClicked(NativeMouseEvent event) {
		// NO-OP
		
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent event) {
		if(!AutoClicker.skipNext) {
			if(event.getButton() == AutoClicker.button) {
				AutoClicker.activated = true;
			}
		}
		
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent event) {
		if (!AutoClicker.skipNext) {
			if(event.getButton() == AutoClicker.button) {
				AutoClicker.activated = false;
			}
			
		} else {
			AutoClicker.skipNext = false;
		}
		
	}

}
