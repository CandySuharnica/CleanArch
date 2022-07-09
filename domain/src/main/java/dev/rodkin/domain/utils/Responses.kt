package dev.rodkin.domain.utils

sealed class Response<T> {
    abstract val data: T
    abstract fun <R> mapData(transform: (T) -> R): Response<R>

    data class Success<T>(override val data: T) : Response<T>() {
        override fun <R> mapData(transform: (T) -> R): Response<R> {
            return Success(transform.invoke(data))
        }
    }

    data class Error<T>(override val data: T, val exception: String) : Response<T>() {
        override fun <R> mapData(transform: (T) -> R): Response<R> {
            return Error(transform.invoke(data), exception)
        }
    }

}