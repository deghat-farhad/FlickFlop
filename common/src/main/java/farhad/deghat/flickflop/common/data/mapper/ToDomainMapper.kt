package farhad.deghat.flickflop.common.data.mapper

interface ToDomainMapper<From, To> {
    fun mapToDomain(from: From): To
}