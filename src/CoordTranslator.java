public class CoordTranslator {

	public static String translateCoordinates(int x, int y){
		return (char)('A' + x) + "" + (y + 1);
	}
}
