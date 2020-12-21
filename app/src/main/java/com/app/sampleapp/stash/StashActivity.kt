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
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_ONE
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_THREE
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_TWO
import com.app.sampleapp.stash.utils.Constants.INITIAL_FRAGMENT
import com.app.sampleapp.stash.utils.Constants.INITIAL_FRAGMENT_NAME
import com.app.sampleapp.stash.utils.Constants.MAXIMUM_STEPS
import com.app.sampleapp.stash.vm.StashVM
import com.app.sampleapp.utils.FragmentFactory
import java.lang.Exception
import java.lang.reflect.Method
import kotlin.reflect.KClass


class StashActivity : AppCompatActivity() {

    private val viewModel by viewModels<StashVM>()
    private lateinit var binding: ActivityStashBinding
    private var MAXSCREENS: Int = 4
    private var initalFragmentName: String = ""
    private var initialFragment: StashBaseFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MAXSCREENS = intent.getIntExtra(MAXIMUM_STEPS, 4)
        initalFragmentName = intent.getStringExtra(INITIAL_FRAGMENT_NAME)

        val initialScreen = intent.getParcelableExtra<ScreenDataModel>(INITIAL_FRAGMENT)
        initialScreen?.let {
            if (initialScreen.bundle != null && !initialScreen.fragmentName.isNullOrEmpty()) {
                initialFragment  = checkIfClassIsValid(
                    initialScreen.fragmentName,
                    initialScreen.bundle
                )

                if (initialFragment == null) {
                    finish()
                } else {
                    viewModel.expandNextScreen(
                        initialScreen
                    )
                }

            }
        }


        /*val initialFragment = initialScreen.bundle?.let {
            checkIfClassIsValid(
                initalFragmentName,
                it
            )
        }*/



        viewModel.stashState.observe(this, { screenData ->
            when (screenData.currentScreenPosition) {
                MAXSCREENS + 1 -> {
                    finish()
                }
                FRAGMENT_ONE -> {
                    initialFragment?.let {
                        supportFragmentManager.beginTransaction().replace(
                            R.id.containerOne,
                            it
                        ).commit()
                    }
                }
                FRAGMENT_TWO -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_two,
                        FragmentFactory.getFragment(screenData.fragmentName, screenData.bundle)
                    ).commit()
                    toggle(binding.containerTwo, true)
                }
                FRAGMENT_THREE -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_three,
                        FragmentFactory.getFragment(screenData.fragmentName, screenData.bundle)
                    ).commit()
                    toggle(binding.containerThree, true)
                }
                FRAGMENT_FOUR -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_four,
                        FragmentFactory.getFragment(screenData.fragmentName, screenData.bundle)
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
            while (currentScreenPosition <= MAXSCREENS) {
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

       // if (myClass is StashBaseFragment) {
            var method: Method? = null
            return try {
                method = myClass.getDeclaredMethod(
                    "newInstance",
                    Bundle::class.java
                )
                val result: StashBaseFragment = method.invoke(null, bundle) as StashBaseFragment
                result
            } catch (e: Exception) {
                Toast.makeText(this, "Class Must Have Method named newInstance and Extend StashBaseFragment", Toast.LENGTH_SHORT)
                    .show()
                null
            }

     /*   } else {
            Toast.makeText(this, "Class Must Extend StashBaseFragment", Toast.LENGTH_SHORT).show()
            return null
        }*/
/*
        if (clazz is StashBaseFragment) {
            return clazz.newInstance(bundle) as StashBaseFragment
        } else {
            throw ClassNotFoundException()
        }

        val temp = Class.forName(className).kotlin

        // val myClass= temp.java
        val method: Method = myClass.getDeclaredMethod(
            "newInstance",
            Bundle::class.java
        )
        val result: StashBaseFragment = method.invoke(null, bundle) as StashBaseFragment
        return result*/
    }
}