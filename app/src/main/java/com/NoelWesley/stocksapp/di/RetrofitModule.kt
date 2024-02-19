package com.NoelWesley.stocksapp.di

import com.NoelWesley.stocksapp.screens.account_screen.data.remote.AccountApi
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.HomeScreenApi
import com.NoelWesley.stocksapp.screens.login.data.remote.LoginApi
import com.NoelWesley.stocksapp.screens.registration.data.remote.RegistrationApi
import com.NoelWesley.stocksapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

//The @Module annotation marks the class as a module that can provide dependencies to other classes.
@Module

//The @InstallIn annotation specifies which component the module belongs to.
// In this case, the module is installed in the SingletonComponent,
// which means that the objects provided by the module will be available throughout the application’s lifetime.
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    //The @Singleton annotation indicates that only one instance of the annotated class or
    // method will be created and shared throughout the application
    @Singleton

    //The @Provides annotation marks a method that creates and returns an object that can be injected into other classes.
    //The method must be inside a class that is annotated with @Module, which tells Dagger how to provide the dependencies.
    @Provides
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        ////this function is providing a singleton Retrofit instance for your application with a specified base URL and a Gson converter factory
    }

    @Singleton
    @Provides

    //The code defines a method that provides a RegistrationApi object,
    // which is an interface that defines the API endpoints for user registration.
    fun provideRegisterApi(retrofit: Retrofit): RegistrationApi {

        //Creating an instance of our RegistrationAPI
        return retrofit.create(RegistrationApi::class.java)

    }


    @Singleton
    @Provides
    //The code defines a method that provides a LoginApi object,
    // which is an interface that defines the API endpoints for user login.
    fun providesLoginApi(retrofit: Retrofit) : LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Singleton
    @Provides
    //The code defines a method that provides a AccountApi object,
    // which is an interface that defines the API endpoints for user login.
    fun providesAccountApi(retrofit: Retrofit) : AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Singleton
    @Provides
    //The code defines a method that provides a HomeScreen object,
    // which is an interface that defines the API endpoints for HomeScreen.
    fun providesHomeScreenApi(retrofit: Retrofit): HomeScreenApi{
        return retrofit.create(HomeScreenApi::class.java)
    }







}