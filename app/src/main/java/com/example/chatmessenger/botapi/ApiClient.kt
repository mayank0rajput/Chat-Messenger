package com.example.apitest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiClient {
    private val baseUrl = "https://getcody.ai/api/"
    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    suspend fun generateMessage(prompt: String, conversationId: String, accessToken: String): String {
        return withContext(Dispatchers.IO) {
            val apiService = retrofit.create(ApiService::class.java)
            val requestBody = RequestBody(prompt, conversationId)

            val headers = mapOf(
                "Authorization" to "Bearer $accessToken",
                "Content-Type" to "application/json"
            )

            val call = apiService.generateMessage(headers, requestBody)
            val response: Response<ApiResponse> = call.execute()

            if (response.isSuccessful) {
                val responseData = response.body()
                responseData?.data?.content ?: "Response data is null"
            } else {
                "API request failed with code: ${response.code()}"
            }
        }
    }
}