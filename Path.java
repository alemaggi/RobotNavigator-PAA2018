import org.unige.aims.GridWorld;
import org.unige.aims.GridWorld.Direction;

/**
 * 
 * @author alessandromaggi
 *
 * La classe Path mette a disposizione un metodo per convertire uno spostamento da una cella all' altra in uno spostamento ammesso
 * da GridWorld.coordinate
 */
public class Path {

	private Cell currentCell;
	private Cell targetCell;
	
	public Path() {}
	
	/**
	 * 
	 * @param currentCell Cella corrente nella quale si trova il robot 
	 * @param targetCell cell nella quale il robot deve muoversi
	 * @return un oggetto di tipo Direction 
	 */
	public Direction moveCell(Cell currentCell, Cell targetCell) {
		//NORTH
		if ((currentCell.getRow() - 1 == targetCell.getRow()) && (currentCell.getCol() == targetCell.getCol())) {
			return Direction.NORTH;
		}
		//EST
		if ((currentCell.getRow() == targetCell.getRow()) && (currentCell.getCol() + 1 == targetCell.getCol())) {
			return Direction.EAST;
		}
		//SOUTH
		if ((currentCell.getRow() + 1 == targetCell.getRow()) && (currentCell.getCol() == targetCell.getCol())) {
			return Direction.SOUTH;
		}
		//WEST
		if ((currentCell.getRow() == targetCell.getRow()) && (currentCell.getCol() - 1 == targetCell.getCol())) {
			return Direction.WEST;
		}
		else return null;
	}
}
