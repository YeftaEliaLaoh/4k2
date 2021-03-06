package com.example.a4k.core.network.responses

import com.example.a4k.core.annotations.OpenForTesting

/**
 * Generic network response for any type data [T].
 *
 * @param code The HTTP status code of the returned result.
 * @param status A string description of the call status.
 * @param message A more descriptive message of the failure call status.
 * @param data The results returned by the call.
 */
@OpenForTesting
data class BaseResponse<T>(
    val results: List<T>
)
