import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.unige.aims.GridWorld;
import org.unige.aims.GridWorld.Coordinate;

/**
 * 
 * @author alessandromaggi
 *
 *La classe Pathfinder mette a disposizione i metodi per permettere al robot di scegliere la migliore 
 *cella nella quale muoversi (con un riferimento all' A*) e tornare indietro in caso non siano piu disponibili
 *celle libere.
 */
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
	
	/**
	 * metodo per ottenere le celle libere adiacenti ad una cella nella quale si trova il robot
	 * @return ArrayList di celle libere adiacenti
	 */
	public ArrayList<Cell> adjacentFreeCell() {
		ArrayList<Cell> adjacentFreeCell = new ArrayList<Cell>(); //ArrayList per contenere le celle libere adiacenti
		Iterable<Coordinate> adjacentFreeCoordinate; //iterable che contiene le celle adiacenti e libere
		adjacentFreeCoordinate = gw.getAdjacentFreeCells();
		//metto le coordinate adiacenti e libere in un Iterable
		for (Coordinate c: adjacentFreeCoordinate) {
			Cell c1 = new Cell(c, currentCell);
			calculateHcost(c1);
			adjacentFreeCell.add(c1);
		}
		return adjacentFreeCell;
	}
	
	/**
	 * metodo per calcolare il costo H (in riferimento all' algoritmo A*) stimato con il metodo Manhattan
	 * @param cell
	 */
	public void calculateHcost(Cell cell) {
		int hX = gridDimension - cell.getRow() - 1; //-1 perche la cella target è in girdDimension - 1;
		int hY = gridDimension - cell.getCol() - 1;
		cell.setHCost(hX + hY);
	}
	
	/**
	 * @param adjacentFreeCell
	 * @return la cella con il minor costo F
	 */
	public Cell findMinValue(ArrayList<Cell> adjacentFreeCell) {
		Cell minCell = adjacentFreeCell.get(0);
		int min = adjacentFreeCell.get(0).getHCost();
		
		for (Cell c: adjacentFreeCell) {
			if (c.getHCost() < min) {
				min = c.getHCost();
				minCell = c;
			}
		}
		return minCell;
	}
	
	/**
	 * metodo per rimuovere le celle libere adiacenti che sono presenti nella closed list
	 * 
	 * @param cl <-- closedList (celle visitate)
	 * @param fac <-- FreeAdjacentCell
	 */
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
	
	/**
	 * metodo per far tornare indietro il robot di un passo quando esso non ha piu celle 
	 * libere adiacenti (anche che non appartengano alla closedList)
	 */
	private void getBack() {
		Path p = new Path();
		path.remove(currentCell);
		gw.moveToAdjacentCell(p.moveCell(currentCell, currentCell.getPreviousCell()));
		currentCell = currentCell.getPreviousCell();
	}
	
	/**
	 * 
	 * @param c <-- cella corrente
	 * @return true in caso la cella c si possa aggiugere alla closedList
	 */
	private boolean canAddToClosedList(Cell c) {
		for (Cell c1: closedList) {  	
			if(c.getRow() == c1.getRow() && c.getCol() == c1.getCol()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * metodo per far muovere il robot nella cella migliore secondo quanto detto e aggiungere la cella visitata in path 
	 */
	public void bestMove() {
		Path p = new Path();
		path.add(currentCell);
		ArrayList<Cell> tmp = adjacentFreeCell();
		removeCells(closedList, tmp);
		//controllo sull' impossibilità di trovare un percorso
		if ((currentCell.getRow() == 0 && currentCell.getCol() == 0) && tmp.size() == 0) {
			System.out.println("Nessun percorso!");
			System.exit(0);
		}
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
	
	/**
	 * metodo di stampa del percorso finale del robot
	 * 
	 */
	public void printpath() {
		for (Cell c : path) {
			System.out.print("(" + c.getRow() + ", " + c.getCol() + ") ");
		}
	}	
}
