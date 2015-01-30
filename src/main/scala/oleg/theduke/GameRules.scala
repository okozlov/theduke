package oleg.theduke

/**
 * Base class for game rules. Sub-classes will provide customizable game rules,
 * this class provides most of the default for the "Classic" (i.e. by the book) 
 * The Duke game rules as specific in the original games rule book, or expansion sets
 * rule book, where applicable.
 */
abstract class GameRules {
	
	def canCaptureTerrainTile = false

}