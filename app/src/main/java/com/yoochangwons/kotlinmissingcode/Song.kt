package com.yoochangwons.kotlinmissingcode

import java.io.Serializable

class Song(
    var title: String? = null,
    var thumbnail: String? = null,
    var song: String? = null
) : Serializable