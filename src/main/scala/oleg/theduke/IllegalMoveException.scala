package oleg.theduke

import oleg.theduke.model.Coordinates
import oleg.theduke.model.Tile

/**
 * @author oleg
 */
class IllegalMoveException(val tile: Tile, val coord:Coordinates, val reason:String) 
	extends Exception("Cannot move '"+tile+"' to "+coord+ (if (reason!=null) " : " + reason)) {

	def this(tile: Tile, coord:Coordinates) = this(tile, coord, null)
	
}
	