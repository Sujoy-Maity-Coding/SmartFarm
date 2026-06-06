package com.sujoy.smartfarming.di

import android.content.Context
import androidx.room.Room
import com.sujoy.smartfarming.data.local.dao.DailyRecordDao
import com.sujoy.smartfarming.data.local.dao.FarmDao
import com.sujoy.smartfarming.data.local.database.SmartIrrigationDatabase
import com.sujoy.smartfarming.data.repo.FarmRepositoryImpl
import com.sujoy.smartfarming.domain.repo.FarmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(
    SingletonComponent::class
)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SmartIrrigationDatabase {

        return Room.databaseBuilder(
            context,
            SmartIrrigationDatabase::class.java,
            "smart_irrigation.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideFarmDao(
        database: SmartIrrigationDatabase
    ): FarmDao {
        return database.farmDao()
    }

    @Provides
    fun provideDailyRecordDao(
        database: SmartIrrigationDatabase
    ): DailyRecordDao {
        return database.dailyRecordDao()
    }

    @Provides
    @Singleton
    fun provideFarmRepository(
        farmDao: FarmDao,
        dailyRecordDao: DailyRecordDao
    ): FarmRepository {

        return FarmRepositoryImpl(
            farmDao,
            dailyRecordDao
        )
    }
}