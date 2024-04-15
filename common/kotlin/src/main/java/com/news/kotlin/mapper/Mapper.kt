package com.news.kotlin.mapper

interface Mapper<T, R> {
    fun map(param: T): R
}
