package com.example.gb_mvp.data.api

import com.example.gb_mvp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object GitHubApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .header("Authorization", BuildConfig.GIT_HUB_TOKEN)
                .build()
        )

}