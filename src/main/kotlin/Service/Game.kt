package Service

import Model.Team.Team
import Service.SetPlay.LineOut
import Service.SetPlay.Scrum
import Service.Setting.PlayerSetting

class Game {
    companion object {
        fun play(Team1: Team, Team2: Team) {
            // ポジションカテゴリの設定
            PlayerSetting.teamPreparation(Team1)
            PlayerSetting.teamPreparation(Team2)
            println(Team1.playerList)
            println(Team2.playerList)
            // ラインアウトを10回試行
            for (i in 1..10) {
                println("LineOut: " + LineOut.getResult(Team1, Team2))
            }
            
            var count = 0
            for (i in 1..100) {
                if (Scrum.getResult(Team1, Team2) == Scrum.RESULT.WON) count++
                println("Scrum:" + Scrum.getResult(Team1, Team2))
            }
            println(count)
        }
    }
}