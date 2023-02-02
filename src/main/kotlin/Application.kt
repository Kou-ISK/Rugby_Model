import Service.Game
import testdata.test_teams

fun main() {
    Game(test_teams.team1, test_teams.team2).play()
}
