package dev.rodkin.domain.entities

data class User(
    val name: String,
    val avatarUri: String,
    val likes: List<Long>
)
