package Service.Setting

import Model.Player.Position
import Model.Player.PositionCategory1
import Model.Player.PositionCategory2
import Model.Team.Team

class PlayerSetting {
    companion object {
        fun teamPreparation(Team: Team) {
            Team.playerList.filter {
                it.position == Position.LOOSE_HEAD_PROP || it.position == Position.HOOKER ||
                        it.position == Position.TIGHT_HEAD_PROP || it.position == Position.LEFT_LOCK ||
                        it.position == Position.RIGHT_LOCK
            }.map { player ->
                player.positionCategory1 = PositionCategory1.FORWARDS
                player.positionCategory2 = PositionCategory2.TIGHT_FIVE
            }
            Team.playerList.filter {
                it.position == Position.BLIND_SIDE_FLANKER || it.position == Position.OPEN_SIDE_FLANKER ||
                        it.position == Position.NUMBER_EIGHT
            }.map { player ->
                player.positionCategory1 = PositionCategory1.FORWARDS
                player.positionCategory2 = PositionCategory2.LOOSE_FORWARDS
            }
            Team.playerList.filter {
                it.position == Position.SCRUMHALF || it.position == Position.FLYHALF ||
                        it.position == Position.INSIDE_CENTER || it.position == Position.OUTSIDE_CENTER
            }.map { player ->
                player.positionCategory1 = PositionCategory1.BACKS
                player.positionCategory2 = PositionCategory2.INSIDE_BACKS
            }
            Team.playerList.filter {
                it.position == Position.BLIND_SIDE_WINGER || it.position == Position.OPEN_SIDE_WINGER ||
                        it.position == Position.FULLBACK
            }.map { player ->
                player.positionCategory1 = PositionCategory1.BACKS
                player.positionCategory2 = PositionCategory2.OUTSIDE_BACKS
            }
        }
    }
}