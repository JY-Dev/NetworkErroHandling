package com.example.networkerrohandling.util

import java.lang.Exception

class TokenExpireException(message : String) : Exception(message)
class JwtRefreshException(message : String) : Exception(message)