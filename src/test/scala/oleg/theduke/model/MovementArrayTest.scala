package oleg.theduke.model

import org.scalatest.junit.JUnitSuite
import org.junit.Test

class MovementTypeTest extends JUnitSuite {

	@Test
	def testMovementTypeArrayBuilder_Init() {
		val mtab = new MovementArray(2,2)
		
		print(mtab.toString())
	}
}