package uz.gita.mobilebanking.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.theme.errorColor2


@Composable
fun LogOutButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Transparent,
            contentColor = errorColor2,
        ),
        modifier = modifier
            .padding(top = 12.dp)
            .height(56.dp)
            .clickable { },
        shape = RoundedCornerShape(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logout),
            modifier = Modifier.size(24.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(horizontal = 12.dp))
        TextBold(
            letterSpacing = 0.8.sp,
            color = errorColor2,
            fontSize = 16.sp,
            text = stringResource(R.string.log_out_profile)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LogOutPreview() {
    LogOutButton(
        Modifier.fillMaxWidth(), {}
    )
}