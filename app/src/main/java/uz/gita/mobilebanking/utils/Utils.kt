package uz.gita.mobilebanking.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import timber.log.Timber
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.utils.biometric.BiometricAuthenticator
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

fun logger(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

fun String.toLog(msg: String = "", tag: String = "TTT") {
    Timber.tag(tag).d("%s%s", msg, this)
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

fun Modifier.angledGradientBackground(colors: List<Color>, degrees: Float) = this.then(
    drawBehind {
        val (x, y) = size
        val gamma = atan2(y, x)

        if (gamma == 0f || gamma == (PI / 2).toFloat()) {
            // degenerate rectangle
            return@drawBehind
        }

        val degreesNormalised = (degrees % 360).let { if (it < 0) it + 360 else it }

        val alpha = (degreesNormalised * PI / 180).toFloat()

        val gradientLength = when (alpha) {
            // ray from centre cuts the right edge of the rectangle
            in 0f..gamma, in (2 * PI - gamma)..2 * PI -> {
                x / cos(alpha)
            }
            // ray from centre cuts the top edge of the rectangle
            in gamma..(PI - gamma).toFloat() -> {
                y / sin(alpha)
            }
            // ray from centre cuts the left edge of the rectangle
            in (PI - gamma)..(PI + gamma) -> {
                x / -cos(alpha)
            }
            // ray from centre cuts the bottom edge of the rectangle
            in (PI + gamma)..(2 * PI - gamma) -> {
                y / -sin(alpha)
            }
            // default case (which shouldn't really happen)
            else -> hypot(x, y)
        }

        val centerOffsetX = cos(alpha) * gradientLength / 2
        val centerOffsetY = sin(alpha) * gradientLength / 2

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                // negative here so that 0 degrees is left -> right
                //and 90 degrees is top -> bottom
                start = Offset(center.x - centerOffsetX, center.y - centerOffsetY),
                end = Offset(center.x + centerOffsetX, center.y + centerOffsetY)
            ),
            size = size
        )
    }
)

fun String.checkExpirationDateValidation(): Boolean {
    try {
        if (this.substring(0, 2).toInt() > 12) return false

        val currentDate = Date()
        val dateFormat = SimpleDateFormat("MMyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)

        val monthsNow = formattedDate.substring(0, 2).toInt() + formattedDate.substring(2, 4).toInt() * 12
        val monthsUser = this.substring(0, 2).toInt() + this.substring(2, 4).toInt() * 12 // this is like a MM/YY
        return monthsUser >= monthsNow
    } catch (e: Exception) {
        return false
    }
}

fun String.containsOnlyNumbers(): Boolean {
    val regex = Regex("^\\d+\$")
    return this.matches(regex)
}

fun String.toPhoneNumber(): String {
    if (
        this.length != 13
        || !this.startsWith("+")
        || !this.substring(1, 13).containsOnlyNumbers()
    ) return this

    val sb = StringBuilder(this)
    listOf(4, 7, 11, 14).forEach { sb.insert(it, " ") }

    return sb.toString()
}


fun String.toValue(): String {
    if (this.length <= 3) return this

    val builder = StringBuilder(this)
    val length = builder.length

    for (i in (length - 3) downTo 1 step 3)
        builder.insert(i, " ")

    return builder.toString()
}

fun String.toFullMonth() =
    if (this.length == 1) "0$this" else this

fun CardData.toCardExpirationDate() = "${this.expiredMonth.toString().toFullMonth()} / ${this.expiredYear.toString().substring(2, 4)}"

@Composable
fun <T> previewStateOf(value: T) = remember { mutableStateOf(value) }

fun getGradient(type: Int) : Brush = when(type) {
    0 -> { Brush.verticalGradient(listOf(Color(0xFF0063B5), Color(0xFF00EBC8))) }
    1 -> { Brush.verticalGradient(listOf(Color(0xFF06693a), Color(0xFF20d970))) }
    2 -> { Brush.verticalGradient(listOf(Color(0xFF5b0a8a), Color(0xFFa9518d))) }
    3 -> { Brush.verticalGradient(listOf(Color(0xFF930709), Color(0xFFff9c63))) }
    4 -> { Brush.verticalGradient(listOf(Color(0xFF886e33), Color(0xFFffd645))) }
    5 -> { Brush.verticalGradient(listOf(Color(0xFF282a75), Color(0xFF009ffd))) }
    6 -> { Brush.verticalGradient(listOf(Color(0xFF191a1f), Color(0xFF55555f))) }
    7 -> { Brush.verticalGradient(listOf(Color(0xFF6c0f17), Color(0xFFbd1373))) }
    else -> { Brush.verticalGradient(listOf(Color(0xFFa95403), Color(0xFFecbe38))) }
}

fun getCardType(cardNumbers: String) : Int {
    return if (cardNumbers.startsWith("9860")) R.drawable.ic_paymentsystem_humo
    else if (cardNumbers.startsWith("8600")) R.drawable.ic_paymentsystem_uzcard
    else if (cardNumbers.startsWith("5440")) R.drawable.ic_paymentsystem_uzcard
    else if (cardNumbers.startsWith("5614")) R.drawable.ic_paymentsystem_uzcard
    else R.drawable.ic_paymentsystem_humo
}
