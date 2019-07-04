package com.gsv28rus.sandbox.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class TokenNotFoundException(s: String) : SecurityException(s)
