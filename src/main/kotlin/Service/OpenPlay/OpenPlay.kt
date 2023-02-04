package Service.OpenPlay

import Model.Descriptor.*
import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Descriptor.RESULT.*
import Model.Descriptor.POSSESSIONSTART.*
import Model.Descriptor.TYPE.*
import Model.Player.Player
import Model.Team.Team
import Service.Game
import Service.Game.Companion.playResult

class OpenPlay {
    //TODO 変数を増やしてラックのリアリティを上げる
    private val RUCK_WON_RATE = 90
    private val DEF_PEN_WON_RATE = 40

    fun openPlay(attTeam: Team, defTeam: Team, player: Player) {
        var result: RESULT = WON
        var phaseNum = 0
        var player = player
        while (result in listOf(WON, AGAIN, PASSED)) {
            result = Carry().decisionMaking(attTeam, defTeam, player)
            if (result == PASSED) player = attTeam.playerList.random()
            if (result == WON) {
                result = ruck(attTeam, defTeam)
                player = attTeam.getScrumHalf()
                phaseNum++
            }
        }
        println("${attTeam.teamName} attacked for $phaseNum Phases")
        when (result) {
            LOST -> {
                gainLine = 100 - gainLine
                playResult = PlayResult(TURNOVER, defTeam, attTeam)
            }

            WON_PEN -> playResult = PlayResult(PK, attTeam, defTeam)
            LOST_PEN -> {
                gainLine = 100 - gainLine
                playResult = PlayResult(PK, defTeam, attTeam)
            }

            WON_FK -> playResult = PlayResult(FK, attTeam, defTeam)
            LOST_FK -> {
                gainLine = 100 - gainLine
                playResult = PlayResult(FK, defTeam, attTeam)
            }

            WON_SCRUM -> playResult = PlayResult(SCRUM, attTeam, defTeam)
            LOST_SCRUM -> {
                gainLine = 100 - gainLine
                playResult = PlayResult(SCRUM, defTeam, attTeam)
            }

            TRY_SCORED -> {
                scoreTry(attTeam, player)
                playResult = PlayResult(RESTART, defTeam, attTeam, FIFTY_METER_RESTART)
            }

            KICKED -> playResult = PlayResult(KICK_COUNTER, defTeam, attTeam)
            WON_LINEOUT -> playResult = PlayResult(LINEOUT, attTeam, defTeam)
            LOST_LINEOUT -> playResult = PlayResult(LINEOUT, defTeam, attTeam)
            else -> TODO()
        }
    }

    private fun scoreTry(attTeam: Team, player: Player) {
        if (attTeam == Game.team1) {
            GameDescriptor.team1Score += 5
            if ((0..100).random() < attTeam.getKicker().shotSuccessRatio!!) GameDescriptor.team1Score += 2
        } else {
            GameDescriptor.team2Score += 5
            if ((0..100).random() < attTeam.getKicker().shotSuccessRatio!!) GameDescriptor.team2Score += 2
        }
        println("Try scored by ${player.shirtNo}")
        gainLine = 100 - gainLine
    }

    fun ruck(attTeam: Team, defTeam: Team): RESULT {
        if ((1..100).random() > RUCK_WON_RATE) {
            return if ((1..100).random() < defTeam.discipline) {
                WON_PEN
            } else if ((1..100).random() < DEF_PEN_WON_RATE) {
                LOST_PEN
            } else {
                LOST
            }
        }
        return WON
    }
}