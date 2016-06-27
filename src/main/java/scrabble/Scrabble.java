package scrabble;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class Scrabble {

	
	Map<Character, Integer> frequenciesIndexedByTile = new HashMap<Character, Integer>();

	Scrabble() {
		this("AAAAAAAAA" + "BB" + "CC" + "DDDD" + "EEEEEEEEEEEE" + "FF" + "GGG" + "HH" + "IIIIIIIII" + "J" + "K"
				+ "LLLL" + "MM" + "NNNNNN" + "OOOOOOOO" + "PP" + "Q" + "RRRRRR" + "SSSS" + "TTTTTT" + "UUUU" + "VV"
				+ "WW" + "X" + "YY" + "Z" + "__");
	}

	Scrabble(String tiles) {
		for (char tile : tiles.toCharArray())
			addTile(tile);
	}

	public void addTile(char tile) {
		int frequency = 0;
		try {
			frequency = frequenciesIndexedByTile.get(tile);
		} catch (Exception e) {
			frequenciesIndexedByTile.put(tile, 0);
		}
		frequency++;
		frequenciesIndexedByTile.put(tile, frequency);
	}
	
	SortedMap<Frequency, Set<Character>> indexTilesByFrequency() {
		SortedMap<Frequency, Set<Character>> tilesIndexedByFrequency = new TreeMap<Frequency, Set<Character>>();
		for (Map.Entry<Character, Integer> pair : frequenciesIndexedByTile.entrySet()) {
			Character tile=pair.getKey();
			Integer frequency=pair.getValue();
			Set<Character> s=tilesIndexedByFrequency.get(new Frequency(frequency));
			if (s==null) {
				s=new TreeSet<Character>();
			    tilesIndexedByFrequency.put(new Frequency(frequency),s);
			} 
			s.add(tile);
		}
		return tilesIndexedByFrequency;
	}

	public void removeTile(char tile) {
		int frequency = frequenciesIndexedByTile.get(tile);
		frequenciesIndexedByTile.put(tile, frequency - 1);
	}

	public void removeTiles(String tiles) {
		for (char tile : tiles.toCharArray())
			removeTile(tile);
	}

	public String toString() {
		SortedMap<Frequency, Set<Character>> tilesIndexedByFrequency = indexTilesByFrequency();
		StringBuffer result = new StringBuffer();		
		for (Map.Entry<Frequency, Set<Character>> pair : tilesIndexedByFrequency.entrySet()) {
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
		return result.toString();
	}
}

class Frequency implements Comparable<Frequency>{
	
	Integer value;
	
	Frequency(int value) {
		this.value=value;
	}
	
	public int getValue() {
		return this.value;
	}	
	
	// Return opposite value
	public int compareTo(Frequency f) {
		return -value.compareTo(f.getValue());
	}
	
	public String toString() {
		return value.toString();
	}

}