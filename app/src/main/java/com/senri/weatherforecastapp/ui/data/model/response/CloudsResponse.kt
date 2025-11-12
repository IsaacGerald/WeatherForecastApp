package com.senri.weatherforecastapp.ui.data.model.response

import com.senri.weatherforecastapp.ui.data.db.entity.CloudsEntity

data class CloudsResponse(
    val all: Int? = null
)


fun CloudsResponse.toCloudsEntity() = CloudsEntity(
    all = all
)