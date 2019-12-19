import java.util.ArrayList;

import org.unige.aims.GridWorld;
import org.unige.aims.GridWorld.Coordinate;

/**
 * 
 * @author alessandromaggi
 *
 * Main per robotNavigator, applicazione che permette al robot di trovare l'uscita 
 * all' interno di un labirinto della libreria GridWorld (Gridworld.jar)
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		if (args.length != 3) {
			System.out.println("Attenzione parametri invalidi");
		}
		else {
			GridWorld Gw = new GridWorld(Integer.parseInt(args[0]), Double.parseDouble(args[1]), Long.parseLong(args[2]));
		
			int gridDimension = Integer.parseInt(args[0]);
			
			Cell CellaTest = new Cell(Gw.getCurrentCell());
			CellaTest.setHCost((gridDimension * 2) - 2);
			
			Pathfinder pf = new Pathfinder(Gw, Integer.parseInt(args[0]), CellaTest);
			
			while(!Gw.targetReached()) {
				pf.bestMove();
				}
			pf.finalMove();
			pf.printpath();
		}
	}
}
