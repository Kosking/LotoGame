package my.game.loto.gameAction.repository

import android.support.annotation.VisibleForTesting

object GameProvider{
    @Volatile
    private var myGameObject: GameObject? = null;
    @Volatile
    private var myGamingRepository: GamingRepository? = null

    var gameObject: GameObject?
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

    var gamingRepository: GamingRepository?
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

    fun init(){
        myGameObject = GameObject
        myGamingRepository = GamingRepository
    }
}