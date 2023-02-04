package Service.SetPlay

import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Descriptor.RESULT
import Model.Descriptor.RESULT.*
import Model.Descriptor.TYPE
import Model.Team.Team
import Service.OpenPlay.Carry
import Service.OpenPlay.OpenPlay

class RestartKick {
    fun getResult(attTeam: Team, defTeam: Team, type: TYPE) {
        setNum += 1
        gainLine = when (type) {
            TYPE.DROPOUT -> (23..65).random()
            TYPE.GOAL_LINE_DROPOUT -> (5..50).random()
            TYPE.FIFTY_METER_RESTART -> (60..100).random()
        }
        val result: RESULT = if ((1..100).random() > 90) {
            if ((1..100).random() > 90) {
                WON
            } else LOST_SCRUM
        } else LOST

        when (result) {
            WON -> OpenPlay().openPlay(attTeam, defTeam, attTeam.playerList.random())
            LOST -> OpenPlay().openPlay(defTeam, attTeam, defTeam.playerList.random())
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

            WON_SCRUM -> Scrum().getResult(attTeam, defTeam)
            LOST_SCRUM -> {
                gainLine = 100 - gainLine
                Scrum().getResult(defTeam, attTeam)
            }

            else -> TODO()
        }
    }
}