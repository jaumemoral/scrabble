package scrabble;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class Scrabble {

	Map<Character, Frequency> frequenciesIndexedByTile = new HashMap<Character, Frequency>();

	Scrabble(String tiles) {
		for (char tile : tiles.toCharArray())
			addTile(tile);
	}

	public void addTile(char tile) {
		try {
			frequenciesIndexedByTile.get(tile).increase();
		} catch (NullPointerException e) {
			frequenciesIndexedByTile.put(tile, new Frequency(1));
		}
	}

	public void removeTile(char tile) {
		frequenciesIndexedByTile.get(tile).decrease();
	}

	public void removeTiles(String tiles) {
		for (char tile : tiles.toCharArray())
			removeTile(tile);
	}

	SortedMap<Frequency, Set<Character>> indexTilesByFrequency() throws TooManyTilesTakenException {
		SortedMap<Frequency, Set<Character>> tilesIndexedByFrequency = new TreeMap<Frequency, Set<Character>>();
		for (Map.Entry<Character, Frequency> pair : frequenciesIndexedByTile.entrySet()) {
			Character tile=pair.getKey();
			Frequency frequency=pair.getValue();
			if (frequency.isInvalid()) throw new TooManyTilesTakenException(
					"Invalid input. More "+tile+"'s have been taken from the bag than possible.");
			Set<Character> tiles=tilesIndexedByFrequency.get(frequency);
			if (tiles==null) {
				tiles=new TreeSet<Character>();
			    tilesIndexedByFrequency.put(frequency,tiles);
			}
			tiles.add(tile);
		}
		return tilesIndexedByFrequency;
	}

	String printIndexByFrequency(SortedMap<Frequency, Set<Character>> tilesIndexedByFrequency) {
		StringBuilder result = new StringBuilder();
		for (Map.Entry<Frequency, Set<Character>> pair : tilesIndexedByFrequency.entrySet()) {
			result.append(pair.getKey());
			result.append(": ");
			result.append(pair.getValue().toString().replace("[","").replace("]",""));
			result.append("\n");
		}
		return result.toString();
	}


	public String toString() {
		try {
			return printIndexByFrequency(indexTilesByFrequency());
		} catch (TooManyTilesTakenException e) {
			return e.getMessage();
		}
	}

}

@SuppressWarnings("serial")
class TooManyTilesTakenException extends Exception {
    public TooManyTilesTakenException(String message) {
       super(message);
    }
}

class Frequency implements Comparable<Frequency> {

	Integer value;

	Frequency(int value) {
		this.value=value;
	}

	public int getValue() {
		return this.value;
	}

	public void increase() {
		this.value++;
	}

	public void decrease() {
		this.value--;
	}

	public boolean isInvalid() {
		return this.value<0;
	}

	// Return opposite value. High frequencies are the first ones
	public int compareTo(Frequency f) {
		return -value.compareTo(f.getValue());
	}

	public String toString() {
		return value.toString();
	}

}