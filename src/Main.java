import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

	public static void main(String[] args){
		if(args.length != 1){
			System.err.println("Usage: sinkem (board-file-path)");
			System.exit(1);
		}

		Board board = new Board(args[0]);
		board.board.forEach( (k,v) -> System.out.println(k + "="+ v));
		RandomWalk walker = new RandomWalk();
		Random gen = new Random();

		// Move it!
		for(Map.Entry<String, String> entry : board.board.entrySet()){
			HashMap<String, String> history = board.shuffledBoardHistory;

			// place the start value
			String prevValue = history.get(entry.getKey());
			history.put(entry.getKey(), prevValue == null ? entry.getValue() : prevValue + "," + entry.getValue());

			// Setup for moving around
			String tile = entry.getValue().split(":")[1];
			String prevCoord = entry.getKey();

			int index = 1;
			int iterations = 3;
			while(iterations-- > 0) {
				while (walker.hasNext()) {
					String step = walker.getCoordPair();
					String stamp = index + ":" + tile + ":" + prevCoord;
					prevValue = history.get(step);
					history.put(step, prevValue == null ? stamp : prevValue + "," + stamp);
					prevCoord = step;
					index++;
				}
				walker.resetBag();
			}
		}

		board.shuffledBoardHistory.forEach((k,v) -> System.out.println(k + "=" + v));
	}
}
