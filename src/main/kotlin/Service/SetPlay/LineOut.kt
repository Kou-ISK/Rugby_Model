package Service.SetPlay

import Model.Player.Player
import Model.Player.Position
import Model.Player.PositionCategory2
import Model.Team.Team

class LineOut {
    // TODO 変数が増え次第内容を追加
    companion object {
        var number = 1
        fun getResult(AttTeam: Team, DefTeam: Team): RESULT {
            setNumber()
            return if (jump(AttTeam) == jump(DefTeam) && (setMember(AttTeam)[jump(AttTeam)].bodyHeight <= setMember(
                    DefTeam
                )[jump(DefTeam)].bodyHeight)
            ) {
                RESULT.LOST
            } else {
                RESULT.WON
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
            // TODO Team.playerListからpositionCategory=FORWARDSのものを選ぶ(PROPは最初と最後に追加)
            return lineOutMembers
        }
    }

    enum class RESULT {
        WON, LOST, WON_PEN, LOST_PEN, WON_FK, LOST_FK, WON_SCRUM, LOST_SCRUM
    }
}