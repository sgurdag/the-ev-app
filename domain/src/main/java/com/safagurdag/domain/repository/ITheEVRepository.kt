package com.safagurdag.domain.repository

import com.safagurdag.domain.common.ResultState
import com.safagurdag.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ITheEVRepository {
    suspend fun user(
    ): Flow<ResultState<User>>
}