package com.example.jettip.Util

import java.time.temporal.TemporalAmount
import java.util.Spliterator


fun calculateToatlTip(totalBill: Double, tipPercentage: Int): Double {
    return if(totalBill > 1 && totalBill.toString().isNotEmpty()){
        (totalBill * tipPercentage) / 100
    }else{
        0.0
    }
}

fun calculateTotalPerPerson(totalBill: Double,splitby: Int,tipAmount: Double): Double {
    val bill = totalBill + tipAmount
    return (bill / splitby)

}