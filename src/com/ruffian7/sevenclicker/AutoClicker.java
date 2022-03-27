package com.ruffian7.sevenclicker;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.ruffian7.sevenclicker.gui.ClickerGui;
import com.ruffian7.sevenclicker.listener.KeyListener;
import com.ruffian7.sevenclicker.listener.MouseListener;

public class AutoClicker {
	
	public static Robot robot;
	public static Point mousePos;
	public static ClickerGui gui = new ClickerGui();
	
	public static boolean toggled = false;
	public static boolean skipNext = false;
	public static boolean activated = false;
	
	private static int delay = -1;
	public static long lastTime = 0;
	public static int minCps = 0;
	public static int maxCps = 0;
	public static int button = 1;
	
	public static String toggleKey = "P";
	
	public static void main(String[] args) throws InterruptedException {
		
		try {
			SaveData data = (SaveData) ResourceManager.load("1.save");
			
			minCps = data.minCps;
			maxCps = data.maxCps;
			toggleKey = data.toggleKey;
			
		} catch (Exception e) {
			if(e.getMessage().toLowerCase().indexOf("1.save") != -1) {
				SaveData data = new SaveData();
				
				data.maxCps = maxCps;
				data.minCps = minCps;
				data.toggleKey = toggleKey;
				
				try {
					ResourceManager.save(data, "1.save");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		}
			
		LogManager.getLogManager().reset();
		Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
		
		try {
			robot = new Robot();
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeMouseListener(new MouseListener());
			GlobalScreen.addNativeKeyListener(new KeyListener());
		} catch (NativeHookException | AWTException e) {
			e.printStackTrace();
		}
		
		Random random = new Random();
		if (delay == -1) {
			delay = random.nextInt((1000 / minCps) - (1000 / maxCps) + 1) + (1000 / maxCps);
		}
		
		while(true) {
			Thread.sleep(1);
			long thisTime = System.currentTimeMillis();
			
			if (thisTime - lastTime >= delay && activated && toggled) {
				click();
				//System.out.println("Click!");
				lastTime = thisTime;
				delay = random.nextInt((1000 / minCps) - (1000 / maxCps) + 1) + (1000 / maxCps);
			}
			//System.out.println(toggled);
		}
		
	}
	
	private static void click() {
		skipNext = true;
		robot.mousePress((button == 1) ? 16 : 4);
		robot.mouseRelease((button == 1) ? 16 : 4);
	}
	
	public static void updateIcon(int Case) {
		switch(Case) {
			case 0:
				break;
		}
	}
	
	public static void toggle() {
		toggled = !toggled;
		activated = false;
		skipNext = false;
	}
	
}
