import org.unige.aims.GridWorld;
import org.unige.aims.GridWorld.Coordinate;
/**
 * 
 * @author alessandromaggi
 *
 * La classe Cell mette a disposizione gli attributi di ogni singola cella
 *
 */
public class Cell {

	private int row, col;
	private int hCost;
	private Cell previousCell;

	//constructor per la prima cella
	public Cell(Coordinate other) {
		this.row = other.row;
		this.col = other.col;
	}
	
	//Constructor per le altre celle
	//constructor
	public Cell(Coordinate other, Cell previousCell) {
		this.previousCell = previousCell;
		this.row = other.row;
		this.col = other.col;
	}
	
	//setter and getter for x
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	//setter and getter for y
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	//setter and getter for hCost
	public int getHCost() {
		return hCost;
	}
	public void setHCost(int hCost) {
		this.hCost = hCost;
	}
	public Cell getPreviousCell() {
		return previousCell;
	}
}
