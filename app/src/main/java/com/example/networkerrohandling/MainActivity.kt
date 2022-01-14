package com.example.networkerrohandling

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Visibility
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)
    }
    private val loadingQueue : Queue<Boolean> = LinkedList()

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
        findViewById<Button>(R.id.movie_info_button).setOnClickListener {
            mainViewModel.getMovieInfo()
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
        mainViewModel.movieInfo.observe(this){
            println("데이터는:$it")
        }
        mainViewModel.loading.observe(this){
            controlProgress(it)
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

    private fun controlProgress(isShowing : Boolean){
        val progress = findViewById<ProgressBar>(R.id.progress)
        if(progress.visibility == View.GONE && isShowing){
            progress.visibility = View.VISIBLE
            return
        }
        if(progress.visibility == View.VISIBLE){
            if(isShowing) loadingQueue.offer(true)
            else {
                if(loadingQueue.isNotEmpty())
                    loadingQueue.poll()
                else
                    progress.visibility = View.GONE
            }
        }
    }

    private fun clearProgress(){
        loadingQueue.clear()
        controlProgress(false)
    }

}