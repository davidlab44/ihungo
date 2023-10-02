package com.david.ihungo.di

import android.content.Context
import androidx.room.Room
import com.david.ihungo.localdata.database.IhungoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "ihungo_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, IhungoDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideRecipeDao(db: IhungoDatabase) = db.getRecipeDao()

}

