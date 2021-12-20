package com.example.networkerrohandling.exception

import java.lang.Exception

class TokenExpireException(message : String) : Exception(message)
class JwtRefreshException(message : String) : Exception(message)