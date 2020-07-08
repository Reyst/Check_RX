package gsihome.reyst.checkrx

import android.app.Application

class App: Application() {

    private val messageGenerator: MessageGenerator by lazy { MessageGenerator() }
    val presenter: Presenter by lazy { Presenter(messageGenerator) }


}