package my.clean.persentation.common

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import my.clean.domian.common.FlowableRxTransformer

class AsyncFlowableTransformer<T> : FlowableRxTransformer<T>() {
    override fun apply(upstream: Flowable<T>) =
        upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}