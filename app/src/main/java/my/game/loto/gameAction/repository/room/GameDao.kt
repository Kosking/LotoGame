package my.game.loto.gameAction.repository.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData

@Dao
abstract class GameDao {

    @Query("SELECT * FROM play_object WHERE id = :numberOfPlayers")
    abstract fun getListPlayObjects(numberOfPlayers: IntArray): List<PlayObject>

    @Query("SELECT * FROM full_game_object WHERE id = 0")
    abstract fun getFullGameObject(): FullGameObject

    @Insert(onConflict = REPLACE)
    abstract fun setPrimaryData(primaryData: PrimaryData )
}