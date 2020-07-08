package gsihome.reyst.checkrx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

interface MainView {
    fun showMessage(message: Message)
}

class MainActivity : AppCompatActivity(), MainView {

    private val presenter: Presenter by lazy { (application as App).presenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attach(this)

        btn_next.setOnClickListener { presenter.getMessages() }

    }

    override fun showMessage(message: Message) {
        msg_text.text = message.text
    }

    override fun onStop() {
        super.onStop()
        if(isFinishing) presenter.detach()
    }

}