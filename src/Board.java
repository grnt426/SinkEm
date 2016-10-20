import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Board {

	// Ship Types
	public static final String EMPTY = "e";
	public static final String PATROL = "p";

	// Control Words
	public static final String START = "ST";

	public final HashMap<String, String> board;

	public Board(String filename){
		board = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = null;
			int row = 0;
			while ((line = reader.readLine()) != null) {
				String[] tiles = line.split("");
				int index = 0;
				for(String tile : tiles){
					String coord = CoordTranslator.translateCoordinates(row, index++);
					board.put(coord, generateStamp(tile));
				}
				row++;
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

	private String generateStamp(String tile){
		return generateStamp(tile, 0);
	}

	private String generateStamp(String tile, int index){
		switch(tile){
			case EMPTY: return index + ":" + EMPTY + ":" + START;
			case PATROL: return index + ":" + PATROL + ":" + START;
			default: return "ER:ER:ER";
		}
	}
}
