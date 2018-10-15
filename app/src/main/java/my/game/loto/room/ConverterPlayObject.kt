package my.game.loto.room

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterPlayObject {

    @TypeConverter
    fun fromIdsCards(value: String): IntArray {
        val intType = object : TypeToken<IntArray>() {}.type
        return Gson().fromJson(value, intType)
    }

    @TypeConverter
    fun toIdsCards(idsCards: IntArray): String {
        return Gson().toJson(idsCards)
    }
}