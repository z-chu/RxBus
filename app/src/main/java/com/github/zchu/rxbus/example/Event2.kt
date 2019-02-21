package com.github.zchu.rxbus.example

data class Event2(val text:String="test2"
                  , val value1: Double=0.222
                  , val value2: Int=21
                  , val value3: List<String> = arrayListOf("test2-1,test2-2") )