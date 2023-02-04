package Model.Descriptor

class GameDescriptor {
    companion object {
        var team1Score: Int = 0
        var team2Score: Int = 0
        var period: Int = 1
        var setNum: Int = 0

        // TurnOver時に gainLine = 100 -gainLineする
        var gainLine: Int = 0
    }
}