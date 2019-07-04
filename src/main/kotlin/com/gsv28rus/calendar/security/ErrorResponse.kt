package com.gsv28rus.calendar.security

class ErrorResponse(code: Int, message: String) {
    private var status: Int = code
    private var message: String? = message

}
