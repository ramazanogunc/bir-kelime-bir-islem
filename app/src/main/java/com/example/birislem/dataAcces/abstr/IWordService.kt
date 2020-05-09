package com.example.birislem.dataAcces.abstr

import android.content.res.Resources

interface IWordService {
    fun getWordList(resources: Resources):List<String>
}