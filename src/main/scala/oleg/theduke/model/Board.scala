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
	
	//TODO TEST
	def moveTile(tile: Tile, from:Coordinates, to: Coordinates) = {
		val targetTileOpt = allTilesMatrix(to.x)(to.y)
		
		if (targetTileOpt.isDefined) {
			//CAPTURING? 
			
			val targetTile = targetTileOpt.get
			//If there is already a tile at the 'to' coord - check if
			//it can be captured, if yes - capture/remove from board.
			validateCanCaptureTile(tile, targetTile)
			validateCanPlaceTile(tile, to, true)
			
			//capture tile only after making sure we can move our tile there	
			processCapturedTile(targetTile, to)
		} else {
			//NOT CAPTURING
			validateCanPlaceTile(tile, to, false)
		}
		
		//update tile matrix
		updateAllTilesMatrixCell(Some(tile), to)
		updateAllTilesMatrixCell(None, from)
	}
	
	/**
	 * Verifies whether attacking tile can capture target tile
	 * 
	 * @throws(classOf[IllegalMoveException]) if attacker cannot capture target
	 */
	private def validateCanCaptureTile(attacker: Tile, target:Tile) = {
		
	}
	
	/**
	 * Processes just captured tile. Moves to captured collections,
	 * removes tile from board and from "alive" collections.
	 * 
	 * @throws(classOf[IllegalMoveException])
	 */
	private def processCapturedTile(tile:Tile, tileCoord: Coordinates) = {
		
		if (tile.isInstanceOf[TerrainTile] && !gameRules.canCaptureTerrainTile) {
			throw new IllegalMoveException(tile, tileCoord, "Game rules do not allow terrain tile capture!")
		}
		
		allTilesMatrix(tileCoord.x)(tileCoord.y) = None
		
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
	def placeNewTileOnBoard(tile: Tile, coord: Coordinates) = {
		//check if there is already a tile
		validateCanPlaceTile(tile, coord, false)
		
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
	private def validateCanPlaceTile(tile: Tile, coord: Coordinates, ignoreCapturedTile:Boolean) = {
		val bTileOpt = allTilesMatrix(coord.x)(coord.y)
		if (! bTileOpt.isDefined
			 || (bTileOpt.get.isInstanceOf[Inhabitable]
					&& !bTileOpt.get.asInstanceOf[Inhabitable].hasTileInside)
			 || (ignoreCapturedTile && bTileOpt.get.player != tile.player)
			) {
		} else {
			throw new IllegalMoveException(tile, coord)
		}
	}
	
	private def updateAllTilesMatrixCell(tileOpt: Option[Tile], coord: Coordinates) = {
		allTilesMatrix(coord.x)(coord.y) = tileOpt
	}

}