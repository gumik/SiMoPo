package pl.edu.uj.ii.psm.fiszki;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Random;

import android.graphics.drawable.Drawable;

public class Game {
	
	public Game(Map<String, Drawable> images) {		
		random = new Random();
		toGuess = new LinkedList<String>();
		toGuess.addAll(images.keySet());
		imagesMap = images;
	}
	
	public List<Drawable> getImages() {
		GenerateImageSet();
		return imageSet.getImages();
	}
	
	public String getWord() {
		return imageSet.getWord();
	}
	
	public int getAndCheckAnswer(int pos) {
		if (pos == imageSet.getCorrect()) {
			++correctCount;
		}
		
		++passedCount;
		
		return imageSet.getCorrect();
	}
	
	public int getPassedCount() {
		return passedCount;
	}
	
	public int getCorrectCount() {
		return correctCount;
	}
	
	public int getAllCount() {
		return imagesMap.size();
	}
	
	public boolean isGameEnd() {
		return passedCount >= imagesMap.size();
	}
	
	private void GenerateImageSet() {
		String word = getNextWord();
		List<Drawable> wrongs = getWrongs(word);
		List<Drawable> threeList = getThreeRandom(wrongs);
		
		int pos = random.nextInt(3);
		threeList.add(pos, imagesMap.get(word));
		
		imageSet = new ImageSet(word, threeList, pos);
	}
	
	private List<Drawable> getWrongs(String correct) {
		ArrayList<Drawable> list = new ArrayList<Drawable>(imagesMap.size() - 1);
		
		for (Entry<String, Drawable> entry : imagesMap.entrySet()) {
			if (entry.getKey() != correct) {
				list.add(entry.getValue());
			}
		}
		
		return list;
	}
	
	private String getNextWord() {
		return toGuess.poll();
	}
	
	private List<Drawable> getThreeRandom(List<Drawable> list) {
		ArrayList<Drawable> threeList = new ArrayList<Drawable>(3);
		List<Drawable> set = new LinkedList<Drawable>(list);
		
		for (int i = 0; i < 2; ++i) {
			int r = random.nextInt(set.size());
			Drawable selected = set.get(r);
			threeList.add(selected);
			set.remove(selected);
		}
		
		return threeList;
	}
	
	private int passedCount;
	private int correctCount;
	private Random random;
	private Queue<String> toGuess;
	private Map<String, Drawable> imagesMap;
	private ImageSet imageSet;
}

class ImageSet {
	
	public ImageSet(String word, List<Drawable> images, int correct) {
		this.word = word;
		this.images = images;
		this.correct = correct;
	}	
	
	public String getWord() {
		return word;
	}
	
	public int getCorrect() {
		return correct;
	}
	
	public List<Drawable> getImages() {
		return images;
	}

	private String word;
	private int correct;
	private List<Drawable> images;
}