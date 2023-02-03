package Model.Team

import Model.Player.Player
import Model.Player.Position

data class Team(
    // チーム名
    val teamName: String,
    // プレーヤーのリスト
    val playerList: List<Player>,
    // ポゼッション選好(1-100)　100ではキックを選択しない
    val possessionPreferences: Int,
    // ラインアウト獲得率(0-100)
    val lineOutWonRate: Int,
    // ラインアウト奪取率(0-100)
    val lineOutStealRate: Int,
    // スクラム獲得率(0-100)
    val scrumWonRate: Int,
    // スクラム奪取率(0-100)
    val scrumStealRate: Int,
    // 規律(0-100)　高いほどペナルティを起こす確率が上がる
    val discipline: Int
) {
    fun getScrumHalf(): Player {
        return playerList.find { it.position == Position.SCRUMHALF }!!
    }
}