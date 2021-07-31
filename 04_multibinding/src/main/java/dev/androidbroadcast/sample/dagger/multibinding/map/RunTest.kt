package dev.androidbroadcast.sample.dagger.multibinding.map


fun main() {
    test2()
}

private fun test1() {
    val appComponent = DaggerAppComponent.create()
    val viewModel = appComponent.factory.create(MainViewModel::class.java)
    print(viewModel)
}

private fun test2() {
    val appComponent: AppComponent = DaggerAppComponent.create()
    // Выводим класс ViewModel для которых есть фабрики в AppComponent
    println(appComponent.factory.viewModelsClasses.map { it.simpleName })

    val secondComponent: SecondComponent = appComponent.secondComponent.build()
    // Выводим класс ViewModel для которых есть фабрики в SecondComponent (сабкомпонент AppComponent)
    println(secondComponent.factory.viewModelsClasses.map { it.simpleName })

    // Выводим между классами ViewModel для которых есть фабрики в SecondComponent но нет в AppComponent
    print("Diff=" +
            secondComponent.factory.viewModelsClasses
                .subtract(appComponent.factory.viewModelsClasses)
                .map { it.simpleName }
    )
}