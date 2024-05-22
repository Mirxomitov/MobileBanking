package uz.gita.mobilebanking.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.ui.FullInfoData
import uz.gita.mobilebanking.presentation.profile.components.ItemInfo
import uz.gita.mobilebanking.presentation.profile.components.LogOutButton
import uz.gita.mobilebanking.presentation.profile.components.MyInformation
import uz.gita.mobilebanking.presentation.profile.components.Support
import uz.gita.mobilebanking.presentation.profile.components.UsefulInformation
import uz.gita.mobilebanking.presentation.profile.components.UserInfo
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.dialogs.PersonalInformation
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.profileBgLight
import uz.gita.mobilebanking.utils.previewStateOf

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ProfileContract.Model = getViewModel<ProfileModel>()

        MobileBankingTheme {
            ProfileContent(
                viewModel.collectAsState(),
                viewModel::onEventDispatchers
            )
        }
    }
}

@Composable
private fun ProfileContent(
    uiState: State<ProfileContract.UIState>,
    onEventDispatcher: (ProfileContract.Intent) -> Unit
) {

    val bottomSheetNavigator = LocalBottomSheetNavigator.current

    Column(
        Modifier
            .fillMaxSize()
            .background(profileBgLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = {
                onEventDispatcher(ProfileContract.Intent.Back)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "back icon",
                    modifier = Modifier.size(24.dp)
                )
            }

            TextBold(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(R.string.profile),
                fontSize = 22.sp,
                color = Color.Black
            )

        }
        Column(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (uiState.value.fullInfoData != null)
                UserInfo(userData = uiState.value.fullInfoData!!)

            MyInformation(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clickable {
                        bottomSheetNavigator.show(
                            PersonalInformation(
                                onClickHide = { bottomSheetNavigator.hide() }
                            )
                        )
                    }
            )

            Support(modifier = Modifier.padding(top = 12.dp))

            UsefulInformation(
                modifier = Modifier.padding(top = 12.dp),
                onClickMap = {
                    onEventDispatcher(ProfileContract.Intent.ToMaps)
                }
            )

            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .shadow(
                        elevation = 2.dp, shape = RoundedCornerShape(16.dp), ambientColor = ShadowColorCard
                    )
                    .background(CardColor)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ItemInfo(
                    paddingIcon = 2.dp,
                    modifier = Modifier.padding(vertical = 4.dp),
                    icon = R.drawable.ic_settings,
                    text = stringResource(R.string.application_settings),
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .shadow(
                        elevation = 2.dp, shape = RoundedCornerShape(16.dp), ambientColor = ShadowColorCard
                    )
                    .background(CardColor)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ItemInfo(
                    paddingIcon = 2.dp,
                    modifier = Modifier.padding(vertical = 4.dp),
                    icon = R.drawable.ic_operations_star_v2,
                    text = stringResource(R.string.rate_application),
                )
            }

            LogOutButton(
                Modifier.fillMaxWidth(),
                onClick = {
                    onEventDispatcher(ProfileContract.Intent.LogOut)
                }
            )
        }
    }
}

@Preview
@Composable
fun ProfileContentPreview() {
    ProfileContent(previewStateOf(
        value = ProfileContract.UIState(
            FullInfoData(
                0L,
                "Tohir",
                0,
                "Mirxomitov",
                "+998903553620"
            )
        )
    ), {})
}

