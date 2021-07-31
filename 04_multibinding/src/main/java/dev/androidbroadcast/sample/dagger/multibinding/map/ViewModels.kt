package dev.androidbroadcast.sample.dagger.multibinding.map

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel()

class DetailsViewModel @Inject constructor() : ViewModel()

class SecondViewModel @Inject constructor() : ViewModel()