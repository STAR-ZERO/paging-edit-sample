package com.star_zero.pagingeditsample.di

import com.star_zero.pagingeditsample.di.activity.MainActivityModule
import com.star_zero.pagingeditsample.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
