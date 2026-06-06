package com.sujoy.smartfarming.data.mapper

import com.sujoy.smartfarming.data.local.entity.FarmEntity
import com.sujoy.smartfarming.domain.model.Farm

fun FarmEntity.toDomain(): Farm {

    return Farm(

        id = id,

        cropName = cropName,

        soilType = soilType,

        landArea = landArea,

        sowingDate = sowingDate
    )
}

fun Farm.toEntity(): FarmEntity {

    return FarmEntity(

        id = id,

        cropName = cropName,

        soilType = soilType,

        landArea = landArea,

        sowingDate = sowingDate
    )
}