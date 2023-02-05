package Service

import Model.Descriptor.GameDescriptor.Companion.period
import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Descriptor.GameDescriptor.Companion.team1Score
import Model.Descriptor.GameDescriptor.Companion.team2Score
import Model.Descriptor.POSSESSIONSTART.*
import Model.Descriptor.PlayResult
import Model.Descriptor.TYPE.*
import Model.Team.Team
import Service.OpenPlay.OpenPlay
import Service.SetPlay.*

class Game(team1: Team, team2: Team) {
    companion object {
        lateinit var team1: Team
        lateinit var team2: Team
        lateinit var playResult: PlayResult
    }

    init {
        Game.team1 = team1
        Game.team2 = team2
        playResult = PlayResult(RESTART, Companion.team1, Companion.team2, restartType = FIFTY_METER_RESTART)
    }

    fun play() {
        // ポジションカテゴリの設定
        println(team1.playerList.map { it.position })
        println(team2.playerList.map { it.position })
        while (period < 3) {
            if (period == 1) println("-*-*-*-*-*-*-*-*- First Half Kick Off -*-*-*-*-*-*-*-*-")
            else println("-*-*-*-*-*-*-*-*- Second Half Kick Off -*-*-*-*-*-*-*-*-")
            setNum = 0
            while (setNum < 20) {
                println("----------${playResult.attTeam.teamName} ${playResult.startOfPossession}----------")
                when (playResult.startOfPossession) {
                    LINEOUT -> LineOut().getResult(playResult.attTeam, playResult.defTeam)
                    SCRUM -> Scrum().getResult(playResult.attTeam, playResult.defTeam)
                    RESTART -> RestartKick().getResult(playResult.attTeam, playResult.defTeam, playResult.restartType!!)
                    PK -> PenaltyKick().getResult(playResult.attTeam, playResult.defTeam)
                    FK -> FreeKick().getResult(playResult.attTeam, playResult.defTeam)
                    TURNOVER -> {
                        OpenPlay().openPlay(
                            playResult.attTeam,
                            playResult.defTeam, playResult.attTeam.playerList.random()
                        )
                    }

                    KICK_COUNTER -> {
                        OpenPlay().openPlay(
                            playResult.attTeam,
                            playResult.defTeam,
                            playResult.attTeam.getOutSideBacks()
                        )
                    }
                }
            }
            println("----------SCORE----------")
            println("${team1.teamName}: $team1Score ${team2.teamName}: $team2Score")
            println("-------------------------")
            period++
        }
        println("Full Time")
    }
}