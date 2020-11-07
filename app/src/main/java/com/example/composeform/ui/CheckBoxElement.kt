package com.example.composeform.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.data.ElementDto

class CheckBoxElement(val elementDto: ElementDto) : ComposableElement {
    val fieldName = elementDto.data?:"value"
    @Composable
    override fun compose(hoist: Map<String, MutableState<String>>) {
        Row {
            Checkbox(checked = hoist.get(fieldName)?.value.equals("true", true), onCheckedChange = {it -> hoist.get(fieldName)?.value = it.toString()})
            Text (elementDto.label?:"")
        }
    }

    override fun getHoist(): Map<String, MutableState<String>> {
        return mapOf(Pair(fieldName, mutableStateOf(elementDto.default?:"")))
    }
}

