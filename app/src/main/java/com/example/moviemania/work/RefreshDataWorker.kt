package com.example.moviemania.work

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.moviemania.repository.MainRepository
import retrofit2.HttpException



class RefreshDataWorker @WorkerInject constructor (
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    val mainRepository: MainRepository
    ) : CoroutineWorker(appContext,params) {


    companion object {
        const val WORK_NAME = "com.example.moviemania.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        try {
            mainRepository.refreshMovies()
        }catch (e: HttpException){
            return Result.retry()
        }
        return Result.success()
    }
}