import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold

@Composable
fun ItemPay(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes iconID: Int,
    onClick: () -> Unit
) {
    Row(
        modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFECF0F2))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextBold(
            text = title,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(end = 4.dp),
        )

//        Icon(
//            modifier = Modifier.size(36.dp),
//            painter = painterResource(id = iconID),
//            contentDescription = null,
//        )
    }
}

@Preview
@Composable
fun ItemPayPreview() {
    ItemPay(
        title = "hello world",
        iconID = R.drawable.search_clear
    ) {

    }
}