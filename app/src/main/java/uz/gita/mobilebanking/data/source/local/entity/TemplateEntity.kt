package uz.gita.mobilebanking.data.source.local.entity

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("template_table")
data class TemplateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @DrawableRes val icon: Int,
    val title: String,
)

/*
kartaga o'tkazish
 */