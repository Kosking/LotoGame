package my.game.loto.gameAction.repository

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
        set(value) {
            myGamingRepository = value
        }
}