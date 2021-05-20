package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Game;

public class BoatGame extends Game {

	public final static int MAIN_MENU = 0;
	public final static int GAME = 1;
	public final static int INSTRUCTION = 2;

	SplashScreen splashScreen;
	MainMenuScreen mainMenuScreen;
	GameScreen gameScreen;
	InstructionScreen instructionScreen;

	@Override
	public void create () {
		splashScreen = new SplashScreen(this);
		setScreen(splashScreen);
	}

	@Override
	public void render () {
		super.render();
	}


	public void changeScreen(int screen) {
		switch(screen){
			case MAIN_MENU:
				if(mainMenuScreen == null) mainMenuScreen = new MainMenuScreen(this);
				setScreen(mainMenuScreen);
				break;
			case GAME:
				if(gameScreen == null) gameScreen = new GameScreen(this);
				setScreen(gameScreen);
				break;
			case INSTRUCTION:
				if(instructionScreen == null) instructionScreen = new InstructionScreen(this);
				setScreen(instructionScreen);
				break;
		}
	}

	@Override
	public void dispose () {
		splashScreen.dispose();
		mainMenuScreen.dispose();
		gameScreen.dispose();
		instructionScreen.dispose();
	}
}
