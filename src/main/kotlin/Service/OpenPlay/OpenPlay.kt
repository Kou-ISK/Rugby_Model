package Service.OpenPlay

import Model.Descriptor.*
import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Descriptor.RESULT.*
import Model.Descriptor.START_OF_POSSESSION.*
import Model.Player.Player
import Model.Player.Position
import Model.Team.Team
import Service.Game
import Service.SetPlay.*

class OpenPlay {
    //TODO 変数を増やしてラックのリアリティを上げる
    private val RUCK_WON_RATE = 90
    private val DEF_PEN_WON_RATE = 40

    fun openPlay(attTeam: Team, defTeam: Team, player: Player): PlayResult {
        var result: RESULT = WON
        var phaseNum = 0
        //var player = attTeam.playerList.random()
        while (result in listOf(WON, AGAIN, PASSED)) {
            result = decisionMaking(attTeam, defTeam, player)
            if (result != WON) break
            if (result !in listOf(AGAIN, PASSED)) {
                result = ruck(attTeam, defTeam)
                phaseNum++
            }
        }
        println("${attTeam.teamName} attacked for $phaseNum Phases")
        return when (result) {
            LOST -> PlayResult(TURNOVER, defTeam, attTeam)
            WON_PEN -> PlayResult(PK, attTeam, defTeam)
            LOST_PEN -> PlayResult(PK, defTeam, attTeam)
            WON_FK -> PlayResult(FK, attTeam, defTeam)
            LOST_FK -> PlayResult(FK, defTeam, attTeam)
            WON_SCRUM -> PlayResult(SCRUM, attTeam, defTeam)
            LOST_SCRUM -> PlayResult(SCRUM, defTeam, attTeam)
            TRY_SCORED -> TODO()
            KICKED -> PlayResult(KICK_COUNTER, defTeam, attTeam)
            WON_LINEOUT -> PlayResult(LINEOUT, attTeam, defTeam)
            LOST_LINEOUT -> PlayResult(LINEOUT, defTeam, attTeam)
            else -> TODO()
        }
    }

    fun decisionMaking(attTeam: Team, defTeam: Team, player: Player): RESULT {
        var decisionList = listOf<Int>()
        for (i in 1..player.carryPreference) decisionList += 0
        for (i in 1..player.passPreference) decisionList += 1
        for (i in 1..player.kickPreference) decisionList += 2
        when (decisionList.random()) {
            0 -> {
                println("Carry")
                return carry(attTeam, defTeam, player)
            }

            1 -> {
                println("Pass")
                return pass(attTeam, defTeam, player)
            }

            2 -> {
                println("Kick")
                return kick(attTeam, defTeam, player)
            }

            else -> return WON
        }
    }

    fun carry(attTeam: Team, defTeam: Team, player: Player): RESULT {
        gainLine += (0..5).random()
        println("Gain Line: $gainLine")
        if (gainLine >= 100) return TRY_SCORED
        // Touchの判定
        if (player.position == Position.FULLBACK || player.position == Position.OPEN_SIDE_WINGER || player.position == Position.BLIND_SIDE_WINGER) {
            return LOST_LINEOUT
        } else if (contact() == WON) {
            gainLine += (5..15).random()
        }
        return AGAIN
    }

    private fun pass(attTeam: Team, defTeam: Team, player: Player): RESULT {
        if ((0..100).random() > 80) return LOST
        gainLine -= (0..5).random()
        if ((1..100).random() > 85) {
            println("Knock On")
            return LOST_SCRUM
        }
        return PASSED
    }

    private fun kick(attTeam: Team, defTeam: Team, player: Player): RESULT {
        val kickMeter = (40..60).random()
        if (gainLine + kickMeter > 110 || gainLine + kickMeter < -10) {
            println("Gain Line: $gainLine")
            return LOST_SCRUM
        } else if (gainLine + kickMeter > 100 || gainLine + kickMeter < 0) {
            gainLine += kickMeter
        }
        println("Gain Line: $gainLine")
        return KICKED
    }

    // TODO DFも含めて変数、処理を追加
    private fun contact(): RESULT {
        return if ((0..100).random() < 90) {
            LOST
        } else WON
    }

    private fun scoreTry(attTeam: Team, defTeam: Team, player: Player) {
        if (attTeam == Game.team1) {
            GameDescriptor.team1Score += 5
            if ((0..100).random() < 80) GameDescriptor.team1Score += 2
        } else {
            GameDescriptor.team2Score += 5
            if ((0..100).random() < 80) GameDescriptor.team2Score += 2
        }
        println("Try scored by ${player.shirtNo}")
        RestartKick().getResult(defTeam, attTeam, TYPE.RESTART)
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