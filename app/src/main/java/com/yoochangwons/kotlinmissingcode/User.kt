package com.yoochangwons.kotlinmissingcode

import java.io.Serializable

class User(
    var userName: String? = null,
    var token: String? = null
) : Serializable