package com.moxisoftworks.beetzgame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.support.v4.app.NavUtils;

@SuppressWarnings("unused")
public class Game extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        /** post this in AndroidManifest.xml to make fullscreen **/
        // android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
        
        //This sets view to Panel instead of default View
        setContentView(new Panel(this)); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_game, menu);
        return true;
    }

    
}
