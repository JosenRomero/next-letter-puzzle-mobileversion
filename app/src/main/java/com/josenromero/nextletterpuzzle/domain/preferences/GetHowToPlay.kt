package com.josenromero.nextletterpuzzle.domain.preferences

import com.josenromero.nextletterpuzzle.data.preferences.DataStoreRepository
import javax.inject.Inject

class GetHowToPlay @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke(): Boolean {
        return dataStoreRepository.getHowToPlay()
    }

}