@file:Suppress("DEPRECATION")

package uz.gita.mobilebanking.presentation.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import uz.gita.mobilebanking.R

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<SplashModel>()
        SplashContent(viewModel::onEventDispatcher)
    }
}

@Composable
private fun SplashContent(
    onEventDispatcher: (SplashContract.Intent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val preloaderLottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))

        val preloaderProgress by animateLottieCompositionAsState(
            preloaderLottieComposition,
            isPlaying = true
        )

        LottieAnimation(
            composition = preloaderLottieComposition,
            progress = preloaderProgress,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LaunchedEffect(preloaderProgress) {
            if (preloaderProgress == 1f) {
                onEventDispatcher(SplashContract.Intent.NextScreen)
            }
        }

    }
}

@Preview
@Composable
private fun SplashPreview() {
    SplashContent({})
}