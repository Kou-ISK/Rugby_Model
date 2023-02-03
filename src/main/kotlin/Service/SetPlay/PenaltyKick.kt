package Service.SetPlay

import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Team.Team
import Service.OpenPlay.Carry
import Service.OpenPlay.OpenPlay

class PenaltyKick {
    fun getResult(attTeam: Team, defTeam: Team) {
        val i = (1..100).random()
        if (i <= 95) {
            //toTouch
            LineOut().getResult(attTeam, defTeam)
        } else if (i in 96..97) {
            // tap
            setNum += 1
            OpenPlay().openPlay(attTeam, defTeam, attTeam.playerList.random())
        } else {
            // scrum
            Scrum().getResult(attTeam, defTeam)
        }
    }
}