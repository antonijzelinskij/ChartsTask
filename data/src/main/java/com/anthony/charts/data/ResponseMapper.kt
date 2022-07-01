package com.anthony.charts.data

interface ResponseMapper<From, To> {
    fun map(from: From): To

    fun mapList(from: List<From>): List<To> {
        return from.map { map(it) }
    }
}