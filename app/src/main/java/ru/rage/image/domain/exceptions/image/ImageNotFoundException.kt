package ru.rage.image.domain.exceptions.image

import ru.rage.image.domain.exceptions.DomainException

class ImageNotFoundException : DomainException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}