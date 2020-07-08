package gsihome.reyst.checkrx

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

class MessageGenerator {

    private val messageSubject: Subject<Message> = PublishSubject.create()

    val messageChanel: Observable<Message>
        get() = messageSubject

    fun generateNextMessages(amount: Int) {
        Observable.zip(
            Observable.interval(0, 2, TimeUnit.SECONDS),
            Observable.range(1, amount),
            BiFunction<Long, Int, Int> { _, counter -> counter }
        ).subscribeOn(Schedulers.io())
            .map { Message(it, "Message $it", it == amount) }
            .doOnNext { messageSubject.onNext(it) }
            .subscribe()
    }

}
