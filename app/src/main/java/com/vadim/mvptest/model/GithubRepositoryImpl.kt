package com.vadim.mvptest.model

import android.util.Log
import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.database.GithubAppDB
import com.vadim.mvptest.model.database.GithubRepositoryDB
import com.vadim.mvptest.model.database.UserDBObject
import com.vadim.mvptest.model.requests.GithubRepositoryRest
import com.vadim.mvptest.model.requests.IDataSource
import com.vadim.mvptest.model.requests.dto.user.GithubUserDTO
import com.vadim.mvptest.utils.GithubRepositoryMapper
import com.vadim.mvptest.utils.UserMapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Класс реализующий работу с репозиторием и реализующий функции:
 *  - запрос списка пользователей и их данных из API и БД
 *  - хранение пользователей и их данных в БД
 */
class GithubRepositoryImpl constructor(
    private val usersApi: IDataSource,
    private val dataBase: GithubAppDB,
    private val networkStatus: INetworkStatus
): GithubRepositoryRest, GithubRepositoryDB {

    //region работа со списком пользователей________________________________
    /**
     * Функция запроса списка пользователей
     * Получает данные о состоянии сети интернет и выдаёт данные:
     *  - если интернет есть - от API
     *  - если интернета нет - из БД Room
     */
    override fun getUsers(): Single<List<GithubUserEntity>> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline){
                Log.v("@@@","Online")
                getDataFromApiAndSaveToDB()
            }
            else {
                Log.v("@@@","Offline")
                getDataFromDB()
            }
        }
    }

    //Функция получения данных от API и запись их в БД Room
    private fun getDataFromApiAndSaveToDB(): Single<List<GithubUserEntity>>{
        return usersApi.getAllUsers()
            .map {
                saveUserToDB(it)
                it.map(UserMapper::mapUserDtoToEntity)
            }.subscribeOn(Schedulers.io())
    }

    //Фукнция получения данных из БД Room
    private fun getDataFromDB(): Single<List<GithubUserEntity>>{
        Log.v("@@@","Start Restore")
        return dataBase.userDao.queryAllUsers().map {
            Log.v("@@@","Size ${it.size}")
            it.map(UserMapper::mapUserDbToEntity)
        }.subscribeOn(Schedulers.io())
    }
    //endregion_______________________________________________________________

    //Функция получения подробной информации о конкретном пользователе из API
    override fun getUserById(login: String): Single<GithubUserEntity> {
        return usersApi.getUser(login)
            .map(UserMapper::mapUserDtoToEntity)
    }

    //Функция получения данны о репозитори из API
    override fun getRepositoryInformation(url: String): Single<List<GithubRepositoryEntity>> {
        return usersApi.getGithubRepositoryInfo(url)
            .map { it.map(GithubRepositoryMapper::mapRepositoryDtoToEntity) }
    }

    override fun getUserFromDB(id: Int) {

    }

    override fun saveUserToDB(userData: List<GithubUserDTO>){
        dataBase.userDao.insert(
            UserMapper.mapUserDtoToDb(userData[0])
        ).doOnComplete {
            Log.v("@@@","Finish Saving1")
        }

        Log.v("@@@","Start Saving")
        dataBase.userDao.insertAll(
            userData.map(UserMapper::mapUserDtoToDb)
        )
    }
}