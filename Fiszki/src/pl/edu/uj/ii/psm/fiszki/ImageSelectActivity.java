package pl.edu.uj.ii.psm.fiszki;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent.PointerCoords;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ImageSelectActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.image_select);
		
		newGame();
	}

	private void newGame() {	
		
		button = new ImageButton[3];
		button[0] = (ImageButton)findViewById(R.id.imageButton1);
		button[1] = (ImageButton)findViewById(R.id.imageButton2);
		button[2] = (ImageButton)findViewById(R.id.imageButton3);
		wordTextView = (TextView)findViewById(R.id.textView1);
		scoreTextView = (TextView)findViewById(R.id.textView2);
        
		for (int i = 0; i < 3; ++i) {
			button[i].setOnClickListener(this);
		}
		
		Map<String, Drawable> map = new HashMap<String, Drawable>();
		
		insert("apple", R.drawable.apple, map);
		insert("banana", R.drawable.banana, map);
		insert("butter", R.drawable.butter, map);
		insert("cake", R.drawable.cake, map);
		insert("carrot", R.drawable.carrot, map);
		insert("cheese", R.drawable.cheese, map);
		insert("cherry", R.drawable.cherry, map);
		insert("chicken", R.drawable.chicken, map);
		insert("corn", R.drawable.corn, map);
		insert("egg", R.drawable.egg, map);
		insert("grapes", R.drawable.grapes, map);
		insert("honey", R.drawable.honey, map);
		insert("icecream", R.drawable.icecream, map);
		insert("lemon", R.drawable.lemon, map);
		insert("orange", R.drawable.orange, map);
		insert("pear", R.drawable.pear, map);
		insert("plum", R.drawable.plum, map);
		insert("sweet", R.drawable.sweet, map);
		insert("tomato", R.drawable.tomato, map);
		insert("watermelon", R.drawable.watermelon, map);
		
		game = new Game(map);	
		
		nextRound();
	}
	
	private void insert(String name, int id, Map<String, Drawable> map) {
		map.put(name, getResources().getDrawable(id));
	}
	
	private void nextRound() {
		List<Drawable> images = game.getImages();
		String word = game.getWord();
		
		for (int i = 0; i < 3; ++i) {
			button[i].setImageDrawable(images.get(i));
		}
		
		wordTextView.setText(word);		
		scoreTextView.setText(getScoreString());
	}
	
	private String getScoreString() {
		StringBuilder sb = new StringBuilder();
		sb.append(game.getPassedCount());
		sb.append(" / ");
		sb.append(game.getAllCount());
		sb.append(" (");
		sb.append(game.getCorrectCount());
		sb.append(" correct)");
		return sb.toString();
	}

	public void onClick(View v) {
		int id = v.getId();
		int pos = 0;
		
		if (id == R.id.imageButton1) {
			pos = 0;
		} else if (id == R.id.imageButton2) {
			pos = 1;
		} else if (id == R.id.imageButton3) {
			pos = 2;
		}
		
		game.getAndCheckAnswer(pos);
				
		if (!game.isGameEnd()) {
			nextRound();
		} else {
			setContentView(R.layout.game_end);	
			TextView pointTextView = (TextView)findViewById(R.id.pointTextView);
			TextView percentTextView = (TextView)findViewById(R.id.percentTextView);
			TextView noteTextView = (TextView)findViewById(R.id.noteTextView);
			
			Button onceAgainButton = (Button)findViewById(R.id.onceAgainButton);
			
			double percent = (double)game.getCorrectCount() / game.getAllCount() * 100;
			
			pointTextView.setText("You have " + game.getCorrectCount() + " / " + game.getAllCount());
			percentTextView.setText("It is " + percent + "%");
			
			String note = null;
			if (percent < 51) {
				note = "F";
			} else if (percent < 61) {
				note = "E";
			} else if (percent < 71) {
				note = "D";
			} else if (percent < 81) {
				note = "C";
			} else if (percent < 91) {
				note = "B";
			} else {
				note = "A :D";
			}
			
			noteTextView.setText("Your note is: " + note);
			
			onceAgainButton.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					setContentView(R.layout.image_select);
					newGame();
				}
			});
		}
	}
	
	private ImageButton[] button;
	private TextView wordTextView;
	private TextView scoreTextView;
	private Game game;
}
