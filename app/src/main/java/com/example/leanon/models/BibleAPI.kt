package com.example.leanon.models

import com.example.leanon.ui.theme.pages.bible.BibleModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

public interface UserApi {
    @Headers(
        "Accept: application/json"
    )
    @GET("{book}{chapter}:{verse}")
    abstract fun getUserById(@Path("book") book: String,@Path("chapter") chapter: String, @Path("verse") verse: String): Call<BibleModel?>?
}


