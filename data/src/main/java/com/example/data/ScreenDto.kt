package com.example.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ScreenDto (
    val children: List<ElementDto>? = null
) 

@JsonClass(generateAdapter = true)
data class ElementDto (
    val children: List<ElementDto>? = null,
    val label: String? = null,
    val viewtype: ViewType? = null,
    val default: String? = null,
    val data: String? = null
)

enum class ViewType {
    TEXT,
    TEXTFIELD,
    CHECKBOX,
    FORM
}

