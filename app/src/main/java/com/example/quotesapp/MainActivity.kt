package com.example.quotesapp

import android.app.ProgressDialog
import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.quotesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var dialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        RequestManager(this@MainActivity).getAllQuotes(listener)
        dialog = ProgressDialog(this@MainActivity)
        dialog?.setTitle("Loading....")
        dialog?.show()
    }

    private val listener: QuotesResponseListener = object : QuotesResponseListener{
        override fun didFetch(response: List<QuotesResponse>, message: String) {
            dialog?.dismiss()
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
            val adapter = QuotesListAdapter(this@MainActivity, response, copyListener)
            binding.recyclerView.adapter = adapter
        }

        override fun didError(message: String) {
            dialog?.dismiss()
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }

    }

    private val copyListener: CopyListener = object : CopyListener{
        override fun onCopyClicked(text: String) {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Copied_data", text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this@MainActivity,"Quote Copied to Clipboard!", Toast.LENGTH_LONG).show()
        }

    }
}