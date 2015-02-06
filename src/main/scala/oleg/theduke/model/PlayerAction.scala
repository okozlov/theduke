package oleg.theduke.model

/**
 * When player decides what top-level action to execute on his turn - it
 * returns an instance of PlayerAction
 */
abstract class PlayerAction(val name: String) {

}

case class MoveAction(from: Coordinates, to: Coordinates) extends PlayerAction("Move")
case class DrawTileAction(numOfTiles: Int) extends PlayerAction("Draw Tile")

//expanded play actions...
case class SummonAction(tile : Tile) 		extends PlayerAction("Summon (EP)")
case class DivinationAction(tile : Tile) 	extends PlayerAction("Divination (EP)")
case class EscapeAction(tile : Tile) 		extends PlayerAction("Escape (EP)")
case class RansomAction(tile : Tile) 		extends PlayerAction("Ransom (EP)")
case class RedeployAction(tile : Tile) 		extends PlayerAction("Redeploy (EP)")