package com.safagurdag.theevapp.view.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safagurdag.domain.common.ResultState
import com.safagurdag.domain.usecase.profile.ProfileUiModel
import com.safagurdag.domain.usecase.profile.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: ProfileUseCase) :
    ViewModel() {

    private val _profileState =
        MutableStateFlow<ResultState<ProfileUiModel>>(ResultState.Loading())
    val profileState get() = _profileState

    fun getProfile() {
        viewModelScope.launch {
            _profileState.emit(ResultState.Loading())
            profileUseCase().collect {
                _profileState.tryEmit(
                    when (it) {
                        is ResultState.Failure -> ResultState.failure(it.failureReason)
                        is ResultState.Loading -> ResultState.Loading()
                        is ResultState.Success -> ResultState.success(it.value)
                    }
                )
            }
        }
    }
}