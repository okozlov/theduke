package oleg.theduke.model

import MovementType._

/**
 * Represents movements of a tile on one side.
 */
class MovementArray(val selfOffsetRow : Int, val selfOffsetCol: Int) {
	
	//init to empty
	val array = Array.fill[Option[MovementType]](5,5)(None)
	
	/**
	 * Method is created according to Builder design pattern, so that calls could be chained.
	 *  
	 * Adds movement by absolute coordinates, starting in lower-left corner, which
	 * has coordinates (0,0), where first coord is the row index, and second is the 
	 * column index.
	 * TODO - TEST, TEST, TEST!!!
	 */
	def addMoveByAbs(row: Int, col: Int, mt: MovementType) : MovementArray = {
		array(row)(col) = Some(mt)
		return this;
	}
	
	/**
	 * Method is created according to Builder design pattern, so that calls could be chained.
	 * 
	 * Adds movement by offset from "self" on the board. The offset is specified as (row,col)
	 * coordinate, and offset from the cell containing the "owner" tile  (tile that owns this
	 * MovementArray).
	 * TODO - TEST, TEST, TEST!!!
	 */
	def addMoveByOffset(row: Int, col: Int, mt: MovementType) : MovementArray = {
		array(row + selfOffsetRow)(col + selfOffsetCol) = Some(mt)
		return this;
	}
	
	//FIXME fix to print SELF, and also FLIP OUTPUT VERTICALLY (print rows UPSIDE-DOWN)
	//FIXME-2 ALSO - remove Some(..) from print, print movementtype itself
	override def toString() : String = {
		
		val sb = new StringBuilder
		array.foreach { 
			x => sb ++= x.mkString(" "); sb ++= "\n" 
		}
		
		return sb.toString();
	}
}


