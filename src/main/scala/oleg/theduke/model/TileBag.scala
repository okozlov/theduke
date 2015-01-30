package oleg.theduke.model.tiles

import scala.collection.mutable.Set
import oleg.theduke.model.TileNotInBagException
import oleg.theduke.model.Tile

/**
 * @author oleg
 */
case class TileBag(val tiles: Set[Tile]) {

	private val random = new scala.util.Random

	def isEmpty: Boolean = tiles.isEmpty

	def addTile(tile: Tile) = {
		tiles += tile
	}

	/**
	 * For automated play
	 */
	def drawATile(): Option[Tile] = {
		if (tiles.isEmpty) {
			None
		} else {
			val selTile = selectRandomTile
			Some(removeTileFromBag(selTile))
		}
	}

	/**
	 * For manual play
	 */
	def drawATile(tileName: String): Tile = {
		val selTile = tiles.find { x => x.name.equalsIgnoreCase(tileName) }
		//remove from tiles
		if (selTile == None) throw new TileNotInBagException(tileName)
		else removeTileFromBag(selTile.get)
	}
	
	private def removeTileFromBag(tile: Tile): Tile = {
		//remove from tiles
		tiles -= tile
		tile
	}

	private def selectRandomTile = tiles.toVector(random.nextInt(tiles.size))

}