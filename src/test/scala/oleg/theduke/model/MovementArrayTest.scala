package oleg.theduke.model

import org.scalatest.junit.JUnitSuite
import org.junit.Test

import MovementType._

class MovementTypeTest extends JUnitSuite {

	@Test
	def testMovementTypeArrayBuilder_Init() {
		val pikemanUpMa = new MovementArray(2,2)
		
		pikemanUpMa.addMoveByOffset(1, -1, Move)
		pikemanUpMa.addMoveByOffset(2, -2, Move)
		pikemanUpMa.addMoveByOffset(1, 1, Move)
		pikemanUpMa.addMoveByOffset(2, 2, Move)
		
		print(pikemanUpMa.toString())
	}
}