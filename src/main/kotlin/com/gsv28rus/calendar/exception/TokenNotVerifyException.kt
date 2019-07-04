package com.gsv28rus.calendar.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class TokenNotVerifyException(s: String) : SecurityException(s)
