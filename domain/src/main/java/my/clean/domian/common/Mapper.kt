package my.clean.domian.common

import io.reactivex.Flowable

abstract class Mapper<in T, E> {
    abstract fun mapFrom(from: T): E

    fun flowable(from: T): Flowable<E> = Flowable.fromCallable { mapFrom(from) }

    fun flowable(from: List<T>): Flowable<List<E>> =
        Flowable.fromCallable { from.map { mapFrom(it) } }
}