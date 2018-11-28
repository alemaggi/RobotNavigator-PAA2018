import org.unige.aims.GridWorld;
import org.unige.aims.GridWorld.Direction;

public class Path {

	private Cell currentCell;
	private Cell targetCell;
	
	public Path() {}
	
	//method to convert cell movements in GridWorld.Coordinate movements 
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
