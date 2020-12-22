package com.app.sampleapp.stash

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.app.sampleapp.R
import com.app.sampleapp.databinding.ActivityStashBinding
import com.app.sampleapp.stash.base.StashBaseFragment
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_FOUR
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_THREE
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_TWO
import com.app.sampleapp.stash.utils.Constants.INITIAL_FRAGMENT
import com.app.sampleapp.stash.utils.Constants.MAXIMUM_STEPS
import com.app.sampleapp.stash.vm.StashVM
import java.lang.Exception
import java.lang.reflect.Method
import kotlin.reflect.KClass


class StashActivity : AppCompatActivity() {

    private val viewModel by viewModels<StashVM>()
    private lateinit var binding: ActivityStashBinding
    private var maxScreens: Int = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        maxScreens = intent.getIntExtra(MAXIMUM_STEPS, 4)

        val initialScreen = intent.getParcelableExtra<ScreenDataModel>(INITIAL_FRAGMENT)

        initialScreen?.let {
            if (initialScreen.bundle != null && !initialScreen.fragmentName.isNullOrEmpty()) {
                val initialFragment = checkIfClassIsValid(
                    initialScreen.fragmentName,
                    initialScreen.bundle
                )

                initialFragment?.let {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.containerOne,
                        it
                    ).commit()
                }
            }
        }

        viewModel.stashState.observe(this, { screenData ->
            when (screenData.currentScreenPosition) {
                maxScreens + 1 -> {
                    finish()
                }
                FRAGMENT_TWO -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_two,
                        screenData.fragment
                    ).commit()
                    toggle(binding.containerTwo, true)
                }
                FRAGMENT_THREE -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_three,
                        screenData.fragment
                    ).commit()
                    toggle(binding.containerThree, true)
                }
                FRAGMENT_FOUR -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_four,
                        screenData.fragment
                    ).commit()
                    toggle(binding.containerFour, true)
                }
                else -> {
                    //return result from here
                    finish()
                }
            }
        })

        viewModel.destroyState.observe(this, { screenPosition ->
            var currentScreenPosition = screenPosition
            while (currentScreenPosition <= maxScreens) {
                when (currentScreenPosition) {
                    FRAGMENT_TWO -> {
                        toggle(binding.containerTwo, false)
                    }
                    FRAGMENT_THREE -> {
                        toggle(binding.containerThree, false)
                    }
                    FRAGMENT_FOUR -> {
                        toggle(binding.containerFour, false)
                    }
                }
                currentScreenPosition++;
            }
        })
    }


    override fun onBackPressed() {
        if (viewModel.isLocalStackEmpty()) {
            super.onBackPressed()
        }else{
            when (viewModel.getLocalStackCount()) {
                FRAGMENT_TWO -> {
                    toggle(binding.containerTwo, false)
                    viewModel.removeFromLocalStack()
                }
                FRAGMENT_THREE -> {
                    toggle(binding.containerThree, false)
                    viewModel.removeFromLocalStack()
                }
                FRAGMENT_FOUR -> {
                    toggle(binding.containerFour, false)
                    viewModel.removeFromLocalStack()
                }
            }
        }

    }

    private fun toggle(view: View, show: Boolean) {
        val transition: Transition = Slide(Gravity.BOTTOM)
        transition.duration = 300
        transition.addTarget(view)
        TransitionManager.beginDelayedTransition(binding.root, transition)
        view.visibility = if (show) View.VISIBLE else View.GONE
    }


    private fun checkIfClassIsValid(className: String, bundle: Bundle): StashBaseFragment? {
        var clazz: KClass<out Any>? = null
        try {
            clazz = Class.forName(className).kotlin
        } catch (e: ClassNotFoundException) {
            Toast.makeText(this, "Class Not Found", Toast.LENGTH_SHORT).show()
            return null
        }

        val myClass = clazz.java

        var method: Method? = null
        return try {
            method = myClass.getDeclaredMethod(
                "newInstance",
                Bundle::class.java
            )
            val result: StashBaseFragment = method.invoke(null, bundle) as StashBaseFragment
            result
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Class Must Have Method named newInstance and Extend StashBaseFragment",
                Toast.LENGTH_SHORT
            )
                .show()
            null
        }
    }
}