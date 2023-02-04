package Service.SetPlay

import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Team.Team
import Service.OpenPlay.OpenPlay

class FreeKick {
    fun getResult(attTeam: Team, defTeam: Team) {
        val i = (1..100).random()
        if (i <= 90) {
            // scrum
            Scrum().getResult(attTeam, defTeam)
        } else if (i in 91..98) {
            //toTouch
            gainLine = 100 - gainLine
            LineOut().getResult(defTeam, attTeam)
        } else {
            // tap
            OpenPlay().openPlay(attTeam, defTeam, attTeam.playerList.random())
        }
    }
}