package Service.OpenPlay

import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Descriptor.RESULT
import Model.Descriptor.RESULT.*
import Model.Player.Position
import Model.Team.Team
import Service.Game
import Service.SetPlay.FreeKick
import Service.SetPlay.PenaltyKick
import Service.SetPlay.Scrum

class Ruck {
    companion object {
        //TODO 変数を増やしてラックのリアリティを上げる
        private val RUCK_WON_RATE = 90
        private val DEF_PEN_WON_RATE = 40
        fun getResult(attTeam: Team, defTeam: Team) {
            val result: RESULT = if ((1..100).random() > RUCK_WON_RATE) {
                if ((1..100).random() < defTeam.discipline) {
                    WON_PEN
                } else {
                    if ((1..100).random() < DEF_PEN_WON_RATE) {
                        LOST_PEN
                    } else {
                        LOST
                    }
                }
            } else {
                WON
            }
            when (result) {
                WON -> {
                    val scrumHalf = Game.team2!!.playerList.find { it.position == Position.SCRUMHALF }
                    if (scrumHalf != null) {
                        Carry.decisionMaking(attTeam, defTeam, scrumHalf)
                    }
                }

                LOST -> {
                    gainLine = 100 - gainLine
                    Carry.decisionMaking(defTeam, attTeam, defTeam.playerList.random())
                }

                WON_PEN -> PenaltyKick.getResult(attTeam, defTeam)
                LOST_PEN -> {
                    gainLine = 100 - gainLine
                    PenaltyKick.getResult(defTeam, attTeam)
                }

                WON_FK -> FreeKick.getResult(attTeam, defTeam)
                LOST_FK -> {
                    gainLine = 100 - gainLine
                    FreeKick.getResult(defTeam, attTeam)
                }

                WON_SCRUM -> Scrum.getResult(attTeam, defTeam)
                LOST_SCRUM -> {
                    gainLine = 100 - gainLine
                    Scrum.getResult(defTeam, attTeam)
                }
            }
        }
    }
}