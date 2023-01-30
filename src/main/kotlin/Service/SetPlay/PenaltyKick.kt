package Service.SetPlay

import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Team.Team
import Service.OpenPlay.Carry

class PenaltyKick {
    companion object {
        fun getResult(attTeam: Team, defTeam: Team) {
            val i = (1..100).random()
            if (i <= 95) {
                //toTouch
                LineOut.getResult(attTeam, defTeam)
            } else if (i in 96..97) {
                // tap
                setNum += 1
                Carry.decisionMaking(attTeam, defTeam, attTeam.playerList.random())
            } else {
                // scrum
                Scrum.getResult(attTeam, defTeam)
            }
        }
    }
}