package com.example.composeform.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.*
import com.example.composeform.BackEndService
import com.example.composeform.ScreenJson
import com.example.composeform.ServiceLocator
import com.example.data.ElementDto

class FormElement (
    val elementDto: ElementDto
) : ComposableElement {
    @Composable
    override fun compose(hoist: Map<String, MutableState<String>>) {
        val childElements = elementDto.children?.map { it.getComposableElement() } ?: listOf()
        val children = childElements.map { Pair(it, it.getHoist() ) } 
        Column {
            children.map {
                it.first.compose(it.second)
            }
            val json = ScreenJson.current
            Button(onClick = {
                val parameters = children.flatMap { it.second.entries.map { Pair(it.key, it.value.value)  } }.toMap()
                val newPage = ServiceLocator.resolve(BackEndService::class.java).getPage(elementDto.data?:"", parameters)
                json.held.value = newPage
            }){
                Text(elementDto.label?:"")
            }
        }
    }

    override fun getHoist(): Map<String, MutableState<String>> {
        return mapOf()
    }
}
