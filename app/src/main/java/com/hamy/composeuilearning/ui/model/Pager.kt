package com.hamy.composeuilearning.ui.model

import androidx.annotation.DrawableRes
import com.hamy.composeuilearning.R

data class Pager(
    @DrawableRes val image:Int,
    val description:String
)

val listDataPager = listOf<Pager>(
    Pager(R.drawable.one,"pager 1"),
    Pager(R.drawable.two,"pager 2"),
    Pager(R.drawable.thre,"pager 3")
)
