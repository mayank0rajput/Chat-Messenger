package com.example.apitest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST


interface ApiService {
    @POST("v1/messages")
    fun generateMessage(
        @HeaderMap headers: Map<String, String>,
        @Body request: RequestBody
    ): Call<ApiResponse>
}