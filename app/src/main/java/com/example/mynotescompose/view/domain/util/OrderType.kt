package com.example.mynotescompose.view.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
