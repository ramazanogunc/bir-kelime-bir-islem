package com.example.birislem.dataAcces

import android.content.res.Resources
import com.example.birislem.R
import com.example.birislem.dataAcces.abstr.IWordService
import org.json.JSONArray

class FakeWordService:IWordService {

    override fun getWordList(resources: Resources): List<String> {
        val text = resources.openRawResource(R.raw.word_list)
            .bufferedReader().use { it.readText() }
        val json = JSONArray(text)
        val list = mutableListOf<String>()
        for (i in 0 until json.length()) {
            val item = json.get(i).toString()
            list.add(item)
        }
        return list.toList()
    }
}
