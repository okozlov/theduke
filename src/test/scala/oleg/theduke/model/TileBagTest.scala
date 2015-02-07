package oleg.theduke.model

import scala.collection.mutable.Set

import org.junit.Test
import org.scalatest.junit.JUnitSuite

import oleg.theduke.model.PlayerSide.Light

class TileBagTest extends JUnitSuite {

	@Test
	def testDrawATileFromEmptyBag() {
		val tileBag = new TileBag(Set())

		val tile = tileBag.drawATile()
		assert(tile == None)
	}

	@Test
	def testDrawATileFromBag() {
		val tileBag = new TileBag(Set(new Duke(true, true, Light), new Duchess(Light), new Pikeman(Light), new Pikeman(Light)))
		for (i <- 1 to 3) {
			println("GOT: " + tileBag.drawATile().get)
			println("TILEBAG: " + tileBag)
			assert(tileBag.tiles.size == 4-i)
		}
	}
}