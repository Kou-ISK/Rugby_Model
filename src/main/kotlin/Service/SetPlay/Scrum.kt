package Service.SetPlay

import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Descriptor.RESULT
import Model.Descriptor.RESULT.*
import Model.Player.Position
import Model.Player.PositionCategory1
import Model.Team.Team
import Service.OpenPlay.Carry

class Scrum {
    // TODO 変数が増え次第内容を追加
    companion object {
        fun getResult(AttTeam: Team, DefTeam: Team) {
            setNum += 1
            val attTeamTotalBodyWeight =
                AttTeam.playerList.filter { it.positionCategory1 == PositionCategory1.FORWARDS }.sumOf { it.bodyWeight }
            val defTeamTotalBodyWeight =
                DefTeam.playerList.filter { it.positionCategory1 == PositionCategory1.FORWARDS }.sumOf { it.bodyWeight }
            // 勝敗決定アルゴリズムは要検討
            val result: RESULT =
                if (attTeamTotalBodyWeight <= defTeamTotalBodyWeight && ((1..100).random() > AttTeam.scrumWonRate)
                ) {
                    LOST
                } else if ((1..100).random() < DefTeam.scrumStealRate) {
                    if (attTeamTotalBodyWeight - AttTeam.scrumWonRate * (1..2).random() <= defTeamTotalBodyWeight) {
                        LOST
                    } else {
                        WON
                    }
                } else {
                    WON
                }
            when (result) {
                WON -> {
                    val scrumHalf = AttTeam.playerList.find { it.position == Position.SCRUMHALF }
                    if (scrumHalf != null) {
                        Carry.decisionMaking(AttTeam, DefTeam, scrumHalf)
                    }
                }

                LOST -> {
                    gainLine = 100 - gainLine
                    val scrumHalf = DefTeam.playerList.find { it.position == Position.SCRUMHALF }
                    if (scrumHalf != null) {
                        Carry.decisionMaking(DefTeam, AttTeam, scrumHalf)
                    }
                }

                WON_PEN -> TODO()
                LOST_PEN -> TODO()
                WON_FK -> TODO()
                LOST_FK -> TODO()
                WON_SCRUM -> TODO()
                LOST_SCRUM -> TODO()
            }
        }
    }
}