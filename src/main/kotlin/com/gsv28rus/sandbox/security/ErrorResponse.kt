package com.gsv28rus.sandbox.security

class ErrorResponse(code: Int, message: String) {
    private var status: Int = code
    private var message: String? = message

}
