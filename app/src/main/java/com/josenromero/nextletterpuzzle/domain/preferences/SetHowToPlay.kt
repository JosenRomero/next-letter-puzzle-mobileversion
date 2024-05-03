package com.josenromero.nextletterpuzzle.domain.preferences

import com.josenromero.nextletterpuzzle.data.preferences.DataStoreRepository
import javax.inject.Inject

class SetHowToPlay @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke(value: Boolean) {
        dataStoreRepository.setHowToPlay(value)
    }

}