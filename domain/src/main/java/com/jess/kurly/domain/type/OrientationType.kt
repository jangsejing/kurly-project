package com.jess.kurly.domain.type

enum class OrientationType {
    GRID,
    HORIZONTAL,
    VERTICAL,
    ;

    companion object {

        fun from(type: String?): OrientationType {
            return entries.find {
                it.name == type?.uppercase()
            } ?: VERTICAL // 기본값
        }
    }
}