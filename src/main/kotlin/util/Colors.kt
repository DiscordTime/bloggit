package util

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

const val darkTheme = false

fun customDarkColors() = darkColors(
    primary = Color.White,
    primaryVariant = Color.White,
    secondary = Color.LightGray,
    background = Color.DarkGray,
    surface = Color.DarkGray,
    onPrimary = Color.DarkGray,
    onSecondary = Color.LightGray,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

fun customLightColors() = lightColors(
    //primary = Color(0x006200EE),
    primary = Color.Black,
    primaryVariant = Color(0x3700B3),
    secondary = Color(0x03DAC6),
    secondaryVariant = Color(0x018786),
    background = Color.White,
    surface = Color.White,
    error = Color(0xB00020),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White,
)

fun getTheme() : Colors {
    if (darkTheme) {
        return customDarkColors()
    }
    return customLightColors()
}