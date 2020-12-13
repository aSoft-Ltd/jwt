package tz.co.asoft

interface KeySource<T : KeyInfo> {
    suspend fun add(key: T): T
    suspend fun remove(key: T): T
    suspend fun load(kid: String): T?
    suspend fun all(): List<T>
}