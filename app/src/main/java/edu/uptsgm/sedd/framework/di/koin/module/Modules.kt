package edu.uptsgm.sedd.framework.di.koin.module

import edu.uptsgm.sedd.application.port.GetEvaluationsInputPort
import edu.uptsgm.sedd.application.port.GetEvaluationsOutputPort
import edu.uptsgm.sedd.application.port.LoginInputPort
import edu.uptsgm.sedd.application.port.LoginOutputPort
import edu.uptsgm.sedd.application.port.StudentInputPort
import edu.uptsgm.sedd.application.port.StudentOutputPort
import edu.uptsgm.sedd.application.useCase.GetEvaluationsUseCase
import edu.uptsgm.sedd.application.useCase.LoginUseCase
import edu.uptsgm.sedd.application.useCase.StudentUseCase
import edu.uptsgm.sedd.framework.android.ui.student.home.StudentHomeActivity
import edu.uptsgm.sedd.framework.android.ui.student.home.StudentHomeViewModel
import edu.uptsgm.sedd.framework.api.mock.LoginGatewayImpl
import edu.uptsgm.sedd.framework.api.mock.StudentHomeGatewayImpl
import edu.uptsgm.sedd.interfaceAdapter.gateway.LoginGateway
import edu.uptsgm.sedd.interfaceAdapter.gateway.StudentHomeGateway
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.binds
import org.koin.dsl.module

val loginModule = module {
    single<LoginGateway> { LoginGatewayImpl() } binds arrayOf(LoginOutputPort::class)
    factory<LoginInputPort> { LoginUseCase(get()) }
}

val studentHomeModule = module {
    scope<StudentHomeActivity> {
        scoped<StudentHomeGateway> { StudentHomeGatewayImpl() } binds arrayOf(
            StudentOutputPort::class,
            GetEvaluationsOutputPort::class
        )
        scoped<StudentInputPort> { StudentUseCase(get()) }
        scoped<GetEvaluationsInputPort> { GetEvaluationsUseCase(get()) }
        viewModel {
            StudentHomeViewModel(
                studentInputPort = get(),
                getEvaluationsInputPort = get()
            )
        }
    }
}

val appModule = listOf(
    loginModule,
    studentHomeModule
)
