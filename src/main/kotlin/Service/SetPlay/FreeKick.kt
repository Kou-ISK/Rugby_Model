package Service.SetPlay

import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Team.Team
import Service.OpenPlay.Carry
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
            setNum += 1
            OpenPlay().openPlay(attTeam, defTeam, attTeam.playerList.random())
        }
    }
}