package uz.gita.mobilebanking.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.presentation.profile.components.ItemInfo
import uz.gita.mobilebanking.presentation.profile.components.MyInformation
import uz.gita.mobilebanking.presentation.profile.components.Support
import uz.gita.mobilebanking.presentation.profile.components.UsefulInformation
import uz.gita.mobilebanking.presentation.profile.components.UserInfo
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.mainBgLight

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ProfileContract.Model = getViewModel<ProfileModel>()

        MobileBankingTheme() {
            ProfileContent(viewModel::onEventDispatchers)
        }
    }
}

@Composable
private fun ProfileContent(
    onEventDispatcher: (ProfileContract.Intent) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(mainBgLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    onEventDispatcher(ProfileContract.Intent.Back)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "back icon",
                    modifier = Modifier.size(24.dp)
                )
            }

            TextBold(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(R.string.profile),
                fontSize = 24.sp,
                color = Color.Black
            )

        }
        Column(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            UserInfo()
            MyInformation(modifier = Modifier.padding(top = 12.dp))
            Support(modifier = Modifier.padding(top = 12.dp))
            UsefulInformation(modifier = Modifier.padding(top = 12.dp))
            ItemInfo(
                modifier = Modifier.padding(top = 12.dp),
                icon = R.drawable.ic_settings,
                text = stringResource(R.string.application_settings)
            )
        }
    }
}

@Preview
@Composable
fun ProfileContentPreview() {
    ProfileContent({})
}

/*
class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        PaynetTheme(statusBarColor = BackgroundLight) {
            val screenViewModel: ProfileContract.Model = getViewModel<ProfileViewModel>()
            val uiState = screenViewModel.collectAsState().value

            ProfileScreenContent(
                uiState = uiState,
                screenViewModel::onEventDispatcher
            )


            val bottomSheetNavigator = LocalBottomSheetNavigator.current
            screenViewModel.onEventDispatcher(ProfileContract.Intent.GetUserPhone)
            screenViewModel.collectSideEffect { sideEffect ->
                when (sideEffect) {
                    ProfileContract.SideEffect.OpenAboutDialog -> {
                        "ProfileScreen sideEffect -> OpenAboutDialog".myLog()
                        bottomSheetNavigator.show(
                            AboutDialog()
                        )
                    }

                    ProfileContract.SideEffect.OpenOffersDialog -> {
//                        bottomSheetNavigator.show(
//                            ReferenceDialog()
//                        )
                    }

                    ProfileContract.SideEffect.OpenRateDialog -> {
//                        bottomSheetNavigator.show(
//                            RateDialog()
//                        )
                    }
                }
            }

        }
    }

    @Composable
    fun ProfileScreenContent(
        uiState: ProfileContract.UIState,
        onEventDispatcher: (ProfileContract.Intent) -> Unit,
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLight)
        ) {
            TopSection(onClickBack = {
                onEventDispatcher.invoke(ProfileContract.Intent.Back)
            })

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundLight)
                    .verticalScroll(enabled = true, state = scrollState)
            ) {
                StatusSection()
                SupportSection()
                InfoSection(
                    onClickAbout = {
                        "ProfileScreenContent onClickAbout".myLog()
                        onEventDispatcher.invoke(ProfileContract.Intent.OpenAboutDialog)
                    },
                    onClickInformation = {}
                )
                SettingsSection()
                RankingsSection()
                LogOutSection()
            }
        }
    }


    @Composable
    fun TopSection(
        modifier: Modifier = Modifier, onClickBack: () -> Unit
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = modifier
        ) {
            Icon(painter = painterResource(id = R.drawable.navigation_arrow_left),
                contentDescription = null,
                tint = Gray70,
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .clickable { onClickBack.invoke() })

            Text(
                stringResource(id = R.string.profile),
                fontSize = 20.sp,
                color = textColorLight,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            )
        }
    }

    @Composable
    fun StatusSection(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
                .padding(horizontal = 12.dp),
        ) {
            Text(
                stringResource(id = R.string._2006),
                color = textColorLight,
                fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                fontSize = 18.sp,
                modifier = Modifier.padding(
                    top = 24.dp, start = 16.dp, end = 16.dp
                )
            )


            StatusEventSection()

            IdentifyButton()
        }
    }


    @Composable
    fun StatusEventSection(modifier: Modifier = Modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 14.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.White)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
                .padding(6.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sad_emoji),
                contentDescription = null,
                modifier = Modifier
                    .padding(6.dp)
                    .size(34.dp)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = AbsoluteAlignment.Left,
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    stringResource(id = R.string.your_status),
                    color = Gray70,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_medium))
                )

                Text(
                    stringResource(id = R.string.anonim),
                    color = textColorLight,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                )
            }
        }
    }


    @Composable
    fun IdentifyButton(modifier: Modifier = Modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .padding(top = 22.dp, bottom = 12.dp)
                .fillMaxWidth()
                .height(46.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xff01a827))
        ) {
            Text(
                stringResource(id = R.string.identity_verification),
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_medium)),
            )
        }
    }


    @Composable
    fun SupportSection(modifier: Modifier = Modifier) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 14.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
        ) {
            Text(
                stringResource(id = R.string.support),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                color = textColorLight80,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp)
            )


            ProfileCategorySection(
                imageRes = R.drawable.ic_operations_comment,
                stringRes = R.string.chat_helpter,
                modifier = Modifier.padding(top = 8.dp, start = 12.dp)
            )

            ProfileCategorySection(
                imageRes = R.drawable.ic_action_phone_alt,
                stringRes = R.string.call,
                modifier = Modifier.padding(top = 24.dp, start = 12.dp)
            )

            ProfileCategorySection(
                imageRes = R.drawable.ic_action_mail,
                stringRes = R.string.write_mail,
                modifier = Modifier.padding(top = 24.dp, start = 12.dp, bottom = 14.dp)
            )
        }
    }


    @Composable
    fun InfoSection(
        modifier: Modifier = Modifier,
        onClickAbout: () -> Unit,
        onClickInformation: () -> Unit,
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
        ) {
            Text(
                stringResource(id = R.string.useful_information),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                color = textColorLight80,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp)
            )


            ProfileCategorySection(
                imageRes = R.drawable.ic_action_info,
                stringRes = R.string.about_paynet,
                modifier = Modifier
                    .padding(top = 14.dp, start = 12.dp)
                    .height(20.dp),
                onClick = {
                    "ProfileCategorySection info click".myLog()
                    onClickAbout.invoke()
                }
            )

            ProfileCategorySection(
                imageRes = R.drawable.ic_action_question,
                stringRes = R.string.reference,
                modifier = Modifier.padding(top = 26.dp, start = 12.dp, bottom = 12.dp)
            )
        }
    }


    @Composable
    fun SettingsSection(modifier: Modifier = Modifier) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
        ) {

            ProfileCategorySection(
                imageRes = R.drawable.ic_actions_settings,
                stringRes = R.string.app_settings,
                modifier = Modifier.padding(top = 12.dp, start = 12.dp, bottom = 12.dp)
            )
        }
    }


    @Composable
    fun RankingsSection(modifier: Modifier = Modifier) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 14.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
        ) {

            ProfileCategorySection(
                imageRes = R.drawable.ic_operations_star_v2,
                stringRes = R.string.rank_to_app,
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, bottom = 14.dp)
                    .height(22.dp)
            )
        }
    }

    @Composable
    fun LogOutSection(
        modifier: Modifier = Modifier, onClick: (() -> Unit)? = null
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 24.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundLight)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .wrapContentSize()
                    .padding(start = 16.dp, end = 16.dp, top = 14.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onClick?.invoke() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_action_sign_in),
                    contentDescription = null,
                    tint = Red,
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    stringResource(id = R.string.log_out_profile),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }

    @SuppressLint("ResourceType")
    @Composable
    fun ProfileCategorySection(
        modifier: Modifier = Modifier,
        onClick: (() -> Unit)? = null,
        imageRes: Int,
        stringRes: Int,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { onClick?.invoke() }) {
            Icon(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                tint = Color(0xff6E7279),
                modifier = Modifier.size(24.dp)
            )

            Text(
                stringResource(id = stringRes),
                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                color = textColorLight90,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 12.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_right_x24),
                contentDescription = null,
                tint = Color(0xff3c4553),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(20.dp)
            )
        }
    }


    @Composable
    @Preview(showBackground = true)
    fun ProfileScreenContentPreview() {
        PaynetTheme {
            val screenViewModel: ProfileContract.Model = getViewModel<ProfileViewModel>()
            val uiState = screenViewModel.collectAsState().value
//           ProfileScreenContent(screenViewModel::onEventDispatcher, uiState)
        }
    }


}

class AboutDialog : Screen {
    @Composable
    override fun Content() {
        AboutDialogComponent()
    }

    @Composable
    fun AboutDialogComponent() {
        Text(
            text = stringResource(id = R.string.app_name), color = textColorLight90, fontSize = 28.sp
        )
    }
}

 */
