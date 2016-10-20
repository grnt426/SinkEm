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
	public final HashMap<String, String> shuffledBoardHistory;

	public Board(String filename){
		board = new HashMap<>();
		shuffledBoardHistory = new HashMap<>();
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

	public String generateStamp(String tile){
		return generateStamp(tile, 0);
	}

	public String generateStamp(String tile, int index){
		return index + ":" + tile + ":" + START;
	}
}
