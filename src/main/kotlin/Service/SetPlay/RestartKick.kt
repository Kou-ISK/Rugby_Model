package Service.SetPlay

import Model.Descriptor.RESULT
import Model.Team.Team

class RestartKick {
    companion object {
        fun getResult(AttTeam: Team, DefTeam: Team): RESULT {
            return if ((1..100).random() > 90) {
                if ((1..100).random() > 90) {
                    RESULT.WON
                } else RESULT.LOST_SCRUM
            } else RESULT.LOST
        }
    }
}