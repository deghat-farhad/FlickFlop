package farhad.deghat.flickflop.common.domain.usecase

interface UseCaseWithParams<T,P> {
    suspend operator fun invoke(params: P): T
}