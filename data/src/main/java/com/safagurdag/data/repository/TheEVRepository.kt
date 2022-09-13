package com.safagurdag.data.repository

import com.safagurdag.data.api.theevapi.TheEVApiService
import com.safagurdag.domain.common.ResultState
import com.safagurdag.domain.model.User
import com.safagurdag.domain.repository.ITheEVRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TheEVRepository @Inject constructor(private val theEVApiService: TheEVApiService): BaseRepository(),
    ITheEVRepository {
    override suspend fun user(): Flow<ResultState<User>> =
        invoke(response = theEVApiService.userDetail())
}