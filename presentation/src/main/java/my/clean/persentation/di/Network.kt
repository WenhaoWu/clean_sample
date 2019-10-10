package my.clean.persentation.di

import my.clean.presentation.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

fun createNetworkClient(url: String) = retrofitFactory(url, httpClient())

private fun httpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val builder = OkHttpClient.Builder()

    builder.run {

        if (BuildConfig.DEBUG) {
            addInterceptor(httpLoggingInterceptor)
        }

        addInterceptor(BasicAuthInterceptor())
        readTimeout(120, TimeUnit.SECONDS)
        writeTimeout(120, TimeUnit.SECONDS)
        return build()
    }
}

class BasicAuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl =
            request.url().newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}

private fun retrofitFactory(url: String, client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()