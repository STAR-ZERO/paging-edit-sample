package com.star_zero.pagingeditsample.di.activity

import androidx.lifecycle.ViewModel
import com.star_zero.pagingeditsample.di.ViewModelKey
import com.star_zero.pagingeditsample.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
