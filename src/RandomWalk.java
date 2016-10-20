import java.util.*;

public class RandomWalk {

	private List<String> coordBag;
	private List<String> originalCoordBag;

	public RandomWalk(){
		generateOriginalCoordBag();
		coordBag = new ArrayList<>(originalCoordBag.size());
		resetBag();
	}

	private void generateOriginalCoordBag() {
		originalCoordBag = new ArrayList<>();
		for(int i = 0; i < 2; i++){
			for(int k = 0; k < 2; k++){
				originalCoordBag.add(CoordTranslator.translateCoordinates(i, k));
			}
		}
	}

	public String getCoordPair(){
		return coordBag.remove(0);
	}

	public boolean hasNext(){
		return !coordBag.isEmpty();
	}

	public void resetBag(){
		coordBag.addAll(originalCoordBag);
		Collections.shuffle(coordBag);
	}
}
