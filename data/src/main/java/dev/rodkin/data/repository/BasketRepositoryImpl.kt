package dev.rodkin.data.repository

import dev.rodkin.data.localDB.BasketDao
import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.domain.repositoryIntefaces.BasketRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class BasketRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao,
    @Named("IO") private val coroutineDispatcher: CoroutineDispatcher
) : BasketRepository {

    override fun getBasketFlow(): Flow<List<BasketItem>> =
        basketDao.getBasketList()

    override suspend fun insertBasketItem(item: BasketItem) {
        withContext(coroutineDispatcher) {
            basketDao.insert(item)
        }
    }

    override suspend fun deleteBasketItem(item: BasketItem) {
        withContext(coroutineDispatcher) {
            basketDao.delete(item)
        }
    }

    override suspend fun updateBasketItem(item: BasketItem) {
        withContext(coroutineDispatcher) {
            basketDao.update(item)
        }
    }

}