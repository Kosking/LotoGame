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
/*    @TypeConverter
    fun fromJsonFullObject(value: String): FullGameObject {
        val listType = object : TypeToken<FullGameObject>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJsonFullObject(fullGameObject: FullGameObject): String {
        return Gson().toJson(fullGameObject)
    }
    @TypeConverter
    fun fromJsonFullObject(value: String): FullGameObject {
        val listType = object : TypeToken<FullGameObject>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJsonFullObject(fullGameObject: FullGameObject): String {
        return Gson().toJson(fullGameObject)
    }
    @TypeConverter
    fun fromJsonFullObject(value: String): FullGameObject {
        val listType = object : TypeToken<FullGameObject>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJsonFullObject(fullGameObject: FullGameObject): String {
        return Gson().toJson(fullGameObject)
    }
    @TypeConverter
    fun fromJsonFullObject(value: String): FullGameObject {
        val listType = object : TypeToken<FullGameObject>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJsonFullObject(fullGameObject: FullGameObject): String {
        return Gson().toJson(fullGameObject)
    }*/
}