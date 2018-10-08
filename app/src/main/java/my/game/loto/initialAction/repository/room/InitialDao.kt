package my.game.loto.initialAction.repository.room

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData

abstract class InitialDao {

    @Insert(onConflict = REPLACE)
    abstract fun setPrimaryData(primaryData: PrimaryData)

    //TODO server must send FullGameObject id = 0
    @Insert(onConflict = REPLACE)
    abstract fun setFullGameObject(fullGameObject: FullGameObject)
}