package com.safagurdag.domain.usecase.profile

import com.safagurdag.domain.common.ResultState
import com.safagurdag.domain.model.User
import com.safagurdag.domain.repository.ITheEVRepository
import com.safagurdag.domain.usecase.UseCaseWithoutParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

data class ProfileUiModel(
    val last_energy_level: String,
    val last_energy_level_percentage: Float,
    val subscription_miles_left: String,
    val subscription_miles_left_percentage: Float,
)

class ProfileUseCase @Inject constructor(
    private val repository: ITheEVRepository
) : UseCaseWithoutParams<ProfileUiModel>() {


    suspend operator fun invoke() = run()

    override suspend fun run(): Flow<ResultState<ProfileUiModel>> {
        return repository.user().map {
            when (it) {
                is ResultState.Success -> ResultState.success(getProfileUIModel(it.value))
                is ResultState.Failure -> ResultState.failure(it.failureReason)
                is ResultState.Loading -> ResultState.Loading()
            }
        }
    }

    private fun getProfileUIModel(user: User): ProfileUiModel {
        return ProfileUiModel(
            user.bookings.first().car.last_energy_level,
            getEnergyLevelPercentage(user.bookings.first().car.last_energy_level),
            user.bookings.first().subscription_miles_left,
            getMilesLeftPercentage(user.bookings.first().subscription_miles_left),
        )
    }

    fun getMilesLeftPercentage(subscription_miles_left: String, maxMiles: Int = 1000): Float {
        return subscription_miles_left.toFloat() / maxMiles
    }

    fun getEnergyLevelPercentage(last_energy_level: String): Float {
        return last_energy_level.toFloat() / 100.0f
    }
}
