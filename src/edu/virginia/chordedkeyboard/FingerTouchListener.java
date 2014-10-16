package edu.virginia.chordedkeyboard;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class FingerTouchListener implements OnTouchListener {

	private TextView outputView, sentenceOutput;
	private KeyboardEventHandler pressHandler;
	private Map<Button, Boolean> fingersDownMap;
	private int downCount = 0;
	private boolean waitingForAllDown = false;
 	
	public FingerTouchListener(TextView keyOutput, TextView so, List<Button> fingers) {
		outputView = keyOutput;
		pressHandler = new KeyboardEventHandler(keyOutput, so);
		fingersDownMap = new HashMap<Button, Boolean>();
		for (Button b : fingers) {
			fingersDownMap.put(b, false);
		}
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			view.setBackgroundColor(Color.RED);
			fingersDownMap.put((Button) view, true);
			pressHandler.updateDisplay(checkFingers());
			downCount++;
        } else if (event.getAction() == MotionEvent.ACTION_UP && !waitingForAllDown) {
			pressHandler.recordPress(checkFingers());
			fingersDownMap.put((Button) view, false);
			view.setBackgroundColor(Color.LTGRAY);
			waitingForAllDown = true;
			downCount--;
			
			if (downCount == 0) {
				waitingForAllDown = false;
			}
        } else if (event.getAction() == MotionEvent.ACTION_UP && waitingForAllDown) {
        	downCount--;
			fingersDownMap.put((Button) view, false);
			view.setBackgroundColor(Color.LTGRAY);
        	if (downCount == 0) {
        		waitingForAllDown = false;
        	}
        }
		return true;
	}
	
	public Integer checkFingers() {
		int total = 0;
		for (Map.Entry<Button, Boolean> b : fingersDownMap.entrySet()) {
			if (b.getValue()) {
				String keypress = (String) b.getKey().getTag(R.string.finger_button_key);
				total += Integer.parseInt(keypress);
			}
		}
		return total;
	}

}
