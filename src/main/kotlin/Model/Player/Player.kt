package Model.Player

data class Player(
    // 背番号
    val shirtNo: Int,
    // ポジション
    val position: Position,
    // ポジション分類1
    var positionCategory1: PositionCategory1? = null,
    // ポジション分類2
    var positionCategory2: PositionCategory2? = null,
    // 身長
    val bodyHeight: Int,
    // 体重
    val bodyWeight: Int,
    // スピード(10段階評価)
    val speed: Int
)

enum class Position {
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

enum class PositionCategory1 {
    FORWARDS, BACKS
}

enum class PositionCategory2 {
    TIGHT_FIVE,
    LOOSE_FORWARDS,
    INSIDE_BACKS,
    OUTSIDE_BACKS
}
