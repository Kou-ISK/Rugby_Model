package Service.SetPlay

import Model.Descriptor.GameDescriptor
import Model.Descriptor.GameDescriptor.Companion.setNum
import Model.Descriptor.RESULT
import Model.Descriptor.RESULT.*
import Model.Player.Player
import Model.Player.Position
import Model.Player.PositionCategory2
import Model.Team.Team
import Service.OpenPlay.OpenPlay

class LineOut {
    // TODO 変数が増え次第内容を追加
    companion object {
        var number = 1
    }

    fun getResult(attTeam: Team, defTeam: Team) {
        setNumber()
        setNum += 1
        val result: RESULT =
            if (jump(attTeam) == jump(defTeam) && (setMember(attTeam)[jump(attTeam)].bodyHeight <= setMember(
                    defTeam
                )[jump(defTeam)].bodyHeight)
            ) {
                LOST
            } else {
                WON
            }
        when (result) {
            WON -> OpenPlay().openPlay(attTeam, defTeam, attTeam.getScrumHalf())
            LOST -> {
                GameDescriptor.gainLine = 100 - GameDescriptor.gainLine
                OpenPlay().openPlay(defTeam, attTeam, defTeam.getScrumHalf())
            }

            WON_PEN -> TODO()
            LOST_PEN -> TODO()
            WON_FK -> TODO()
            LOST_FK -> TODO()
            WON_SCRUM -> TODO()
            LOST_SCRUM -> TODO()
            else -> {}
        }
    }

    // 乱数でジャンプ位置を決定する
    private fun jump(Team: Team): Int {
        // TODO チームのjumpの選好によって乱数の出現頻度をコントロールする（重みづけ乱数を取る関数を実装）
        return (1 until number + 2).random()
    }

    private fun setNumber() {
        number = (1..5).random()
    }

    // LineOut参加メンバーを決定する
    private fun setMember(Team: Team): List<Player> {
        // サンプルのメンバーリスト
        val lineOutMembers: List<Player>?
        val forwards =
            Team.playerList.filter { it.positionCategory2 == PositionCategory2.LOOSE_FORWARDS || it.position == Position.LEFT_LOCK || it.position == Position.RIGHT_LOCK }
        lineOutMembers =
            Team.playerList.filter { it.position == Position.LOOSE_HEAD_PROP } + forwards.take(number) + Team.playerList.filter { it.position == Position.TIGHT_HEAD_PROP }
        return lineOutMembers
    }
}
