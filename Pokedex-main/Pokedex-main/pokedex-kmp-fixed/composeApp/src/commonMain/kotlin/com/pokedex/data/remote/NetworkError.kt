package com.pokedex.data.remote

sealed class NetworkError {
    object NoConnection : NetworkError()
    object Timeout : NetworkError()
    data class HttpError(val code: Int) : NetworkError()
    object Unknown : NetworkError()

    fun toMessage(): String = when (this) {
        is NoConnection -> "Sem conexão com a internet. Verifique sua rede."
        is Timeout -> "O servidor demorou muito para responder. Tente novamente."
        is HttpError -> when (code) {
            404 -> "Recurso não encontrado (404)."
            500 -> "Erro interno no servidor (500). Tente mais tarde."
            401 -> "Sem autorização para acessar este conteúdo (401)."
            else -> "Erro HTTP $code. Tente novamente."
        }
        is Unknown -> "Erro desconhecido. Tente novamente."
    }
}