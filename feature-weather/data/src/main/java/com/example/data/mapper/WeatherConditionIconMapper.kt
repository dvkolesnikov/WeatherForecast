package com.example.data.mapper

import com.example.data.dto.WeatherConditionDto

fun WeatherConditionDto.toIconUrl(
    baseUrl: String,
): String {
    return "$baseUrl$PATH".format(iconId).replace("//", "/")
}

private const val PATH = "/payload/api/media/file/%s.png"
