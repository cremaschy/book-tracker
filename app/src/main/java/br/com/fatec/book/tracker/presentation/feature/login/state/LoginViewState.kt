package br.com.fatec.book.tracker.presentation.feature.login.state

import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.ScreenType

data class LoginViewState(
    val email: String = "",
    val senha: String = "",
    val error: String? = null,
    val screenType: ScreenType = ScreenType.Content
) {
    val isLoginEnable: Boolean
        get() = email.isNotEmpty() && senha.isNotEmpty()
}
