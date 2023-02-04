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
    val speed: Int,
    // パス選好度合い
    val passPreference: Int,
    // キャリー選好度合い
    val carryPreference: Int,
    // キック選好度合い
    val kickPreference: Int,
    // ショット成功率
    var shotSuccessRatio: Int? = null,
    // キッカーかどうか
    var kickerFlag: Boolean = false
) {

    init {
        when (position) {
            Position.LOOSE_HEAD_PROP, Position.HOOKER, Position.TIGHT_HEAD_PROP, Position.LEFT_LOCK, Position.RIGHT_LOCK -> {
                positionCategory1 = PositionCategory1.FORWARDS
                positionCategory2 = PositionCategory2.TIGHT_FIVE
            }

            Position.BLIND_SIDE_FLANKER, Position.OPEN_SIDE_FLANKER, Position.NUMBER_EIGHT -> {
                positionCategory1 = PositionCategory1.FORWARDS
                positionCategory2 = PositionCategory2.LOOSE_FORWARDS
            }

            Position.SCRUMHALF, Position.FLYHALF, Position.INSIDE_CENTER, Position.OUTSIDE_CENTER -> {
                positionCategory1 = PositionCategory1.BACKS
                positionCategory2 = PositionCategory2.INSIDE_BACKS
            }

            Position.BLIND_SIDE_WINGER, Position.OPEN_SIDE_WINGER, Position.FULLBACK -> {
                positionCategory1 = PositionCategory1.BACKS
                positionCategory2 = PositionCategory2.OUTSIDE_BACKS
            }
        }
    }
}

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
