package dev.rodkin.data.repository

import dev.rodkin.data.localDB.BasketDao
import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.domain.repositoryIntefaces.BasketRepository
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao
):BasketRepository {

    override suspend fun insertBasketItem(item: BasketItem) {
        basketDao.insert(item)
    }

    override suspend fun deleteBasketItem(id: Long) {
        basketDao.delete(id)
    }

    override suspend fun updateBasketItem(item: BasketItem) {
        basketDao.update(item)
    }

}