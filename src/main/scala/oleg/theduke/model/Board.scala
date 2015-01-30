package oleg.theduke.model

import scala.collection.mutable.Set
import oleg.theduke.model.Player._
import oleg.theduke.IllegalMoveException
import oleg.theduke.GameRules

/**
 * @author oleg
 */
class Board(val size: Int, val gameRules: GameRules) {


	private val allTilesMatrix = Array.ofDim[Option[Tile]](size, size)

	private val lightTiles = Set[Tile]()
	private val lightCapturedTiles = Set[Tile]()
	private val darkTiles = Set[Tile]()
	private val darkCapturedTiles = Set[Tile]()
	private val terrainTiles = Set[Tile]()

	def moveTile(tile: Tile, from:Coordinates, to: Coordinates) = {
		//TODO implement......
		//TODO implement......
		//TODO implement......
		//TODO implement......
	}
	
	/**
	 * Processes just captured tile.
	 * NOTICE: this method DOES NOT update allTilesMatrix
	 */
	def processCapturedTile(tile:Tile, tileCoord: Coordinates) = {
		
		if (tile.isInstanceOf[TerrainTile] && !gameRules.canCaptureTerrainTile) {
			throw new IllegalMoveException(tile, tileCoord, "Game rules do not allow terrain tile capture!")
		}
		
		tile.player match {
			case Light => { 
				lightTiles -= tile
				lightCapturedTiles += tile
			}
			case Dark => {
				darkTiles -= tile
				darkCapturedTiles += tile	
			}
			case _ => throw new IllegalStateException("Unsupported player type for tile capture " + tile.player)
		}
	}
	
	/**
	 * Places new tile on the board.
	 */
	def placeTile(tile: Tile, coord: Coordinates) = {
		//check if there is already a tile
		validateCanPlaceTile(tile, coord)
		
		allTilesMatrix(coord.x)(coord.y) = Some(tile)
		
		tile.player match {
			case Light => lightTiles += tile
			case Dark => darkTiles += tile
			case Neutral => terrainTiles += tile
			case _ => throw new IllegalStateException("Unsupported player type for tile placement " + tile.player)
		}
	}
	
	/**
	 * Validates whether tile can be placed here.
	 * @throws(classOf[IllegalMoveException])
	 */
	private def validateCanPlaceTile(tile: Tile, coord: Coordinates) = {
		val bTile = allTilesMatrix(coord.x)(coord.y)
		if (bTile == None ||
			(bTile.get.isInstanceOf[Inhabitable]
				&& !bTile.get.asInstanceOf[Inhabitable].hasTileInside)) {
		} else {
			throw new IllegalMoveException(tile, coord)
		}
	}

}