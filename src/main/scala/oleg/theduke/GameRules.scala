package oleg.theduke

import oleg.theduke.model.TileSet
import oleg.theduke.model.Tile
import oleg.theduke.model.StandardTilesTileSet
import oleg.theduke.model.PlayerSide._


/**
 * Base class for game rules. Sub-classes will provide customizable game rules,
 * this class provides most of the default for the "Classic" (i.e. by the book) 
 * The Duke game rules as specific in the original games rule book, or expansion sets
 * rule book, where applicable.
 */
abstract class GameRules {
	
	def canCaptureTerrainTile = false
	def isExpandedPlay = false
	
	def getPlayerTiles(player:PlayerSide)  : Set[Tile]

}

object GameRules {
	
	class StandardRules extends GameRules with StandardTilesTileSet
	//TODO uncomment and implement with an appropriate tileset...
	//class ExpandedPlayStandardRules extends GameRules { }
	
	def getGameRules: GameRules = new StandardRules
}