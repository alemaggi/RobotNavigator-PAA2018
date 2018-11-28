import java.util.ArrayList;

import org.unige.aims.GridWorld;
import org.unige.aims.GridWorld.Coordinate;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		if (args.length != 3) {
			System.out.println("Attenzione parametri invalidi");
		}
		else {
			GridWorld Gw = new GridWorld(Integer.parseInt(args[0]), Double.parseDouble(args[1]), Long.parseLong(args[2]));
			
			int gridDimension = Integer.parseInt(args[0]);
			Gw.print();
			//System.out.println(Gw.getCurrentCell());
			Cell CellaTest = new Cell(Gw.getCurrentCell());
			CellaTest.setFCost((gridDimension * 2) - 2);
			Pathfinder pf = new Pathfinder(Gw, Integer.parseInt(args[0]), CellaTest);
			
			while(!Gw.targetReached()) {
				pf.bestMove();
				}
			pf.printpath();
			System.out.println("(" + (gridDimension- 1 ) + "," + (gridDimension - 1) + ")"); //(Version 0.1)
		}
	}
}
