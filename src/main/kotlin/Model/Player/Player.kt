package Model.Player

data class Player (
    private val shirtNo:Int,
    private val position:Position,
    private val positionCategory1: PositionCategory1,
    private val positionCategory2: PositionCategory2,
    private val bodyHeight:Int,
    private val bodyWeight:Int,
    private val speed:Int
    )

enum class Position{
    LOOSE_HEAD_PROP,
    HOOKER,
    TIGHT_HEAD_PROP,
    LEFT_LOCK,
    RIGHT_LOCK,
    BLIND_SIDE_FLANKER,
    OPEN_SIDE_FLANKER,
    NUMBER_EIGHT,
    SCRUMHALF,
    FLYHALF,
    INSIDE_CENTER,
    OUTSIDE_CENTER,
    BLIND_SIDE_WINGER,
    OPEN_SIDE_WINGER,
    FULLBACK
}

enum class PositionCategory1{
    FORWARDS,BACKS
}

enum class PositionCategory2{
    TIGHT_FIVE,
    LOOSE_FORWARDS,
    INSIDE_BACKS,
    OUTSIDE_BACKS
}
