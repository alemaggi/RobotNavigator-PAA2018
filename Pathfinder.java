import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.unige.aims.GridWorld;
import org.unige.aims.GridWorld.Coordinate;

public class Pathfinder {
	
	private GridWorld gw; //oggetto di tipo gridWorld
	private int gridDimension; //dimensione della griglia di gridWorld
	private Cell currentCell; //cella di origine

	private HashSet<Cell> closedList = new HashSet<Cell>(); //Collezione di celle contenete le celle visitate
	private LinkedHashSet<Cell> path = new LinkedHashSet<Cell>(); //Collezione di celle contenente il percorso finale del robot
	
	//parametrized constructor
	public Pathfinder(GridWorld gw, int gridDimension, Cell currentCell) {
		this.gw = gw;
		this.gridDimension = gridDimension;
		this.currentCell = currentCell;
		closedList.add(currentCell);
	}
	
	//metodo per ottenere le celle libere adiacenti ad una cella 
	public ArrayList<Cell> adjacentFreeCell() {
		ArrayList<Cell> adjacentFreeCell = new ArrayList<Cell>(); //ArrayList per contenere le celle libere adiacenti
		Iterable<Coordinate> adjacentFreeCoordinate; //iterable che contiene le celle adiacenti e libere
		adjacentFreeCoordinate = gw.getAdjacentFreeCells();
		//metto le coordinate adiacenti e libere in un Iterable
		for (Coordinate c: adjacentFreeCoordinate) {
			Cell c1 = new Cell(c, currentCell);
			calculateHcost(c1);
			calculateFcost(c1);
			adjacentFreeCell.add(c1);
		}
		return adjacentFreeCell;
	}
	
	//metodo per calcolare il costo H (in riferimento all' algoritmo A*) stimato con il metodo Manhattan
	public void calculateHcost(Cell cell) {
		int hX = gridDimension - cell.getRow() - 1; //-1 perche la cella target è in girdDimension - 1;
		int hY = gridDimension - cell.getCol() - 1;
		cell.setHCost(hX + hY);
	}
	
	//metodo per calcoare il costo F (in riferimento all' algoritmo A*) ottenuto come somma del costo H e del costo G
	public void calculateFcost(Cell cell) {	
		calculateHcost(cell);
		cell.setFCost(cell.getGCost() + cell.getHCost());
	}
	
	//metodo per trovare la cella con il minor costo F tra le celle libere adiacenti 
	public Cell findMinValue(ArrayList<Cell> adjacentFreeCell) {
		Cell minCell = adjacentFreeCell.get(0);
		int min = adjacentFreeCell.get(0).getFCost();
		
		for (Cell c: adjacentFreeCell) {
			if (c.getFCost() < min) {
				min = c.getFCost();
				minCell = c;
			}
		}
		return minCell;
	}
	
	//metodo per rimuovere le celle libere adiacenti che sono presenti nella closed list
	private void removeCells(HashSet<Cell> cl, ArrayList<Cell> fac) {
		ArrayList<Cell> markedForDel = new ArrayList<Cell>(); //ArrayList contenete le celle che andranno cancellate
		for (Cell oc : fac) {
			for (Cell cc : cl) {
				if (cc.getRow() == oc.getRow() && cc.getCol() == oc.getCol()) {
					markedForDel.add(oc);
					break;
				}
			}
		}
	    fac.removeAll(markedForDel);
	}
	
	//metodo per far tornare indietro il robot di un passo quando esso non ha piu celle libere adiacenti (anche che non appartengano alla closedList)
	private void getBack() {
		Path p = new Path();
		path.remove(currentCell);
		gw.moveToAdjacentCell(p.moveCell(currentCell, currentCell.getPreviousCell()));
		currentCell = currentCell.getPreviousCell();
		if(canAddToClosedList(currentCell)) {
			closedList.add(currentCell);
		}
	}
	
	//metodo per controllare che una cella non sia gia contenuta nella closedList e quindi che possa essere aggiunta in essa
	private boolean canAddToClosedList(Cell c) {
		for (Cell c1: closedList) {  	
			if(c.getRow() == c1.getRow() && c.getCol() == c1.getCol()) {
				return false;
			}
		}
		return true;
	}
	
	//metodo per far muovere il robot nella cella migliore secondo quanto detto e aggiungere la cella visitata in path
	public void bestMove() {
		Path p = new Path();
		path.add(currentCell);
		ArrayList<Cell> tmp = adjacentFreeCell();
		removeCells(closedList, tmp);
		if (tmp.size() != 0) {
			gw.moveToAdjacentCell(p.moveCell(currentCell,findMinValue(tmp)));
			currentCell = findMinValue(tmp);
			if(canAddToClosedList(currentCell))
			closedList.add(currentCell);
		}
		else {
			getBack();
		}
		
	}
	
	//metodo di stampa del percorso finale del robot
	public void printpath() throws InterruptedException {
		System.out.println(path.size());
		for (Cell c : path) {
			System.out.println("(" + c.getRow() + "," + c.getCol() + ")");

		}
	}	
}
