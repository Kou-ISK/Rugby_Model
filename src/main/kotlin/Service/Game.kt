package Service

import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Descriptor.GameDescriptor.Companion.team1Score
import Model.Descriptor.GameDescriptor.Companion.team2Score
import Model.Descriptor.TYPE.*
import Model.Team.Team
import Service.SetPlay.RestartKick

class Game(team1: Team, team2: Team) {
    companion object {
        lateinit var team1: Team
        lateinit var team2: Team
    }

    init {
        Game.team1 = team1
        Game.team2 = team2
    }

    fun play() {
        // ポジションカテゴリの設定
        println(team1.playerList)
        println(team2.playerList)

        while (setNum < 10) {
            RestartKick().getResult(team1, team2, RESTART)
        }
        println("Team1: $team1Score Team2:$team2Score")
    }
}