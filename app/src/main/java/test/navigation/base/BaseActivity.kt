package test.navigation.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import test.navigation.R

abstract  class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById<Button>(R.id.button1).setOnClickListener{ onClickAction1()}
//        findViewById<Button>(R.id.button2).setOnClickListener{onClickAction2()}

    }

    abstract fun onClickAction1()
    abstract fun onClickAction2()
}