package Service

import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Descriptor.GameDescriptor.Companion.team1Score
import Model.Descriptor.GameDescriptor.Companion.team2Score
import Model.Descriptor.TYPE.*
import Model.Team.Team
import Service.SetPlay.RestartKick
import Service.Setting.PlayerSetting

class Game {
    companion object {
        var team1: Team? = null
        var team2: Team? = null
        fun play(Team1: Team, Team2: Team) {
            // ポジションカテゴリの設定
            PlayerSetting.teamPreparation(Team1)
            PlayerSetting.teamPreparation(Team2)
            team1 = Team1
            team2 = Team2
            println(Team1.playerList)
            println(Team2.playerList)
            if (team1 != null && team2 != null) {
                while (setNum < 10) {
                    RestartKick.getResult(team1!!, team2!!, RESTART)
                }
            }
            println("Team1: $team1Score Team2:$team2Score")
        }
    }
}