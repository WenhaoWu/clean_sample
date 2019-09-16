package my.clean.domian.common

import io.reactivex.Flowable

abstract class Mapper<in T, E> {
    abstract fun mapFrom(from: T): E

    fun Flowable(from: T) = Flowable.fromCallable { mapFrom(from) }

    fun Flowable(from: List<T>) = Flowable.fromCallable { from.map { mapFrom(it) } }

}