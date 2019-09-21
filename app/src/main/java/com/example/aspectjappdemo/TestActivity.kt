package com.example.aspectjappdemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    companion object{
        const val KEY_NUMBER = "key_number"
        fun navigate(context: Context, number: Int){
            val intent = Intent(context, TestActivity::class.java)
            intent.putExtra(KEY_NUMBER, number)
            context.startActivity(intent)
        }
    }

    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        number = intent.getIntExtra(KEY_NUMBER, 0)
        tv.text = "Test $number"
    }
}
