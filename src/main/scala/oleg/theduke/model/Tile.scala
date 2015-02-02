package oleg.theduke.model

import oleg.theduke.model.Player._


/**
 * @author oleg
 *
 * @param mustBeCaptured true if this tile must be captured by opponent in order to win
 */
abstract class Tile(val name: String, val abbr: String, val player: Player, var isDuke: Boolean, val mustBeCaptured: Boolean) {

	//true = staring side, false = non-starting side of the tile, a.k.a (up/down)
	var side: Boolean = true
	

	override def toString = name + (if (isDuke) "*" else "") + (if (player==Dark) "-D" else if (player==Light) "-L") 
}

trait Inhabitable {
	var tileInside : Option[SoldierTile] = None
	def hasTileInside = tileInside != None
}


abstract class SoldierTile(name: String, abbr: String, player:Player, isDuke: Boolean, mustBeCaptured: Boolean) extends Tile(name, abbr, player, isDuke, mustBeCaptured)
abstract class TerrainTile(name: String, abbr: String) extends Tile(name, abbr, Neutral, false, false)



/**************************
 * STANDARD SET TILES
 **************************/
//Duke tile may not be be acting as The Duke when playing some extensions
class Duke(isDuke: Boolean, player: Player) extends SoldierTile("Duke", "Du", player, isDuke, isDuke)
class Duchess(player: Player) extends SoldierTile("Duchess", "Dc", player, false, false)
class Assassin(player: Player) extends SoldierTile("Assassin", "As", player, false, false)
class General(player: Player) extends SoldierTile("General", "Ge", player, false, false)
class Pikeman(player: Player) extends SoldierTile("Pikeman","Pk", player, false, false)
class Footman(player: Player) extends SoldierTile("Footman","Ft", player, false, false)

/**************************/
/* TERRAIN TILES	 	  */
/**************************/
class Mountain() extends TerrainTile("Mountain", "MT")
class Camelot() extends TerrainTile("Camelot", "CM") with Inhabitable