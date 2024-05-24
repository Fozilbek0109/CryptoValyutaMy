package uz.coder.cryptovalyutamy.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.coder.cryptovalyutamy.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val img = findViewById<ImageView>(R.id.img)
        val txt = findViewById<TextView>(R.id.app_txt)
        val progress = findViewById<ProgressBar>(R.id.progress)
        val loadAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_img)
        img.startAnimation(loadAnimation)
        loadAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                txt.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@WelcomeActivity,
                        R.anim.anim_txt
                    )
                )
            }

            override fun onAnimationEnd(animation: Animation?) {
                lifecycleScope.launch {
                    progress.visibility = View.VISIBLE
                    delay(1000)
                    startActivity(CoinInfoActivity.newIntent(this@WelcomeActivity))
                    finish()
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })


    }
}