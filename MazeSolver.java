import java.util.ArrayList;
/**Name: Anish Rajeshkumar
ID: A16670774
Email: arajeshkumar@ucsd.edu
Sources Used: tutors
File header: The purpose of this file is to implement all
the necessary methods needed to create a functioning mazesolver
*/

/**Class header: this class
 * implements a metgod which will find the 
 * least costliest path from the start square
 * to the finish square in the maze
* 

*/
public abstract class MazeSolver {
	   /**
		 * A method 
		 * which essentially goes through a maze
		 * in anorsth south west east direction
		 * in order to find least costliest path to the 
		 * finish
		 * 
		 * @param maze and priorityque containing maxor min heap 
		 * @return finish square
		 */
	public static Square solve(Maze maze, PriorityQueue<Integer,Square> pq) {
		pq.add(maze.start.getCost(), maze.start);
		while(!(pq.isEmpty())) {
			Entry<Integer,Square> current = pq.poll();
			Square currentSquare = current.value;
			currentSquare.visit();
			if(currentSquare.equals(maze.finish)) {
				return currentSquare;
			}
			else {
				for(int i = 0; i < 4; i++) {
					if(i == 0) {
						if(currentSquare.getRow() - 1 < 0) {
							continue;
						}
						int north = currentSquare.getRow() - 1;
						if(maze.contents[north][currentSquare.getCol()].isVisited() || 
								maze.contents[north][currentSquare.getCol()].getIsWall()) {
							continue;
						}
						Square neighbor = maze.contents[north][currentSquare.getCol()];
						int currentCost = neighbor.getCost() + current.key;
						if(currentCost < neighbor.getRunningCost()) {
							neighbor.setPrevious(currentSquare);
							neighbor.setRunningCost(currentCost);
							pq.add(currentCost, neighbor);
						}
					}
					if(i == 1) {
						if(currentSquare.getRow() + 1 >= maze.rows) {
							continue;
						}
						int south = currentSquare.getRow() + 1;
						if(maze.contents[south][currentSquare.getCol()].isVisited() || 
								maze.contents[south][currentSquare.getCol()].getIsWall()) {
							continue;
						}
						Square neighbor = maze.contents[south][currentSquare.getCol()];
						int currentCost = neighbor.getCost() + current.key;
						if(currentCost < neighbor.getRunningCost()) {
							neighbor.setPrevious(currentSquare);
							neighbor.setRunningCost(currentCost);
							pq.add(currentCost, neighbor);
						}
					}
					if(i == 2) {
						if(currentSquare.getCol() - 1 < 0) {
							continue;
						}
						int west = currentSquare.getCol() - 1;
						if(maze.contents[currentSquare.getRow()][west].isVisited() || 
								maze.contents[currentSquare.getRow()][west].getIsWall()) {
							continue;
						}
						Square neighbor = maze.contents[currentSquare.getRow()][west];
						int currentCost = neighbor.getCost() + current.key;
						if(currentCost < neighbor.getRunningCost()) {
							neighbor.setPrevious(currentSquare);
							neighbor.setRunningCost(currentCost);
							pq.add(currentCost, neighbor);
						}
					}
					if(i == 3) {
						if(currentSquare.getCol() + 1 >= maze.cols) {
							continue;
						}
						int east = currentSquare.getCol() + 1;
						if(maze.contents[currentSquare.getRow()][east].isVisited() || 
								maze.contents[currentSquare.getRow()][east].getIsWall()) {
							continue;
						}
						Square neighbor = maze.contents[currentSquare.getRow()][east];
						int currentCost = neighbor.getCost() + current.key;
						if(currentCost < neighbor.getRunningCost()) {
							neighbor.setPrevious(currentSquare);
							neighbor.setRunningCost(currentCost);
							pq.add(currentCost, neighbor);
						}
					}
				}
			}
			
		}
		return null;
	}
}
	
