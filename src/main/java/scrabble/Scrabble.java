public class Scrabble {

  Map<Integer,Set<Char>> tilesIndexedByFrequency=new HashMap<Integer,Set<Char>>
  Map<Char,Integer> frequenciesIndexedByTile=new HashMap<Char,Integer>

  Scrabble (String tiles) {
    for (char tile:tiles) addTile(tile);
  }

  public void addTile(char tile) {
    int frequency=0;
    try {
	frequency=frequenciesIndexedByTile.get(tile)
    } catch (Exception e) {
        frequenciesIndexedByTile.put(tile,0)
    }
    frequenciesIndexedByTile.put(char,frequency++)
    try {
	Set s=tilesIndexedByFrequency.get(frequency);
    } catch (Exception e) {
        tilesIndexedByFrequency.put(frequency,new TreeSet<Char>())
    }
    s.add(tile);
  }

  public void removeTile(char tile) {
    int frequency=frequenciesIndexedByTile(tile);
    frequenciesIndexedByTile.put(tile,frequency-1);
    tilesIndexedByFrequency.remove(frequency,tile);
    tilesIndexedByFrequency.add(frequency-1,tile);
  }

  public void toString();
}

