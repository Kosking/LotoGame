package my.game.loto.roomDatabase

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import my.game.loto.choiceAction.repository.room.ChoiceDao
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.gameAction.repository.room.GameDao
import my.game.loto.initialAction.repository.room.InitialDao
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData

@Database(entities = arrayOf(PlayObject::class,
        PrimaryData::class,
        FullGameObject::class),
        version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun initialDao(): InitialDao
    abstract fun choiceDao(): ChoiceDao
    abstract fun gameDao(): GameDao
}