package com.hasanbektas.prepareanddrink

import retrofit2.Call
import retrofit2.http.GET

interface DrinkAPI {

    @GET("api/json/v1/1/search.php?s")
    fun getData() : Call <DrinkModel>
}