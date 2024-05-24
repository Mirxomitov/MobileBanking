package uz.gita.mobilebanking.ui.dialogs

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.MarkerData
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormalBlack

data class LocationDialog(val data: MarkerData, val onHide: () -> Unit) : Screen {
    @Composable
    override fun Content() {
        LocationDialogContent(data = data, onHide = onHide)
    }
}

@Composable
fun LocationDialogContent(data: MarkerData, onHide: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = data.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_close), contentDescription = null,
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(18.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            onHide()
                        }
                    ),
                tint = White,
            )
        }

        TextBoldBlack(
            textAlign = TextAlign.Center,
            text = data.titleBank,
            fontSize = 24.sp,
            color = Black,
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )

        Divider(modifier = Modifier.padding(vertical = 12.dp), color = Black, thickness = 1.dp)


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .padding(horizontal = 12.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onHide()
                        val uri = Uri.parse("geo:${data.lat},${data.lng}?z=${12}")
                        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                        mapIntent
                            .resolveActivity(context.packageManager)
                            ?.let {
                                startActivity(
                                    context,
                                    Intent.createChooser(mapIntent, "Открыть с помощью"),
                                    null
                                )
                            }

                    }
                )
        ) {
            Icon(painter = rememberVectorPainter(image = Icons.Default.LocationOn), contentDescription = null)

            TextNormalBlack(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                text = data.textLocation,
                fontSize = 14.sp,
                color = Black,
                textAlign = TextAlign.Justify
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp, bottom = 24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onHide()
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            this.data = Uri.parse("tel:${data.textTelephoneNumber}")
                        }

                        context.startActivity(intent)
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = rememberVectorPainter(image = Icons.Default.Call), contentDescription = null)

            TextNormalBlack(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                text = data.textTelephoneNumber,
                fontSize = 14.sp,
                color = Black,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    LocationDialogContent(
        data =
        MarkerData(
            lat = 41.31904027624511, 69.24142262507962,
            image = R.drawable.image_ooo_uzpaynet,
            titleBank = "\"OOO\"UZPAYNET",
            titleBankInfo = "Kompaniya ofisi",
            textLocation = "Furqat ko'chasi 10, 100021, Тоshkent, Toshkent, Узбекистан",
        ),
        onHide = {}
    )
}