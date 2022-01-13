package com.cook.cookpadapp.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.cook.cookpadapp.databinding.SplashLayoutBinding
import com.cook.cookpadapp.base.BaseActivity
import com.cook.cookpadapp.SPLASH_DELAY
import com.cook.cookpadapp.ui.component.recipes.RecipesListActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Amanpreet Kaur
 */
@AndroidEntryPoint
class SplashActivity : BaseActivity(){

    private lateinit var binding: SplashLayoutBinding

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    override fun observeViewModel() {
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            val nextScreenIntent = Intent(this, RecipesListActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, SPLASH_DELAY.toLong())
    }
}
