package edu.virginia.chordedkeyboard;

import android.widget.TextView;

public class KeyboardEventHandler {
	TextView LetterOutput, SentenceOutput;
	StringBuilder sb;
	
	public KeyboardEventHandler(TextView lo, TextView so) {
		LetterOutput = lo;
		SentenceOutput = so;
		sb = new StringBuilder();
	}
	
	public void recordPress(int i) {
		if (i == 30) {
			backspace();
		} else if (i == 31) {
			//Enter
		} else {
			sb.append(mapPress(i));
		}
		SentenceOutput.setText(sb.toString());
		LetterOutput.setText("");
	}
	
	public void updateDisplay(int i) {
		if (i == 30) {
			LetterOutput.setText("Backspace");
		} else if (i == 31) {
			LetterOutput.setText("Enter");
		} else {
			LetterOutput.setText(mapPress(i));
		}
	}
	
	public void clear() {
		sb.delete(0, sb.length());
	}
	
	private void backspace() {
		if (sb.length() > 0) {
			sb.delete(sb.length() - 1, sb.length());
		}
	}
	
	private String mapPress(int input) {
		switch (input) {
			case 1: return "a";
			case 2: return "b";
			case 3: return "c";
			case 4: return "d";
			case 5: return "e";
			case 6: return "f";
			case 7: return "g";
			case 8: return "h";
			case 9: return "i";
			case 10: return "j";
			case 11: return "k";
			case 12: return "l";
			case 13: return "m";
			case 14: return "n";
			case 15: return "o";
			case 16: return "p";
			case 17: return "q";
			case 18: return "r";
			case 19: return "s";
			case 20: return "t";
			case 21: return "u";
			case 22: return "v";
			case 23: return "w";
			case 24: return "x";
			case 25: return "y";
			case 26: return "z";
		}
		return "-";
	}
}
