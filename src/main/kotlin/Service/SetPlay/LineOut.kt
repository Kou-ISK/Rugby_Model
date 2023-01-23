package Service.SetPlay

import Model.Player.Player
import Model.Player.Position
import Model.Player.PositionCategory1
import Model.Player.PositionCategory2
import Model.Team.Team

class LineOut {
    companion object {
        const val JUMP_AREA = 5
    }

    // TODO 変数が増え次第内容を追加
    private fun LineOut(AttTeam: Team, DefTeam: Team): RESULT {
        return if (JumpArea(AttTeam) == JumpArea(DefTeam)) {
            RESULT.LOST
        } else {
            RESULT.WON
        }
    }

    // 乱数でジャンプ位置を決定する
    private fun JumpArea(Team: Team): Int {
        return (1..JUMP_AREA).random()
    }

    // LineOut参加メンバーを決定する
    private fun setMember(Team:Team):List<Player> {
        // サンプルのメンバーリスト
        val lineOutMembers: List<Player>?
        lineOutMembers = listOf(Player(1,
            Position.TIGHT_HEAD_PROP, PositionCategory1.FORWARDS, PositionCategory2.TIGHT_FIVE, 180, 110, 100),
        Player(2, Position.LOOSE_HEAD_PROP, PositionCategory1.FORWARDS, PositionCategory2.TIGHT_FIVE, 175, 100, 100))
        // TODO Team.playerListからpositionCategory=FORWARDSのものを選ぶ(PROPは最初と最後に追加)
        return lineOutMembers
    }
}

enum class RESULT{
    WON,LOST,WON_PEN,LOST_PEN,WON_FK,LOST_FK,WON_SCRUM,LOST_SCRUM
}