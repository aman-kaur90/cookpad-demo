package com.cook.cookpadapp.errors

import com.cook.cookpadapp.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
