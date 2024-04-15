package com.news.main.mappers

import com.news.data.models.SourceNet
import com.news.kotlin.mapper.Mapper
import com.news.models.Source
import javax.inject.Inject

class SourceMapper @Inject constructor(): Mapper<SourceNet, Source> {
    override fun map(param: SourceNet): Source {
        return Source(id = param.id, name = param.name)
    }
}