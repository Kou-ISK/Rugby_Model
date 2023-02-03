package Model.Descriptor

import Model.Player.Player
import Model.Player.Position
import Model.Team.Team

enum class RESULT {
    WON, LOST, WON_PEN, LOST_PEN, WON_FK, LOST_FK, WON_SCRUM, LOST_SCRUM, TRY_SCORED, KICKED, WON_LINEOUT, LOST_LINEOUT, AGAIN, PASSED
}

enum class START_OF_POSSESSION {
    LINEOUT, SCRUM, RESTART, PK, FK, TURNOVER, KICK_COUNTER
}

enum class TYPE {
    DROPOUT, GOAL_LINE_DROPOUT, RESTART
}

data class PlayResult(
    var startOfPossession: START_OF_POSSESSION,
    var attTeam: Team,
    var defTeam: Team
)