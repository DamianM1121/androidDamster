package imcCalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bewolf1121.androiddamster.R
import imcCalculator.ImcCalculatorActivity.Companion.IMCKEY

class ResultImcActivity : AppCompatActivity() {

    private lateinit var txRange: TextView
    private lateinit var txImcResult: TextView
    private lateinit var txImcDescription: TextView
    private lateinit var btnReCalular:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)
        val result: Double = intent.extras?.getDouble(IMCKEY) ?: -1.0
        InitializeComponents()
        InitUi(result)
        InitListener()
    }



    private fun InitUi(result: Double) {
        txImcResult.text = result.toString()
        when (result) {
            in 0.00..18.50 -> { // Bajo peso
                txRange.text = getString(R.string.title_bajo_peso)
                txImcDescription.text = getString(R.string.description_bajo_peso)
                txRange.setTextColor(ContextCompat.getColor(this, R.color.color_bajo_peso))
            }

            in 18.51..24.99 -> { // Peso normal
                txRange.text = getString(R.string.title_peso_normal)
                txImcDescription.text = getString(R.string.description_peso_normal)
                txRange.setTextColor(ContextCompat.getColor(this, R.color.color_peso_normal))
            }

            in 25.00..29.99 -> { // Sobrepeso
                txRange.text = getString(R.string.title_sobrepeso)
                txImcDescription.text = getString(R.string.description_sobrepeso)
                txRange.setTextColor(ContextCompat.getColor(this, R.color.color_sobrepeso))
            }

            in 30.00..99.00 -> { // Obesidad
                txRange.text = getString(R.string.title_obesidad)
                txImcDescription.text = getString(R.string.description_obesidad)
                txRange.setTextColor(ContextCompat.getColor(this, R.color.color_obesidad))
            }

            else -> {//error
                txRange.text = getString(R.string.error)
                txImcResult.text = getString(R.string.error)
                txImcDescription.text = getString(R.string.error)
            }
        }
    }

    private fun InitializeComponents() {
        txRange = findViewById(R.id.txRange)
        txImcResult = findViewById(R.id.txImcResult)
        txImcDescription = findViewById(R.id.txImcDescription)
        btnReCalular=findViewById(R.id.btnReCalular)
    }
    private fun InitListener() {
        btnReCalular.setOnClickListener {onBackPressed()}

    }
}