package dev.androidbroadcast.sample.dagger.multibinding.map

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureScope

@Subcomponent(modules = [SecondModule::class])
@FeatureScope
interface SecondComponent {

    val factory: MultiViewModelFactory

    @Subcomponent.Builder
    interface Builder {

        fun build(): SecondComponent
    }
}

@Module
interface SecondModule {

    @Binds
    @IntoMap
    @ViewModelKey(SecondViewModel::class)
    fun provideSecondViewModel(secondViewModel: SecondViewModel): ViewModel
}