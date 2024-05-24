package uz.gita.mobilebanking.data.model

import androidx.annotation.DrawableRes
import uz.gita.mobilebanking.R

data class TemplateData(
    val id: Int,
    @DrawableRes val icon: Int = R.drawable.logo_tbc,
    val title: String,
)