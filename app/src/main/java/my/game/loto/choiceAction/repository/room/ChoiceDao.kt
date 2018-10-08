package my.game.loto.choiceAction.repository.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData

@Dao
abstract class ChoiceDao {

    @Insert
    abstract fun insertPlayObjects(listPlayObject: List<PlayObject>)

    @Query("SELECT * FROM primary_data WHERE id = 0")
    abstract fun getPrimaryData(): PrimaryData
}