package com.cook.cookpadapp.base

import androidx.lifecycle.ViewModel
import com.cook.cookpadapp.errors.ErrorManager
import javax.inject.Inject


/**
 * Created by Amanpreet Kaur
 */


abstract class BaseViewModel : ViewModel() {

    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}
