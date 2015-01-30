package oleg.theduke.model

/**
 * @author oleg
 */
class TileNotInBagException(val tileName:String) extends Exception("Tile " + tileName + " not found in bag") {
	
}