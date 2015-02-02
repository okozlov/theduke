package oleg.theduke.model

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import oleg.theduke.GameRules
import oleg.theduke.model.Player._

class BoardTest  extends JUnitSuite {

	/**
	 * Tests a sample initial setup on 6x6 board 
	 */
	@Test
	def testPlaceNewTileOnBoard() {
		val board = new Board(6, GameRules.getGameRules)
		
		board.placeNewTileOnBoard(new Duke(true, Light), new Coordinates(0,2))
		board.placeNewTileOnBoard(new Pikeman(Light), new Coordinates(0,3))
		board.placeNewTileOnBoard(new Pikeman(Light), new Coordinates(1,2))
		
		board.placeNewTileOnBoard(new Duke(true, Dark), new Coordinates(5,3))
		board.placeNewTileOnBoard(new Pikeman(Dark), new Coordinates(5,2))
		board.placeNewTileOnBoard(new Pikeman(Dark), new Coordinates(4,3))
		
		board.printMatrix()
		
		//TODO asserts...
		//TODO asserts...
		//TODO asserts...
		//TODO asserts...
		//TODO asserts...
	}
}