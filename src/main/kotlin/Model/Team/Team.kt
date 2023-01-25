package Model.Team

import Model.Player.Player

data class Team(
    // チーム名
    val teamName: String,
    // プレーヤーのリスト
    val playerList: List<Player>,
    // ポゼッション選好(1-100)　100ではキックを選択しない
    val possessionPreferences: Int,
    // ラインアウト獲得率(0-1)
    val lineOutWonRate: Float,
    // ラインアウト奪取率(0-1)
    val lineOutStealRate: Float,
    // スクラム獲得率(0-1)
    val scrumWonRate: Float,
    // スクラム奪取率(0-1)
    val scrumStealRate: Float,
    // 規律(0-1)　高いほどペナルティを起こす確率が上がる
    val discipline: Float
)