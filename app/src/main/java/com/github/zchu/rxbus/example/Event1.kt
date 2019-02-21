package com.github.zchu.rxbus.example

data class Event1(val text:String="test1"
                  ,val value1: Double=0.222
                  ,val value2: Int=21
                  ,val value3: List<String> = arrayListOf("test1-1,test2-1") )