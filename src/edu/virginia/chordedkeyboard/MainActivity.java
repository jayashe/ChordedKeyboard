package edu.virginia.chordedkeyboard;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	Button pinky, ring, middle, pointer, thumb;
	TextView selectedLetter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ArrayList<Button> fingers = new ArrayList<Button>();
        
        pinky = (Button) findViewById(R.id.Pinky);
        pinky.setTag(R.string.finger_button_key, "16");
        fingers.add(pinky);
        
        ring = (Button) findViewById(R.id.Ring);
        ring.setTag(R.string.finger_button_key, "8");
        fingers.add(ring);
        
        middle = (Button) findViewById(R.id.Middle);
        middle.setTag(R.string.finger_button_key, "4");
        fingers.add(middle);
        
        pointer = (Button) findViewById(R.id.Pointer);
        pointer.setTag(R.string.finger_button_key, "2");
        fingers.add(pointer);
        
        thumb = (Button) findViewById(R.id.Thumb);
        thumb.setTag(R.string.finger_button_key, "1");
        fingers.add(thumb);
        
        selectedLetter = (TextView) findViewById(R.id.selectedLetterView);
        
        FingerTouchListener ftl = new FingerTouchListener(selectedLetter, fingers);
        pinky.setOnTouchListener(ftl);
        ring.setOnTouchListener(ftl);
        middle.setOnTouchListener(ftl);
        pointer.setOnTouchListener(ftl);
        thumb.setOnTouchListener(ftl);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
   
}
