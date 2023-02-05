package Model.Team

import Model.Player.Player
import Model.Player.Position
import Model.Player.PositionCategory2

data class Team(
    // チーム名
    val teamName: String,
    // プレーヤーのリスト
    val playerList: List<Player>,
    // キック選好(0-5)
    val kickPreferences: Int,
    // ラインアウト獲得率(0-100)
    val lineOutWonRate: Int,
    // ラインアウト奪取率(0-100)
    val lineOutStealRate: Int,
    // スクラム獲得率(0-100)
    val scrumWonRate: Int,
    // スクラム奪取率(0-100)
    val scrumStealRate: Int,
    // 規律(0-100)　高いほどペナルティを起こす確率が上がる
    val discipline: Int,
    // PGを何メートル先から狙うか
    val penaltyGoalRange: Int,
    // PG選好(0-100)
    val penaltyGoalPreference: Int
) {
    fun getScrumHalf(): Player {
        return playerList.find { it.position == Position.SCRUMHALF }!!
    }

    fun getOutSideBacks(): Player {
        return playerList.filter { it.positionCategory2 == PositionCategory2.OUTSIDE_BACKS }.random()
    }

    // TODO キッカーが複数いる場合への対応
    fun getKicker(): Player {
        return playerList.find { it.kickerFlag }!!
    }
}