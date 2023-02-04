package Service.OpenPlay

import Model.Descriptor.GameDescriptor.Companion.gainLine
import Model.Descriptor.RESULT
import Model.Player.Player
import Model.Player.Position
import Model.Team.Team

class Carry {
    fun decisionMaking(attTeam: Team, defTeam: Team, player: Player): RESULT {
        val decisionList = mutableListOf<Int>()
        for (i in 1..player.carryPreference) decisionList += 0
        for (i in 1..player.passPreference) decisionList += 1
        for (i in 1..player.kickPreference) decisionList += 2
        when (decisionList.random()) {
            0 -> {
                println("Carry")
                val result = carry(attTeam, defTeam, player)
                println("RESULT: $result")
                return result
            }

            1 -> {
                println("Pass")
                return pass(attTeam, defTeam, player)
            }

            2 -> {
                println("Kick")
                return kick(attTeam, defTeam, player)
            }

            else -> return RESULT.WON
        }
    }

    fun carry(attTeam: Team, defTeam: Team, player: Player): RESULT {
        gainLine += (0..5).random()
        println("Gain Line: $gainLine")
        var result: RESULT = RESULT.WON
        if (gainLine >= 100) result = RESULT.TRY_SCORED
        else {
            // Touchの判定
            if (player.position == Position.FULLBACK || player.position == Position.OPEN_SIDE_WINGER || player.position == Position.BLIND_SIDE_WINGER) {
                result = RESULT.LOST_LINEOUT
            }
            if (contact() == RESULT.WON) {
                gainLine += (5..15).random()
                result = RESULT.AGAIN
            }
        }
        return result
    }

    private fun pass(attTeam: Team, defTeam: Team, player: Player): RESULT {
        if ((0..100).random() > 80) return RESULT.LOST
        gainLine -= (0..5).random()
        if ((1..100).random() > 85) {
            println("Knock On")
            return RESULT.LOST_SCRUM
        }
        return RESULT.PASSED
    }

    private fun kick(attTeam: Team, defTeam: Team, player: Player): RESULT {
        val kickMeter = (40..60).random()
        var result = RESULT.KICKED
        if (gainLine + kickMeter > 110 || gainLine + kickMeter < -10) {
            println("Gain Line:" + (gainLine + kickMeter) + "(Ball Dead)")
            result = RESULT.LOST_SCRUM
        } else if (gainLine + kickMeter > 100 || gainLine + kickMeter < 0) {
            gainLine += kickMeter
        }
        println("Gain Line: $gainLine")
        gainLine = 100 - gainLine
        return result
    }

    // TODO DFも含めて変数、処理を追加
    private fun contact(): RESULT {
        return if ((0..100).random() < 90) {
            RESULT.LOST
        } else RESULT.WON
    }
}