package com.senri.weatherforecastapp.data.model.response

import com.senri.weatherforecastapp.data.db.entity.CloudsEntity

data class CloudsResponse(
    val all: Int? = null
)


fun CloudsResponse.toCloudsEntity() = CloudsEntity(
    all = all
)