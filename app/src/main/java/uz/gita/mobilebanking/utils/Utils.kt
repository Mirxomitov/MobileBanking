package uz.gita.mobilebanking.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import timber.log.Timber
import uz.gita.mobilebanking.utils.biometric.BiometricAuthenticator

fun logger(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}


fun <T> List<T>.new(): MutableList<T> {
    return mutableListOf<T>().also { it.addAll(this) }
}

fun String.hidePartOfNumber(): String {

    if (this.length == 9) {
        return "+998 ${this.substring(0, 2)} ••• •• ${this.substring(7, 9)}"
    }

    return this
}

fun Context.vibrate(duration: Long) {
    val vibrator = ContextCompat.getSystemService(this, Vibrator::class.java)

    if (
        vibrator != null
        && vibrator.hasVibrator()
        && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    ) {

        val vibrationEffect = VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(vibrationEffect)

    }
}

fun Context.requireBiometricAuth(
    onSuccess: (result: BiometricPrompt.AuthenticationResult) -> Unit,
    onFailed: () -> Unit,
    onError: (errorCode: Int, errString: CharSequence) -> Unit
) {
    val biometricAuthenticator = BiometricAuthenticator(this)

    biometricAuthenticator.promptBiometricAuth(
        title = "Barmoq izi bo'yicha avtorizatsiya",
        subTitle = "Verify identity\nTouch ID",
        negativeButtonText = "BEKOR QILISH",
        fragmentActivity = this as FragmentActivity,
        onSuccess = onSuccess,
        onError = onError,
        onFailed = onFailed,
        )
}