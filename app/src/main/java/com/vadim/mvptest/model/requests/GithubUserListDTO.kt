package com.vadim.mvptest.model.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Класс для получения данных из retrofit запроса
 *  @Expose данная аннотация исключает из обработки все поля, не имеющие аннотации Expose
 */
data class GithubUserListDTO(
    @Expose
    @SerializedName("avatar_url")
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("login")
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    @Expose
    @SerializedName("repos_url")
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
)