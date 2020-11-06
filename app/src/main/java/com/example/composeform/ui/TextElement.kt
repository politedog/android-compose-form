package com.example.composeform.ui

import androidx.compose.foundation.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.data.ElementDto

class TextElement(val elementDto: ElementDto) : ComposableElement {
    @Composable
    override fun compose(hoist: Map<String, MutableState<String>>) {
        Text(elementDto.label?:"")
    }

    override fun getHoist(): Map<String, MutableState<String>> {
        return mapOf()
    }
}
