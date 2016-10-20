public class Main {

	public static void main(String[] args){
		if(args.length != 1){
			System.err.println("Usage: sinkem (board-file-path)");
			System.exit(1);
		}

		Board board = new Board(args[0]);
		board.board.forEach( (k,v) -> System.out.println(k + "="+ v));
	}
}
