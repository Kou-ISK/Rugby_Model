package Service.OpenPlay

import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Descriptor.GameDescriptor.Companion.team1Score
import Model.Descriptor.GameDescriptor.Companion.team2Score
import Model.Descriptor.RESULT
import Model.Descriptor.TYPE.*
import Model.Player.Player
import Model.Player.Position
import Model.Team.Team
import Service.Game.Companion.team1
import Service.SetPlay.LineOut
import Service.SetPlay.RestartKick
import Service.SetPlay.Scrum

class Carry {
    fun decisionMaking(attTeam: Team, defTeam: Team, player: Player) {
        var decisionList = listOf<Int>()
        for (i in 1..player.carryPreference) decisionList += 0
        for (i in 1..player.passPreference) decisionList += 1
        for (i in 1..player.kickPreference) decisionList += 2
        when (decisionList.random()) {
            0 -> {
                println("Carry")
                carry(attTeam, defTeam, player)
            }

            1 -> {
                println("Pass")
                pass(attTeam, defTeam, player)
            }

            2 -> {
                println("Kick")
                kick(attTeam, defTeam, player)
            }
        }
    }

    fun carry(attTeam: Team, defTeam: Team, player: Player) {
        gainLine += (0..5).random()
        println("Gain Line: $gainLine")
        // Touchの判定
        if (player.position == Position.FULLBACK || player.position == Position.OPEN_SIDE_WINGER || player.position == Position.BLIND_SIDE_WINGER) {
            LineOut().getResult(defTeam, attTeam)
        } else {
            if (gainLine >= 100) {
                scoreTry(attTeam, defTeam, player)
            }
            if (contact() == RESULT.WON) {
                gainLine += (5..15).random()
                decisionMaking(attTeam, defTeam, player)
            } else Ruck().getResult(attTeam, defTeam)
        }
    }

    private fun pass(attTeam: Team, defTeam: Team, player: Player) {
        val carrier = attTeam.playerList.random()
        gainLine -= (0..5).random()
        if ((1..100).random() > 85) {
            println("Knock On")
            Scrum().getResult(defTeam, attTeam)
        }
        decisionMaking(attTeam, defTeam, carrier)
    }

    private fun kick(attTeam: Team, defTeam: Team, player: Player) {
        val kickMeter = (40..60).random()
        if (gainLine + kickMeter > 110 || gainLine + kickMeter < -10) {
            gainLine = 100 - gainLine
            Scrum().getResult(defTeam, attTeam)
        } else if (gainLine + kickMeter > 100 || gainLine + kickMeter < 0) {
            gainLine += kickMeter
        }
        println("Gain Line: $gainLine")
        if ((1..100).random() > 95) {
            if ((1..100).random() > 90) {
                decisionMaking(attTeam, defTeam, attTeam.playerList.random())
            } else Scrum().getResult(defTeam, attTeam)
        } else {
            gainLine = 100 - gainLine
            decisionMaking(defTeam, attTeam, defTeam.playerList.random())
        }
    }

    // TODO DFも含めて変数、処理を追加
    private fun contact(): RESULT {
        return if ((0..100).random() < 90) {
            RESULT.LOST
        } else RESULT.WON
    }

    private fun scoreTry(attTeam: Team, defTeam: Team, player: Player) {
        if (attTeam == team1) {
            team1Score += 5
            if ((0..100).random() < 80) team1Score += 2
        } else {
            team2Score += 5
            if ((0..100).random() < 80) team2Score += 2
        }
        println("Try scored by ${player.shirtNo}")
        RestartKick().getResult(defTeam, attTeam, RESTART)
    }
}