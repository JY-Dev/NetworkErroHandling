package com.example.networkerrohandling

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeData()
        setListener()
    }

    private fun setListener() {
        findViewById<Button>(R.id.token_refresh_button).setOnClickListener {
            mainViewModel.getMovieJwtRefresh()
        }
        findViewById<Button>(R.id.token_expire_button).setOnClickListener {
            mainViewModel.getMovieTokenExpire()
        }
        findViewById<Button>(R.id.success_button).setOnClickListener {
            mainViewModel.getMovie()
        }
        findViewById<Button>(R.id.fail_button).setOnClickListener {
            mainViewModel.getMovieFailMessage()
        }
        findViewById<Button>(R.id.exception_button).setOnClickListener {
            mainViewModel.getMovieException()
        }
    }

    private fun observeData() {
        mainViewModel.tokenExpire.observe(this) {
            showDialog("Network 에러", "토큰 만료") {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        mainViewModel.errorMessage.observe(this) {
            val title = it.first
            val message = it.second
            showDialog(title, message) {}
        }
        mainViewModel.movie.observe(this) {
            showDialog(it.title, it.description) {}
        }
    }

    private fun showDialog(title: String, message: String, onClick: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                "확인"
            ) { dialog, _ ->
                onClick()
                dialog.dismiss()
            }
            .show()
    }

}