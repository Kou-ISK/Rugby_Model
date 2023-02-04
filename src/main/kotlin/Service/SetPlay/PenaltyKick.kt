package Service.SetPlay

import Model.Descriptor.GameDescriptor
import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Descriptor.POSSESSIONSTART.*
import Model.Descriptor.PlayResult
import Model.Descriptor.TYPE
import Model.Team.Team
import Service.Game
import Service.Game.Companion.playResult
import Service.OpenPlay.OpenPlay

class PenaltyKick {
    fun getResult(attTeam: Team, defTeam: Team) {
        val i = (1..100).random()
        //PGレンジに入っているかチェック、PG選好に基づいて決定
        if (gainLine > 100 - attTeam.penaltyGoalRange && (1..100).random() < attTeam.penaltyGoalPreference) {
            playResult = shotAtGoal(attTeam, defTeam)
        }
        if (i <= 95) {
            //toTouch
            playResult = PlayResult(LINEOUT, attTeam, defTeam)
        } else if (i in 96..97) {
            // tap
            OpenPlay().openPlay(attTeam, defTeam, attTeam.playerList.random())
        } else {
            // scrum
            playResult = PlayResult(SCRUM, attTeam, defTeam)
        }
    }

    private fun shotAtGoal(attTeam: Team, defTeam: Team): PlayResult {
        var result = PlayResult(RESTART, defTeam, attTeam, restartType = TYPE.DROPOUT)
        if ((1..100).random() < (attTeam.getKicker().shotSuccessRatio ?: 0)) {
            if (attTeam == Game.team1) {
                GameDescriptor.team1Score += 3
            } else {
                GameDescriptor.team2Score += 3
            }
            println("Penalty Goal by ${attTeam.getKicker().shirtNo}")
            gainLine = 100 - gainLine
            result = PlayResult(RESTART, defTeam, attTeam, restartType = TYPE.FIFTY_METER_RESTART)
        }
        return result
    }
}