package io.kdomskia.sample.pesto.domain.extension

val Int.isEven: Boolean
    get() = this % 2 == 0

val Int.isOdd: Boolean
    get() = isEven.not()