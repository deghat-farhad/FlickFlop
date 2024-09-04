package farhad.deghat.flickflop.common.domain.usecase

interface UseCase<T> {
    suspend operator fun invoke(): T
}