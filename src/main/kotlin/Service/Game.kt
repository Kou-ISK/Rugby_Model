package Service

import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Descriptor.GameDescriptor.Companion.team1Score
import Model.Descriptor.GameDescriptor.Companion.team2Score
import Model.Descriptor.TYPE.*
import Model.Team.Team
import Service.SetPlay.RestartKick

class Game {
    companion object {
        var team1: Team = null
        var team2: Team = null
    }

    fun play(team1: Team, team2: Team) {
        // ポジションカテゴリの設定
        for (player in team1.playerList + team2.playerList) {
            player.setPositionCategory()
        }

        println(team1.playerList)
        println(team2.playerList)

        while (setNum < 10) {
            RestartKick().getResult(team1, team2, RESTART)
        }
        println("Team1: $team1Score Team2:$team2Score")
    }
}