package my.game.loto.gameAction.repository

import android.support.annotation.VisibleForTesting

object GameProvider{

    private lateinit var myGameObject: GameObject
    private lateinit var myGamingRepository: GamingRepository

    var gameObject: GameObject
        get() {
            if (myGameObject == null){
                myGameObject = GameObject
            }
            return myGameObject
        }
        @VisibleForTesting(otherwise = VisibleForTesting.NONE)
        set(value) {
            myGameObject = value
        }

    var gamingRepository: GamingRepository
        get() {
            if (myGamingRepository == null){
                myGamingRepository = GamingRepository
            }
            return myGamingRepository
        }
        @VisibleForTesting(otherwise = VisibleForTesting.NONE)
        set(value) {
            myGamingRepository = value
        }
}