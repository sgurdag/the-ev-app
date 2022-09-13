package com.safagurdag.theevapp.view.profile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.safagurdag.domain.common.ResultState
import com.safagurdag.domain.usecase.profile.ProfileUiModel
import com.safagurdag.theevapp.view.profile.viewmodel.ProfileViewModel
import com.safagurdag.theevapp.view.main.AppState
import safagurdag.theevapp.R

@Composable
fun ProfileScreen(
    appState: AppState,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val profileState by viewModel.profileState.collectAsState(ResultState.Loading())
        when (profileState) {
            is ResultState.Loading -> LoadingStateContent()
            is ResultState.Failure -> ErrorStateContent()
            is ResultState.Success -> SuccessStateContent(
                viewModel = viewModel,
                profileUiModel = (profileState as ResultState.Success<ProfileUiModel>).value
            )
        }
    }
    viewModel.getProfile()

}

@Composable
fun LoadingStateContent(
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorStateContent(
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.i_error_cloud),
            contentDescription = "error-image",
            modifier = Modifier.size(96.dp)
        )

        Text(
            text = stringResource(R.string.generic_error),
            style = MaterialTheme.typography.subtitle1
        )
    }
}


@Composable
private fun SuccessStateContent(
    profileUiModel: ProfileUiModel,
    viewModel: ProfileViewModel,
) {

    Column(Modifier.fillMaxSize(),horizontalAlignment = CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            MilesStatus(profileUiModel)
            ChargeStatus(profileUiModel)
        }

        Button(onClick = { viewModel.getProfile() }) {
            Text(text = stringResource(R.string.refresh))
        }
    }
}

@Composable
fun MilesStatus(profileUiModel: ProfileUiModel) {
    CustomProgressBar(
        progressValue = profileUiModel.subscription_miles_left_percentage,
        value = profileUiModel.subscription_miles_left,
        type = ProgressType.MILE
    )
}


@Composable
fun ChargeStatus(profileUiModel: ProfileUiModel) {
    CustomProgressBar(
        progressValue = profileUiModel.last_energy_level_percentage,
        value = profileUiModel.last_energy_level,
        type = ProgressType.CHARGE
    )
}


@Composable
fun CustomProgressBar(progressValue: Float, value: String, type: ProgressType) {
    Box(
        modifier = Modifier
            .size(140.dp)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize(), progress = progressValue)
        Column(horizontalAlignment = CenterHorizontally) {
            when (type) {
                ProgressType.CHARGE -> {
                    Text(text = stringResource(R.string.battery))
                    Text(text = value.plus("%"))
                    Text(
                        text = when (type) {
                            ProgressType.CHARGE -> stringResource(R.string.remaining_charge)
                            ProgressType.MILE -> stringResource(R.string.remaning_miles)
                        }
                    )
                }
                ProgressType.MILE -> {
                    Text(text = stringResource(R.string.monthly))
                    Text(text = value)
                    Text(
                        text = when (type) {
                            ProgressType.CHARGE -> stringResource(R.string.remaining_charge)
                            ProgressType.MILE -> stringResource(R.string.remaning_miles)
                        }
                    )
                }
            }
        }
    }
}
