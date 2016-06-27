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

	SortedMap<Frequency, Set<Character>> indexTilesByFrequency() {
		SortedMap<Frequency, Set<Character>> tilesIndexedByFrequency = new TreeMap<Frequency, Set<Character>>();
		for (Map.Entry<Character, Frequency> pair : frequenciesIndexedByTile.entrySet()) {
			Character tile=pair.getKey();
			Frequency frequency=pair.getValue();
			Set<Character> tiles=tilesIndexedByFrequency.get(frequency);
			if (tiles==null) {
				tiles=new TreeSet<Character>();
			    tilesIndexedByFrequency.put(frequency,tiles);
			}
			tiles.add(tile);
		}
		return tilesIndexedByFrequency;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		StringBuilder errors = new StringBuilder();
		for (Map.Entry<Frequency, Set<Character>> pair : indexTilesByFrequency().entrySet()) {
			if (pair.getKey().isInvalid()) {
				for (char tile : pair.getValue())
					errors.append("Invalid input. More "+tile+"'s have been taken from the bag than possible.");
			}
		}

		for (Map.Entry<Frequency, Set<Character>> pair : indexTilesByFrequency().entrySet()) {
			result.append(pair.getKey());
			result.append(": ");
			boolean firstTime=true;
			for (Character tile : pair.getValue()) {
				if (!firstTime) result.append(", ");
				result.append(tile);
				firstTime=false;
			}
			result.append("\n");
		}
		System.out.println(result);
		if (errors.toString().isEmpty()) {
			return result.toString();
		} else {
			return errors.toString();
		}
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