package com.panasetskaia.countrieswithcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.panasetskaia.countrieswithcompose.R

val MarkPro = FontFamily(Font(R.font.mark_pro))

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = MarkPro,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = MarkPro,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = MarkPro,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)