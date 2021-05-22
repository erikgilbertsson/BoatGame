package se.lth.MAMN01.team4.boatgame;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = true;
		if (ContextCompat.checkSelfPermission(AndroidLauncher.this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(AndroidLauncher.this, new String[] { Manifest.permission.RECORD_AUDIO }, 1);
		}
		else {
			System.out.println("Permission already granted");
		}
		initialize(new BoatGame(), config);
	}

	@Override
	public void onBackPressed() {
		//let libGDX handle this
	}
}
