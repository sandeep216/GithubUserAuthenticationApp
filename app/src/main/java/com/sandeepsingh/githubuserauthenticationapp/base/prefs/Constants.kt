package com.sandeepsingh.githubuserauthenticationapp.base.prefs

/**
 * Created by Sandeep on 9/29/18.
 */

class PrefernceKeys{
    companion object {
        const val KEY_DEVICE_TOKEN = "device_token"
        const val KEY_ACCESS_TOKEN = "access_token"
        const val KEY_LAST_USER_ID_KEY = "user_id"
    }
}

class ApiResponseKeys{
    companion object {
        const val KEY_CODE = "code"
        const val KEY_MESSAGE = "message"
        const val KEY_DATA = "data"
        const val KEY_ERRORS = "errors"
        const val KEY_PAGE = "page"
        const val KEY_STATUS = "status"
    }
}

class URL {
    companion object {
        const val GITHUB_API = "https://api.github.com/"
    }
}