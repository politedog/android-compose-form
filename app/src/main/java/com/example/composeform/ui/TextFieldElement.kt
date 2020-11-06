package com.example.composeform.ui

import androidx.compose.foundation.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.data.ElementDto

class TextFieldElement(val elementDto: ElementDto) : ComposableElement {
    @Composable
    override fun compose(hoist: Map<String, MutableState<String>>) {
        TextField(value = hoist.get("value")?.value?:"", onValueChange = {hoist.get("value")?.value = it}, label = { Text (elementDto.label?:"") })
    }

    override fun getHoist(): Map<String, MutableState<String>> {
        return mapOf(Pair("value", mutableStateOf(elementDto.default?:"")))
    }
}
