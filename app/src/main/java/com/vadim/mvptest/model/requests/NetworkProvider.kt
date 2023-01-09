package com.vadim.mvptest.model.requests

import com.google.gson.GsonBuilder
import com.vadim.mvptest.GITHUB_REQUEST_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProvider {

    val usersApi by lazy { createRetrofit().create(IDataSource::class.java) }

    /**
     * Retrofit запрос
     */
    fun createRetrofit() = Retrofit.Builder()
        .baseUrl(GITHUB_REQUEST_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())// оборачивает запрос в RxJava
        .addConverterFactory(GsonConverterFactory.create(createGson())) //добавляет JSON конвертер
        .build()

    /**
     * Создание Gson поддерживающую работу с аннотацией @Expose
     * данная аннотация исключает из обработки все поля, не имеющие аннотации Expose
     */
    private fun createGson() = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()
}