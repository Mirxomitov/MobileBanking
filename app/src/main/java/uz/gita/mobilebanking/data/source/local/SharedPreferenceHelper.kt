package uz.gita.mobilebanking.data.source.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceHelper @Inject constructor(
    private val sharedPreference: SharedPreferences
) {
    private companion object {
        const val PHONE_NUMBER = "PHONE_NUMBER"
        const val LANGUAGE = "LANGUAGE"
        const val PIN_CODE = "PIN_CODE"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val IS_MONEY_VISIBLE = "IS_MONEY_VISIBLE"
    }

    fun phoneNumber(phoneNumber: String) = sharedPreference.edit().putString(PHONE_NUMBER, phoneNumber).apply()
    fun phoneNumber(): String = sharedPreference.getString(PHONE_NUMBER, "NO NUMBER").toString()
   //
    fun saveActiveLanguage(isUzbek: Boolean) =    sharedPreference.edit().putBoolean(LANGUAGE, isUzbek).apply()
    fun isLanguageUzbek(): Boolean = sharedPreference.getBoolean(LANGUAGE, true)
    fun signed(): Boolean = sharedPreference.getString(PIN_CODE, "") != ""
    //
    fun pinCode(pinCode: String) = sharedPreference.edit().putString(PIN_CODE, pinCode).apply()
    fun pinCode(): String = sharedPreference.getString(PIN_CODE, "").toString()

    //
    fun refreshToken(refreshToken: String) = sharedPreference.edit().putString(REFRESH_TOKEN, refreshToken).apply()
    fun refreshToken(): String = sharedPreference.getString(REFRESH_TOKEN, "").toString()

    //
    fun accessToken(accessToken: String) = sharedPreference.edit().putString(ACCESS_TOKEN, accessToken).apply()
    fun accessToken(): String = sharedPreference.getString(ACCESS_TOKEN, "").toString()

    //
    fun clear() = sharedPreference.edit().clear().apply()

    //
    fun isMoneyVisible(isVisible: Boolean) = sharedPreference.edit().putBoolean(IS_MONEY_VISIBLE, isVisible).apply()
    fun isMoneyVisible(): Boolean = sharedPreference.getBoolean(IS_MONEY_VISIBLE, true)
}
