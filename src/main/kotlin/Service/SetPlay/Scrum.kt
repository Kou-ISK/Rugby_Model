package Service.SetPlay

import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Descriptor.RESULT
import Model.Descriptor.RESULT.*
import Model.Player.Position
import Model.Player.PositionCategory1
import Model.Team.Team
import Service.OpenPlay.OpenPlay

class Scrum {
    // TODO 変数が増え次第内容を追加
    fun getResult(attTeam: Team, defTeam: Team) {
        setNum += 1
        val attTeamTotalBodyWeight =
            attTeam.playerList.filter { it.positionCategory1 == PositionCategory1.FORWARDS }.sumOf { it.bodyWeight }
        val defTeamTotalBodyWeight =
            defTeam.playerList.filter { it.positionCategory1 == PositionCategory1.FORWARDS }.sumOf { it.bodyWeight }
        // 勝敗決定アルゴリズムは要検討
        val result: RESULT =
            if (attTeamTotalBodyWeight <= defTeamTotalBodyWeight && ((1..100).random() > attTeam.scrumWonRate)
            ) {
                LOST
            } else if ((1..100).random() < defTeam.scrumStealRate) {
                if (attTeamTotalBodyWeight - attTeam.scrumWonRate * (1..2).random() <= defTeamTotalBodyWeight) {
                    LOST
                } else {
                    WON
                }
            } else {
                WON
            }
        println("Result: $result")
        when (result) {
            WON -> {
                val scrumHalf = attTeam.playerList.find { it.position == Position.SCRUMHALF }
                if (scrumHalf != null) {
                    OpenPlay().openPlay(attTeam, defTeam, scrumHalf)
                }
            }

            LOST -> {
                gainLine = 100 - gainLine
                val scrumHalf = defTeam.playerList.find { it.position == Position.SCRUMHALF }
                if (scrumHalf != null) {
                    OpenPlay().openPlay(defTeam, attTeam, scrumHalf)
                }
            }

            WON_PEN -> PenaltyKick().getResult(attTeam, defTeam)
            LOST_PEN -> {
                gainLine = 100 - gainLine
                PenaltyKick().getResult(defTeam, attTeam)
            }

            WON_FK -> FreeKick().getResult(attTeam, defTeam)
            LOST_FK -> {
                gainLine = 100 - gainLine
                FreeKick().getResult(defTeam, attTeam)
            }

            WON_SCRUM -> getResult(attTeam, defTeam)
            LOST_SCRUM -> {
                gainLine = 100 - gainLine
                getResult(defTeam, attTeam)
            }

            else -> TODO()
        }
    }
}
