package com.jess.kurly.domain.type

enum class SectionType {
    GRID,
    HORIZONTAL,
    VERTICAL,
    ;

    companion object {

        fun from(type: String?): SectionType {
            return entries.find {
                it.name == type?.uppercase()
            } ?: VERTICAL // 기본값
        }
    }
}