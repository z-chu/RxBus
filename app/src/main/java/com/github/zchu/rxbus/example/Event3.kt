package com.github.zchu.rxbus.example

data class Event3(val text:String="test3"
                  , val value1: Double=0.222
                  , val value2: Int=21
                  , val value3: List<String> = arrayListOf("test3-1,test3-2") )