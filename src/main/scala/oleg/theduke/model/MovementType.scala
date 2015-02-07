package oleg.theduke.model


object MovementType extends Enumeration {
  type MovementType = Value
  val Move, NCMove, Smash, Jump, NCJump, Slide, JumpSlide, Strike, Hammer, Command, Dread, Defence = Value
  
  class MovementTypeValue(mt: Value) {
    def canCapture  = ! (mt == NCMove || mt == NCJump)
    def describe = mt.toString() + " " + (if (canCapture) "can" else "cannot") + " capture"
  }
  
  implicit def value2MovementTypeValue (mt:Value) = new MovementTypeValue(mt)
}


