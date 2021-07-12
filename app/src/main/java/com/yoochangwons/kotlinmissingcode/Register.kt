package com.yoochangwons.kotlinmissingcode

import java.io.Serializable

class Register(
    var userName: String? = null,
    var password1: String? = null,
    var password2: String? = null,
) : Serializable