package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Game;

public class BoatGame extends Game {

	public final static int MAIN_MENU = 0;
	public final static int GAME = 1;
	public final  static  int NEVIGATE = 2;

	SplashScreen splashScreen;
	MainMenuScreen mainMenuScreen;
	GameScreen gameScreen;

	Navigation nevigat;


	@Override
	public void create () {
		splashScreen = new SplashScreen(this);
		setScreen(splashScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		splashScreen.dispose();
		mainMenuScreen.dispose();
		gameScreen.dispose();
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
			case NEVIGATE:
				if (nevigat==null) nevigat = new Navigation(this);
				setScreen(nevigat);


		}
	}
}
