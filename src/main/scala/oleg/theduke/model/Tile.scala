package oleg.theduke.model

import oleg.theduke.model.PlayerSide._


/**
 * @author oleg
 *
 * @param mustBeCaptured true if this tile must be captured by opponent in order to win
 */
abstract class Tile(val name: String, val abbr: String, val player: PlayerSide, var isDuke: Boolean, val mustBeCaptured: Boolean) {

	//true = staring side, false = non-starting side of the tile, a.k.a (up/down)
	var side: Boolean = true
	

	override def toString = name + (if (isDuke) "*" else "") + (if (player==Dark) "-D" else if (player==Light) "-L")
	
	//Special game features
	def isImmuneToDread = false
	
	//Expanded Play (EP) actions
	def canSummon : Boolean = false
	def canUseDivination : Boolean = false
	def canEscape : Boolean = false
	def canRansom : Boolean = false
	def canRedeploy : Boolean = false
}

trait Inhabitable {
	var tileInside : Option[SoldierTile] = None
	def hasTileInside = tileInside != None
}


abstract class SoldierTile(name: String, abbr: String, player:PlayerSide, isDuke: Boolean, mustBeCaptured: Boolean) extends Tile(name, abbr, player, isDuke, mustBeCaptured)
abstract class TerrainTile(name: String, abbr: String) extends Tile(name, abbr, Neutral, false, false)



/**************************
 * STANDARD SET TILES
 **************************/
//Duke tile may not be be acting as The Duke when playing some extensions
class Duke(isDuke: Boolean, player: PlayerSide) extends SoldierTile("Duke", "Du", player, isDuke, isDuke) 
class Duchess(player: PlayerSide) extends SoldierTile("Duchess", "Dc", player, false, false) { override def canSummon = true  }
class Assassin(player: PlayerSide) extends SoldierTile("Assassin", "As", player, false, false)
class General(player: PlayerSide) extends SoldierTile("General", "Ge", player, false, false)
class Pikeman(player: PlayerSide) extends SoldierTile("Pikeman","Pk", player, false, false)
class Footman(player: PlayerSide) extends SoldierTile("Footman","Ft", player, false, false)
class Oracle(player: PlayerSide) extends SoldierTile("Oracle","Or", player, false, false) { override def canUseDivination = true  }

/**************************/
/* TERRAIN TILES	 	  */
/**************************/
class Mountain() extends TerrainTile("Mountain", "MT")
class Camelot() extends TerrainTile("Camelot", "CM") with Inhabitable


/**
 * Base common "abstract" trait describing a tile set to be used in specific GameRules instance
 */
trait TileSet {
	
	protected var allTiles  : Map[PlayerSide, Set[Tile]] = Map()
	
	initTilesForPlayerSide(Light)
	initTilesForPlayerSide(Dark)
	
	protected def initTilesForPlayerSide(side: PlayerSide)
	
	def getPlayerTiles(player:PlayerSide) : Set[Tile]
	def getCommonTiles : Set[Tile] //common tile pot from which either player can pick tiles, based on rules 
}

/**
 * Standard ("vanilla") game set, without any common tiles.
 */
trait StandardTilesTileSet extends TileSet {
	//list all standard tiles
	
	
	
	protected override def initTilesForPlayerSide(side: PlayerSide) = {
		//TODO ADD ALL TILS FROM STANDARD SET
		allTiles += side -> Set(new Duke(true, side))
	}
	
	
	def getPlayerTiles(playerSide:PlayerSide) = allTiles.get(playerSide).get
	def getCommonTiles() = Set() //empty set
	
}
