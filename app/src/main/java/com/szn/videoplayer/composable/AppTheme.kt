package com.szn.app.ui.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LightColors = lightColors(
    background = Color.LightGray,
    onBackground = Color.Black,
    primary = Color(0xFF1FBD59),
    primaryVariant = Color.Blue,
    secondary = Color(0xFFE78D37),
    surface = Color.Black
)

private val DarkColors = darkColors(
    background = Color.Black,
    onBackground = Color.Black,
    primary = Color(0xFF1FBD59),
    primaryVariant = Color(0xFF218B48),
    secondary = Color.Yellow,
    surface = Color.White,
)

val Colors.textColor: Color
    get() = if (isLight) Color.Black else Color.White

private val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)

private val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}