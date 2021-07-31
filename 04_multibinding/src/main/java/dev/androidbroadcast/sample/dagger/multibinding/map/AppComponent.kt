package dev.androidbroadcast.sample.dagger.multibinding.map

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dev.androidbroadcast.sample.dagger.multibinding.utils.AppScope

@Component(modules = [AppBindsModule::class])
@AppScope
interface AppComponent {

    val factory: MultiViewModelFactory

    val secondComponent: SecondComponent.Builder

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
    }
}

@Module(subcomponents = [SecondComponent::class])
interface AppBindsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun provideDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel
}