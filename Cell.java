import org.unige.aims.GridWorld;
import org.unige.aims.GridWorld.Coordinate;

public class Cell {

	private int row, col;
	private int gCost, hCost, fCost;
	private Cell previousCell;

	//constructor per la prima cella
	public Cell(Coordinate other) {
		this.row = other.row;
		this.col = other.col;
		this.gCost = 0;
	}
	
	//Constructor per le altre celle
	//constructor
	public Cell(Coordinate other, Cell previousCell) {
		this.previousCell = previousCell;
		this.row = other.row;
		this.col = other.col;
		this.gCost = previousCell.gCost + 1;
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
	//setter and getter for gCost, hCost, fCost
	public int getGCost() {
		return gCost;
	}
	public void setGCost(int gCost) {
		this.gCost = gCost;
	}
	public int getHCost() {
		return hCost;
	}
	public void setHCost(int hCost) {
		this.hCost = hCost;
	}
	public int getFCost() {
		return fCost;
	}
	public void setFCost(int fCost) {
		this.fCost = fCost;
	}
	public Cell getPreviousCell() {
		return previousCell;
	}
}
