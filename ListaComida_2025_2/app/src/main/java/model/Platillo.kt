package model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Platillo(
    @StringRes val stringResourceId:Int,
    @DrawableRes val drawableResId: Int,
    @StringRes val stringResourcePrecio: Int,
    @StringRes val stringResourceOferta: Int,
    )
