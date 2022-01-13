package com.cook.cookpadapp.errors

import com.cook.cookpadapp.data.error.Error
import com.cook.cookpadapp.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by Amanpreet Kaur
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
