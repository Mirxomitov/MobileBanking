package uz.gita.mobilebanking.presentation.addcard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.ShadowColorCard


@Composable
fun AddCardButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                ambientColor = ShadowColorCard
            )
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            enabled = isEnabled,
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFFB1E0B2),
                disabledContentColor = Color.White,
            )
        ) {
            TextNormal(
                modifier = Modifier.padding(6.dp),
                text = stringResource(id = R.string.txt_continue),
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}