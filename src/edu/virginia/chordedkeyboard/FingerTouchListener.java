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

	private TextView outputView;
	private Map<Button, Boolean> fingersDownMap;
	private int downCount = 0;
	private boolean waitingForAllDown = false;
 	
	public FingerTouchListener(TextView keyOutput, List<Button> fingers) {
		outputView = keyOutput;
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
			downCount++;
        } else if (event.getAction() == MotionEvent.ACTION_UP && !waitingForAllDown) {
			outputView.setText(checkFingers().getValue().toString());
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
	
	public Map.Entry<String, Integer> checkFingers() {
		StringBuilder sb = new StringBuilder();
		int total = 0;
		for (Map.Entry<Button, Boolean> b : fingersDownMap.entrySet()) {
			if (b.getValue()) {
				String keypress = (String) b.getKey().getTag(R.string.finger_button_key);
				sb.append(keypress).append(" ");
				total += Integer.parseInt(keypress);
			}
		}
		Map.Entry<String, Integer> result = new AbstractMap.SimpleEntry<String, Integer>(sb.toString(), total);
		return result;
	}

}
