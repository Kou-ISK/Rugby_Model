package testdata

import Model.Player.Player
import Model.Player.Position

class test_players {
    companion object {
        var team1PlayerList = listOf(
            Player(
                shirtNo = 1,
                position = Position.LOOSE_HEAD_PROP,
                bodyHeight = 180,
                bodyWeight = 110,
                speed = 3,
                passPreference = 5,
                carryPreference = 94,
                kickPreference = 1,
            ), Player(
                shirtNo = 2,
                position = Position.HOOKER,
                bodyHeight = 175,
                bodyWeight = 100,
                speed = 3,
                passPreference = 5,
                carryPreference = 94,
                kickPreference = 1,
                kickerFlag = false
            ), Player(
                shirtNo = 3,
                position = Position.TIGHT_HEAD_PROP,
                bodyHeight = 180,
                bodyWeight = 110,
                speed = 3,
                passPreference = 5,
                carryPreference = 94,
                kickPreference = 1
            ), Player(
                shirtNo = 4,
                position = Position.LEFT_LOCK,
                bodyHeight = 195,
                bodyWeight = 110,
                speed = 4,
                passPreference = 5,
                carryPreference = 94,
                kickPreference = 1
            ), Player(
                shirtNo = 5,
                position = Position.RIGHT_LOCK,
                bodyHeight = 200,
                bodyWeight = 115,
                speed = 4,
                passPreference = 5,
                carryPreference = 94,
                kickPreference = 1
            ), Player(
                shirtNo = 6,
                position = Position.BLIND_SIDE_FLANKER,
                bodyHeight = 180,
                bodyWeight = 100,
                speed = 5,
                passPreference = 7,
                carryPreference = 92,
                kickPreference = 1
            ), Player(
                shirtNo = 7,
                position = Position.OPEN_SIDE_FLANKER,
                bodyHeight = 180,
                bodyWeight = 100,
                speed = 5,
                passPreference = 8,
                carryPreference = 91,
                kickPreference = 1
            ), Player(
                shirtNo = 8,
                position = Position.NUMBER_EIGHT,
                bodyHeight = 185,
                bodyWeight = 105,
                speed = 6,
                passPreference = 10,
                carryPreference = 89,
                kickPreference = 1
            ), Player(
                shirtNo = 9,
                position = Position.SCRUMHALF,
                bodyHeight = 175,
                bodyWeight = 75,
                speed = 7,
                passPreference = 90,
                carryPreference = 2,
                kickPreference = 8
            ), Player(
                shirtNo = 10,
                position = Position.FLYHALF,
                bodyHeight = 180,
                bodyWeight = 80,
                speed = 7,
                passPreference = 85,
                carryPreference = 3,
                kickPreference = 12,
                kickerFlag = true,
                shotSuccessRatio = 85
            ), Player(
                shirtNo = 11,
                position = Position.BLIND_SIDE_WINGER,
                bodyHeight = 176,
                bodyWeight = 80,
                speed = 10,
                passPreference = 10,
                carryPreference = 87,
                kickPreference = 3
            ), Player(
                shirtNo = 12,
                position = Position.INSIDE_CENTER,
                bodyHeight = 180,
                bodyWeight = 90,
                speed = 9,
                passPreference = 30,
                carryPreference = 65,
                kickPreference = 5
            ), Player(
                shirtNo = 13,
                position = Position.OUTSIDE_CENTER,
                bodyHeight = 180,
                bodyWeight = 90,
                speed = 9,
                passPreference = 40,
                carryPreference = 55,
                kickPreference = 5
            ), Player(
                shirtNo = 14,
                position = Position.OPEN_SIDE_WINGER,
                bodyHeight = 180,
                bodyWeight = 80,
                speed = 10,
                passPreference = 10,
                carryPreference = 87,
                kickPreference = 3
            ), Player(
                shirtNo = 15,
                position = Position.FULLBACK,
                bodyHeight = 190,
                bodyWeight = 85,
                speed = 10,
                passPreference = 20,
                carryPreference = 60,
                kickPreference = 20
            )
        )
        var team2PlayerList: List<Player> = team1PlayerList
    }
}