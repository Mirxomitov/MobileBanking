package uz.gita.mobilebanking.data.source.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceHelper @Inject constructor(
    private val sharedPreference: SharedPreferences
) {

    companion object {
        private const val PHONE_NUMBER = "PHONE_NUMBER"
        private const val LANGUAGE = "LANGUAGE"
    }

    fun phoneNumber(phoneNumber: String) {
        sharedPreference.edit().putString(PHONE_NUMBER, phoneNumber).apply()
    }

    fun phoneNumber(): String {
        return sharedPreference.getString(PHONE_NUMBER, "NO NUMBER").toString()
    }

    fun saveActiveLanguage(isUzbek: Boolean) {
        sharedPreference.edit().putBoolean(LANGUAGE, isUzbek).apply()
    }

    fun isLanguageUzbek(): Boolean = sharedPreference.getBoolean(LANGUAGE, true)
}
