package com.example.warmupappp.di

import android.content.Context
import androidx.room.Room
import com.example.warmupappp.room.CocktailDao
import com.example.warmupappp.room.CocktailDatabase
import com.example.warmupappp.room.CocktailRepository
import com.example.warmupappp.service.CocktailApi
import com.example.warmupappp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRetrofitCocktail(): CocktailApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_COCK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CocktailApi::class.java)
    }
    @Provides
    @Singleton
    fun provideRepository(
cocktailApi: CocktailApi,
        cocktailDao: CocktailDao,
    ): CocktailRepository {
        return CocktailRepository(cocktailApi, cocktailDao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CocktailDatabase {
        return Room.databaseBuilder(
            appContext,
            CocktailDatabase::class.java,"cocktails",
        ).build()
    }

    @Provides
    fun provideCockDao(database: CocktailDatabase): CocktailDao {
        return database.cocktailDao()
    }
}