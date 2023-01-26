package Service.SetPlay

import Model.Player.PositionCategory1
import Model.Team.Team

class Scrum {
    // TODO 変数が増え次第内容を追加
    companion object {
        fun getResult(AttTeam: Team, DefTeam: Team): RESULT {
            val attTeamTotalBodyWeight =
                AttTeam.playerList.filter { it.positionCategory1 == PositionCategory1.FORWARDS }.sumOf { it.bodyWeight }
            val defTeamTotalBodyWeight =
                DefTeam.playerList.filter { it.positionCategory1 == PositionCategory1.FORWARDS }.sumOf { it.bodyWeight }
            // 勝敗決定アルゴリズムは要検討
            return if (attTeamTotalBodyWeight <= defTeamTotalBodyWeight && ((1..100).random() > AttTeam.scrumWonRate * 100)
            ) {
                RESULT.LOST
            } else if ((1..100).random() < DefTeam.scrumStealRate * 100) {
                if (attTeamTotalBodyWeight - AttTeam.scrumWonRate * (1..2).random() <= defTeamTotalBodyWeight) {
                    RESULT.LOST
                } else {
                    RESULT.WON
                }
            } else {
                RESULT.WON
            }
        }
    }

    enum class RESULT {
        WON, LOST, WON_PEN, LOST_PEN, WON_FK, LOST_FK, WON_SCRUM, LOST_SCRUM
    }
}