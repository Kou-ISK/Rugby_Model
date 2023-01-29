package Service.SetPlay

import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Descriptor.RESULT
import Model.Descriptor.RESULT.*
import Model.Descriptor.TYPE
import Model.Team.Team
import Service.OpenPlay.Carry

class RestartKick {
    companion object {
        fun getResult(AttTeam: Team, DefTeam: Team, type: TYPE) {
            setNum += 1
            gainLine = when (type) {
                TYPE.DROPOUT -> (23..65).random()
                TYPE.GOAL_LINE_DROPOUT -> (5..50).random()
                TYPE.RESTART -> (60..100).random()
            }
            val result: RESULT = if ((1..100).random() > 90) {
                if ((1..100).random() > 90) {
                    WON
                } else LOST_SCRUM
            } else LOST

            when (result) {
                WON -> Carry.decisionMaking(AttTeam, DefTeam, AttTeam.playerList.random())
                LOST -> Carry.decisionMaking(DefTeam, AttTeam, DefTeam.playerList.random())
                WON_PEN -> {
                    setNum += 1
                    println("PKで再開(未実装)")
                }

                LOST_PEN -> {
                    setNum += 1
                    println("PKで再開(未実装)")
                }

                WON_FK -> {
                    setNum += 1
                    println("FKで再開(未実装)")
                }

                LOST_FK -> {
                    setNum += 1
                    println("FKで再開(未実装)")
                }

                WON_SCRUM -> Scrum.getResult(AttTeam, DefTeam)
                LOST_SCRUM -> Scrum.getResult(DefTeam, AttTeam)
            }
        }
    }
}