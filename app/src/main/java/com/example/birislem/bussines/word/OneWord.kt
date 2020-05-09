package com.example.birislem.bussines.word

import com.example.birislem.bussines.word.abstr.OneWordListener

class OneWord (
    private val wordList: List<String>,
    private val charArray: Array<Char>,
    private val oneWordListener: OneWordListener
){

    private val foundWordList = mutableListOf<String>()

    fun findWords(){
        for ( word in wordList ){
            val success = searchInOneWord(word)
            if (success)
                foundWordList.add(word)
        }
    }

    private fun searchInOneWord(word: String): Boolean {
        val tempCharArray = charArray.toMutableList()
        var jokerUsed = false
        for (wordChar in word.toCharArray()){
            val success = searchInOneChar(wordChar, tempCharArray)
            if (!success && jokerUsed)
                return false
            else if (!success && !jokerUsed)
                jokerUsed = true
        }
        return true
    }

    private fun searchInOneChar(char:Char, charList:MutableList<Char>):Boolean{
        charList.forEachIndexed { index, c ->
            if (char == c){
                charList.removeAt(index)
                return true
            }
        }
        return false
    }

    fun runAnswer(){
        oneWordListener.onWordsFound(foundWordList.toList())
    }
}