package oleg.theduke.model

import oleg.theduke.model.PlayerSide._
import oleg.theduke.GameRules


class Player(val playerSide : PlayerSide, val gameRules : GameRules) {

	/**
	 * Player's top-level action on his turn
	 * 
	 * @param board - player must be able to see the board to decide what to do turing his turn 
	 */
	def act(board:Board) : PlayerAction = {
		//TODO implement
		//here we will use stratege design patter to be able to plug-in different player strategies
		return null
	}
	
}