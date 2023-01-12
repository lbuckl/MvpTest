package com.vadim.mvptest.model

import android.util.Log
import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.database.GithubAppDB
import com.vadim.mvptest.model.database.githubrepositories.GithubRepoDBImpl
import com.vadim.mvptest.model.database.users.GithubUserDBImpl
import com.vadim.mvptest.model.requests.GithubRepositoryRest
import com.vadim.mvptest.model.requests.IDataSource
import com.vadim.mvptest.model.requests.dto.repository.UserRepositoryDTO
import com.vadim.mvptest.model.requests.dto.user.GithubUserDTO
import com.vadim.mvptest.utils.GithubRepositoryMapper
import com.vadim.mvptest.utils.UserMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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
    val networkStatus: INetworkStatus
): GithubRepositoryRest, GithubUserDBImpl, GithubRepoDBImpl {
    
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
                Log.v("@@@","Online Users")
                getDataFromApiAndSaveToDB()
            }
            else {
                Log.v("@@@","Offline Users")
                getUsersFromDB()
            }
        }
    }

    //Функция получения данных от API и запись их в БД Room
    private fun getDataFromApiAndSaveToDB(): Single<List<GithubUserEntity>>{
        return usersApi.getAllUsers()
            .map {
                saveUsersToDB(it)
                it.map(UserMapper::mapUserDtoToEntity)
            }.subscribeOn(Schedulers.io())
    }

    //Функция сохранения данных пользователя в БД Room
    override fun saveUsersToDB(userData: List<GithubUserDTO>){
        dataBase.userDao.insertAll(
            userData.map(UserMapper::mapUserDtoToDb)
        )
    }

    //Фукнция получения данных из БД Room
    override fun getUsersFromDB(): Single<List<GithubUserEntity>>{
        return dataBase.userDao.queryAllUsers().map {
            it.map(UserMapper::mapUserDbToEntity)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    //endregion_______________________________________________________________

    //region работа со списком пользователей________________________________
    /**
     * Функция запроса списка репозиториев с подробной информацией
     * Получает данные о состоянии сети интернет и выдаёт данные:
     *  - если интернет есть - от API
     *  - если интернета нет - из БД Room
     */
    override fun getRepositoryInformation(userId:Int, url: String): Single<List<GithubRepositoryEntity>> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline){
                Log.v("@@@","Online Repo")
                getRepositoryDataFromApiAndSaveToDB(userId,url)
            }
            else {
                Log.v("@@@","Offline Repo")
                getRepositoryFromDB(userId)
            }
        }
    }

    //Функция получения данных от API и сохранеия их в БД Room
    private fun getRepositoryDataFromApiAndSaveToDB(userId:Int, url: String): Single<List<GithubRepositoryEntity>>{
        return usersApi.getGithubRepositoryInfo(url)
            .map {
                saveRepositoryToDB(userId,it)
                it.map(GithubRepositoryMapper::mapRepositoryDtoToEntity)
            }.subscribeOn(Schedulers.io())
    }

    //Функция сохранения данных репозитория в БД Room
    override fun saveRepositoryToDB(userId:Int, repoData: UserRepositoryDTO){
        dataBase.repoDao.insertAllRepo(
            repoData.map {
                    GithubRepositoryMapper.mapRepoDtoToDb(userId,it)
            }
        )
    }

    //Функция получения данных репозитория из БД Room
    override fun getRepositoryFromDB(userId: Int):Single<List<GithubRepositoryEntity>> {
        return dataBase.repoDao.queryAllRepos(userId).map {
            it.map(GithubRepositoryMapper::mapRepoDbToEntity)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    //endregion
}