package gsihome.reyst.checkrx

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class Presenter(
    private val generator: MessageGenerator
) {

    private val rxTasks = CompositeDisposable()
    private var view: MainView? = null

    private val messageChanel = generator.messageChanel

    fun attach(view: MainView) {
        this.view = view

        startListening()

    }

    private fun startListening() {
        messageChanel
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { view?.showMessage(it) },
                { e -> Log.e("INSPECT", e.message ?: "", e) }
            )
            .also { rxTasks.add(it) }
    }

    fun detach() {
        rxTasks.clear()
        view = null
    }

    fun getMessages() {
        generator.generateNextMessages(3)
    }


}
