package com.safagurdag.theevapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safagurdag.data.api.theevapi.TheEVApiService
import com.safagurdag.data.repository.TheEVRepository
import com.safagurdag.domain.repository.ITheEVRepository
import com.safagurdag.domain.usecase.profile.ProfileUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ProfileUseCaseTest {

    @RelaxedMockK
    lateinit var profileUseCase: ProfileUseCase
    private val last_energy_level: String = "60"
    private val subscription_miles_left: String = "500"
    private val maxMiles: Int = 1000

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `should Return Energy Level Between 1 And 0`() {
        assertEquals(0.6f, profileUseCase.getEnergyLevelPercentage(last_energy_level = last_energy_level)
        )
    }

    @Test
    fun `should Return Remaining Miles Percentage As 0 point 5`() {
        assertEquals(
            0.5f,
            profileUseCase.getMilesLeftPercentage(
                subscription_miles_left = subscription_miles_left,
                maxMiles = maxMiles
            )
        )
    }
}