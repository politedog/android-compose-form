package com.example.composeform.ui

import android.widget.Toast
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ContextAmbient
import com.example.data.ElementDto

class FormElement (
    val elementDto: ElementDto
) : ComposableElement {
    @Composable
    override fun compose(hoist: Map<String, MutableState<String>>) {
        val children = elementDto.children?.map { it.getComposableElement() } ?: listOf()
        val fields = children.map { remember { it.getHoist() } } 
        Column {
            children.zip(fields).map {
                it.first.compose(it.second)
            }
            val context = ContextAmbient.current
            Button(onClick = {
                Toast.makeText(context, fields.joinToString { it.get("value")?.value?:"" }, Toast.LENGTH_LONG).show()
            }){
                Text(elementDto.label?:"")
            }
        }
    }

    override fun getHoist(): Map<String, MutableState<String>> {
        return mapOf()
    }
}

data class FormElementOld (
    val prompt: String?,
    val initValue: String? = "",
    val type: String? = "TextField"
) {
    @Composable
    fun compose(state: MutableState<String>) {
        when(type) {
            "TextField" -> TextField(value = state.value, onValueChange = { state.value = it}, label = { Text(prompt?:"")})
            "CheckBox" -> Row {
                Checkbox(checked = state.value.equals("true", true), onCheckedChange = { it -> state.value = it.toString()})
                Text(prompt?:"")
            }
            "Header" -> Text(prompt?:"")
        }
    }
}
