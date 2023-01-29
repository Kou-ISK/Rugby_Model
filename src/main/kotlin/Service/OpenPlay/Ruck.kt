package Service.OpenPlay

import Model.Descriptor.GameDescriptor
import Model.Descriptor.RESULT
import Model.Descriptor.RESULT.*
import Model.Player.Position
import Model.Team.Team
import Service.Game
import Service.SetPlay.Scrum

class Ruck {
    companion object {
        //TODO 変数を増やしてラックのリアリティを上げる
        private val RUCK_WON_RATE = 90
        private val DEF_PEN_WON_RATE = 40
        fun getResult(AttTeam: Team, DefTeam: Team) {
            val result: RESULT = if ((1..100).random() > RUCK_WON_RATE) {
                if ((1..100).random() < DefTeam.discipline) {
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
                        Carry.decisionMaking(AttTeam, DefTeam, scrumHalf)
                    }
                }

                LOST -> Carry.decisionMaking(DefTeam, AttTeam, DefTeam.playerList.random())
                WON_PEN -> {
                    GameDescriptor.setNum += 1
                    println("PKで再開(未実装)")
                }

                LOST_PEN -> {
                    GameDescriptor.setNum += 1
                    println("PKで再開(未実装)")
                }

                WON_FK -> {
                    GameDescriptor.setNum += 1
                    println("FKで再開(未実装)")
                }

                LOST_FK -> {
                    GameDescriptor.setNum += 1
                    println("FKで再開(未実装)")
                }

                WON_SCRUM -> Scrum.getResult(AttTeam, DefTeam)
                LOST_SCRUM -> Scrum.getResult(DefTeam, AttTeam)
            }
        }
    }
}