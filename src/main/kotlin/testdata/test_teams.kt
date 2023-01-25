package testdata

import Model.Team.Team
import testdata.test_players.Companion.team1PlayerList
import testdata.test_players.Companion.team2PlayerList

class test_teams {
    companion object {
        var team1 = Team(
            teamName = "Team1",
            playerList = team1PlayerList,
            possessionPreferences = 50,
            lineOutWonRate = 0.7F,
            lineOutStealRate = 0.2F,
            scrumWonRate = 0.95F,
            scrumStealRate = 0.1F,
            discipline = 0.2F
        )
        var team2 = team1.copy(playerList = team2PlayerList, lineOutWonRate = 0.9F, possessionPreferences = 20)
    }
}