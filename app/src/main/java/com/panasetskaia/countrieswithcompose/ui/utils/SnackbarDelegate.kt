package com.panasetskaia.countrieswithcompose.ui.utils

import android.content.Context
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.panasetskaia.countrieswithcompose.R
import com.panasetskaia.countrieswithcompose.domain.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class SnackbarState {
    DEFAULT,
    ERROR
}

class SnackbarDelegate(
    var snackbarHostState: SnackbarHostState? = null,
    var coroutineScope: CoroutineScope? = null
) {

    private var snackbarState: SnackbarState = SnackbarState.DEFAULT

    val snackbarBackgroundColor: Color
        @Composable
        get() = when (snackbarState) {
            SnackbarState.DEFAULT -> MaterialTheme.colors.secondary
            SnackbarState.ERROR -> Color.Red
        }

    val snackbarContentColor: Color
    @Composable
    get() = when (snackbarState) {
        SnackbarState.DEFAULT -> Color.White
        SnackbarState.ERROR -> Color.White
    }

    fun showSnackbar(
        state: SnackbarState,
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        this.snackbarState = state
        coroutineScope?.launch {
            snackbarHostState?.showSnackbar(message, actionLabel, duration)
        }
    }
}

internal fun showSnackBarWithError(
    snackbarService: SnackbarDelegate,
    networkResult: NetworkResult,
    context: Context
) {
    val msg = networkResult.msg ?: context.getString(R.string.smth_wrong)
    snackbarService.showSnackbar(
        SnackbarState.ERROR,
        msg
    )
}