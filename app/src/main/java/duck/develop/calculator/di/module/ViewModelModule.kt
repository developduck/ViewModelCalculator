package duck.develop.calculator.di.module

import duck.develop.calculator.di.Repository
import duck.develop.calculator.di.named
import duck.develop.calculator.task.viewmodel.CalculateViewModel
import duck.develop.calculator.task.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { SplashViewModel(get(named(Repository.CONFIG_REPOSITORY)), get()) }
    viewModel { CalculateViewModel(get(named(Repository.KEYBOARD_REPOSITORY))) }
}