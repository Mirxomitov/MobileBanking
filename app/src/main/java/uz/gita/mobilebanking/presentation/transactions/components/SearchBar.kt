package uz.gita.mobilebanking.presentation.transactions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.authComponentBg
import uz.gita.mobilebanking.ui.theme.grayColor

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: String,
    onClickContacts: () -> Unit,
    onClickScan: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(authComponentBg)
            .padding(top = 2.dp, bottom = 2.dp, start = 12.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextNormal(
                modifier = Modifier.padding(start = 4.dp),
                text = searchText,
                color = grayColor,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {
                    onClickContacts()
                }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_contacts),
                    contentDescription = null
                )
            }

            IconButton(
                onClick = {
                    onClickScan()
                }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_scan),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(
        searchText = "",
        onClickContacts = {},
        onClickScan = {}
    )
}