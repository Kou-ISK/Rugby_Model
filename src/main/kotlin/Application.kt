import Service.Game
import testdata.test_teams

fun main() {
    Game.play(
        Team1 = test_teams.team1, Team2 = test_teams.team2
    )
}
