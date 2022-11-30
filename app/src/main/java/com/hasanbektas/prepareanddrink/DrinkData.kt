package com.hasanbektas.prepareanddrink


import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class DrinkData (
    val strDrink : String,
    val strCategory : String,
    val strAlcoholic : String,
    val strGlass : String,
    val strInstructions : String,

    // İmage
    val strDrinkThumb : String,

    // Bileşenler
    val strIngredient1 : String,
    val strIngredient2 : String,
    val strIngredient3 : String,


    // Ölçüm
    val strMeasure1 : String,
    val strMeasure2 : String,
    val strMeasure3 : String

):Serializable