package Service.OpenPlay

import Model.Descriptor.RESULT
import Model.Team.Team

class Ruck {
    companion object {
        //TODO 変数を増やしてラックのリアリティを上げる
        private val RUCK_WON_RATE = 90
        private val DEF_PEN_WON_RATE = 40
        fun getResult(AttTeam: Team, DefTeam: Team): RESULT {
            return if ((1..100).random() > RUCK_WON_RATE) {
                return if ((1..100).random() < DefTeam.discipline) {
                    RESULT.WON_PEN
                } else {
                    return if ((1..100).random() < DEF_PEN_WON_RATE) {
                        RESULT.LOST_PEN
                    } else {
                        RESULT.LOST
                    }
                }
            } else {
                RESULT.WON
            }
        }
    }
}