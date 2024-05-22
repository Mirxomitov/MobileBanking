package uz.gita.mobilebanking.ui.dialogs

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.DialogTopLine
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.AuthComponentBg
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.PrimaryColor
import uz.gita.mobilebanking.ui.theme.TextColorLight
import uz.gita.mobilebanking.ui.theme.TextColor

data class CameraPermissionRationaleDialog(val onHide: () -> Unit) : Screen {
    @Composable
    override fun Content() {
        CameraPermissionRationaleDialogContent(onHide)
    }
}

@Composable
fun CameraPermissionRationaleDialogContent(onHide: () -> Unit) {

    val context = LocalContext.current

    Column(
        Modifier
            .background(CardColor)
            .padding(4.dp)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DialogTopLine(
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier
                .align(Alignment.End)
                .size(32.dp)
                .clip(CircleShape)
                .background(AuthComponentBg)
                .clickable { onHide() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                Modifier
                    .size(12.dp)
                    .align(Alignment.Center),
                tint = TextColor
            )
        }

        TextBoldBlack(
            text = stringResource(R.string.to_scanner_card_you_must_give_permission),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        TextNormal(
            text = stringResource(R.string.you_can_go_to_settings),
            fontSize = 16.sp,
            color = TextColorLight,
            modifier = Modifier.padding(top = 16.dp)
        )

        Button(
            modifier = Modifier
                .padding(top = 24.dp, bottom = 6.dp)
                .fillMaxWidth(),
            onClick = {
                onHide()
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", context.packageName, null)
                )
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor,
            )
        ) {
            TextBold(
                text = "Sozlamalarga o'tish",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun CameraPermissionRationaleDialogPreview() {
    CameraPermissionRationaleDialogContent {}
}