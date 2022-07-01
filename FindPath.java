
/**
 * This class helps to compute a path from the entrance of the pyramid to all the treasure chambers
 * @author Kevin Chau 251215166
 */
import java.io.FileNotFoundException;
import java.io.IOException;

public class FindPath {

	Map pyramidMap;

	/**
	 * Constructor method
	 * 
	 * @param fileName
	 * @throws InvalidMapCharacterException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public FindPath(String fileName) throws InvalidMapCharacterException, FileNotFoundException, IOException {
		pyramidMap = new Map(fileName); // create an object of class Map

	}

	/**
	 * This method finds a path from the entrance to all the treasure chambers
	 * 
	 * @return stack
	 */
	public DLStack path() {
		DLStack<Chamber> stack = new DLStack<Chamber>(); // create an empty stack
		Chamber c; // new Chamber c
		int treasureChambers = 0;
		stack.push(pyramidMap.getEntrance()); // push starting chamber into the stack
		pyramidMap.getEntrance().markPushed(); // mark the chamber as pushed

		while (!stack.isEmpty()) { // statement to check is stack is empty
			c = bestChamber(stack.getTop().getElement());

			if (stack.peek().isTreasure()) { // peek at top and determine if current chamber is a treasure
				treasureChambers++; // everytime
				if (pyramidMap.getNumTreasures() == treasureChambers) { // check to see if number of treasure chambers
																		// has already been found
					break; // exit the loop
				}

			}

			if (c != null) { // statement to check if chamber is not null
				stack.push(c); // push c into stack
				c.markPushed(); // mark as pushed
			} else if (c == null) { // statement to check if c is null
				stack.getTop().getElement().markPopped(); // get the top and mark it as popped
				stack.pop(); // pop the top chamber
			}

		}
		return stack; // return the stack

	}

	/**
	 * This method returns the value of pyramidMap
	 * 
	 * @return pyramidMap
	 */
	public Map getMap() {
		return pyramidMap; // return pyramidMap
	}

	/**
	 * This method determines if current chamber is dim or not
	 * 
	 * @param Chamber currentChamber
	 * @return true if conditions satisfies to be dim
	 * @return false if conditions do not satisfy to be dim
	 */
	public boolean isDim(Chamber currentChamber) {

		/*
		 * if statement checks if currentChamber is not null, not sealed,, is not
		 * lighted, and has one of its neighboring chambers lighted
		 * 
		 */
		if (currentChamber != null && currentChamber.isSealed() == false && currentChamber.isLighted() == false) {

			for (int i = 0; i <= 5; i++) { // loops through while i is less than or equal to 5 inclusive for each side
											// of the chamber
				if (currentChamber.getNeighbour(i) == null) { // statement to check if neighboring chamber is null
				} else if (currentChamber.getNeighbour(i).isLighted()) { // else if to check if neighboring chamber is
																			// lighted
					return true;
				}
			}

		} else {

			return false;
		}
		return false;
	}

	/**
	 * This method selects the best chamber to move to from currentChamber according
	 * to specific restrictions
	 * 
	 * @param currentChamber
	 * @return currentChamber.getNeighbour(i) if chamber is not marked and has treasure
	 * @return currentChamber.getNeighbour(i) if chamber is lighted and not marked
	 * @return currentChamber.getNeighbour(i) if chamber is dim and not marked
	 * @return null otherwise
	 */
	public Chamber bestChamber(Chamber currentChamber) {

		for (int i = 0; i <= 5; i++) { 
			if (currentChamber.getNeighbour(i) == null) {  //statement to check if any of the neighbouring sides is null
				continue; //continue onto next block of code
			} else if (!currentChamber.getNeighbour(i).isMarked() && currentChamber.getNeighbour(i).isTreasure()) { //statement to check if neighbour is not marked and has treasure
				return currentChamber.getNeighbour(i); //return the smallest index
			}

		}

		for (int i = 0; i <= 5; i++) {
			if (currentChamber.getNeighbour(i) == null) {
				continue;
			} else if (currentChamber.getNeighbour(i).isLighted() && !currentChamber.getNeighbour(i).isMarked()) { //statement to check if neighbour is lighted and not marked
				return currentChamber.getNeighbour(i);
			}
		}
		for (int i = 0; i <= 5; i++) {
			if (currentChamber.getNeighbour(i) == null) {
				continue;
			} else if (isDim(currentChamber.getNeighbour(i)) && !currentChamber.getNeighbour(i).isMarked()) { //statement to check if neighbour is dim and not marked
				return currentChamber.getNeighbour(i);
			}
		}
		return null; //otherwise return null

	}

}
