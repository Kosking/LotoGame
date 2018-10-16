package my.game.loto.room

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import my.game.loto.initialAction.retrofit.settingsObjects.OtherPlayers


class ConverterFullGameObject {

    @TypeConverter
    fun fromOtherPlayersList(value: String): List<OtherPlayers> {
        val listType = object : TypeToken<List<OtherPlayers>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toOtherPlayersList(otherPlayersList: List<OtherPlayers>): String {
        return Gson().toJson(otherPlayersList)
    }

    @TypeConverter
    fun fromString(value: String): Array<String> {
        val listType = object : TypeToken<Array<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: Array<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromArrayInt(value: String): IntArray {
        val type = object : TypeToken<IntArray>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toArrayInt(value: IntArray): String {
        return Gson().toJson(value)
    }
}