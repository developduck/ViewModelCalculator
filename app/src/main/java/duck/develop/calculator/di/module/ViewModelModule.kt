package duck.develop.calculator.di.module

import duck.develop.calculator.di.Repository
import duck.develop.calculator.di.named
import duck.develop.calculator.task.viewmodel.CalculateViewModel
import duck.develop.calculator.task.viewmodel.MoviesViewModel
import duck.develop.calculator.task.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Hwang on 2019-09-24.
 *
 * Description : 뷰 모델 모듈
 */
val viewModel = module {
    viewModel { SplashViewModel(get(named(Repository.CONFIG_REPOSITORY))) }
    viewModel { CalculateViewModel(get(named(Repository.KEYBOARD_REPOSITORY))) }
    viewModel { MoviesViewModel(get(named(Repository.MOVIE_REPOSITORY))) }
}