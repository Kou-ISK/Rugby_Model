package testdata

import Model.Team.Team
import testdata.test_players.Companion.team1PlayerList
import testdata.test_players.Companion.team2PlayerList

class test_teams {
    companion object {
        var team1 = Team(
            teamName = "REDS",
            playerList = team1PlayerList,
            possessionPreferences = 50,
            lineOutWonRate = 70,
            lineOutStealRate = 20,
            scrumWonRate = 95,
            scrumStealRate = 10,
            discipline = 10,
            penaltyGoalRange = 40,
            penaltyGoalPreference = 40
        )
        var team2 = team1.copy(
            teamName = "BLUES",
            playerList = team2PlayerList,
            lineOutWonRate = 90,
            possessionPreferences = 20,
            penaltyGoalRange = 50,
            penaltyGoalPreference = 80
        )
    }
}