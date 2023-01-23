package Model.Team

import Model.Player.Player

data class Team (
    val teamName:String,
    val playerList:List<Player>,
    val possessionPreferences:Int,
    val lineOutWonRate:Float,
    val lineOutStealRate:Float,
    val scrumWonRate:Float,
    val scrumStealRate:Float,
    val discipline:Float
)