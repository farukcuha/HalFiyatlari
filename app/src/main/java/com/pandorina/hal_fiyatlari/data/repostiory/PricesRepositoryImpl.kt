package com.pandorina.hal_fiyatlari.data.repostiory

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import com.pandorina.hal_fiyatlari.data.remote.price.dto.PriceDto
import com.pandorina.hal_fiyatlari.domain.model.price.PriceDate
import com.pandorina.hal_fiyatlari.domain.repository.PricesRepository
import kotlinx.coroutines.tasks.await

class PricesRepositoryImpl(
    private val firebaseFirestore: FirebaseFirestore
): PricesRepository {

    override suspend fun getPriceDates(cityId: String?): Result<List<PriceDate>> {
        return try {
            val result = firebaseFirestore
                .collection("price_dates")
                .whereEqualTo("cityId", cityId)
                .orderBy("lastUpdatedTime", Query.Direction.DESCENDING)
                .get(Source.SERVER)
                .await()
                .toObjects(PriceDate::class.java)
            Result.success(result)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun getPricesByDate(cityId: String?, date: String?): Result<List<PriceDto>> {
        return try {
            val result = firebaseFirestore
                .collection("prices")
                .whereEqualTo("cityId", cityId)
                .whereEqualTo("priceDate", date)
                .get(Source.SERVER)
                .await()
                .toObjects(PriceDto::class.java)
            Result.success(result)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}