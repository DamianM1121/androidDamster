package imcCalculator


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bewolf1121.androiddamster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    //Variables privadas que preparan las cardView pero no las llama
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var txHeight: TextView
    private lateinit var rsAltura: RangeSlider
    private lateinit var txWeight: TextView
    private lateinit var txYearsOld: TextView
    private lateinit var fab_plusWeight: FloatingActionButton
    private lateinit var fab_minumWeight: FloatingActionButton
    private lateinit var fab_minumYearsOld: FloatingActionButton
    private lateinit var fab_plusYearsOld: FloatingActionButton
    private lateinit var btnCalcularIMC: Button

    companion object {
        const val IMCKEY = "RESULT_IMC"
    }

    //booleanas que confirman si estan seleccionadas male y female
    private var currentHeight: Int = 120
    private var currentWeight: Int = 60
    private var currentYearsOld: Int = 20
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initializeComponents()
        initListener()
        setGenderColor()
        initUi()
    }


    //Inicia los componentes viewMale y viewFemales
    private fun initializeComponents() {
        viewMale = findViewById(R.id.male)
        viewFemale = findViewById(R.id.Female)
        txHeight = findViewById(R.id.txHeight)
        rsAltura = findViewById(R.id.rsAltura)
        fab_plusWeight = findViewById(R.id.fab_plusWeight)
        fab_minumWeight = findViewById(R.id.fab_minumWeight)
        txWeight = findViewById(R.id.txWeight)
        txYearsOld = findViewById(R.id.txYearsOld)
        fab_plusYearsOld = findViewById(R.id.fab_plusYearsOld)
        fab_minumYearsOld = findViewById(R.id.fab_minumYearsOld)
        btnCalcularIMC = findViewById(R.id.btnCalcularIMC)

    }

    private fun initListener() {
        //la funcion de clic llama al evento definir el color generado
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsAltura.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            txHeight.text = "$currentHeight"
        }
        fab_plusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        fab_minumWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        fab_minumYearsOld.setOnClickListener {
            currentYearsOld -= 1
            setYearsOld()
        }
        fab_plusYearsOld.setOnClickListener {
            currentYearsOld += 1
            setYearsOld()
        }
        btnCalcularIMC.setOnClickListener {
            val result = calcularIMC()
            navigateToResultImcActivity(result)
        }
    }

    private fun calcularIMC(): Double {
        val df = DecimalFormat("#,##")
        val imc = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
        return df.format(imc).toDouble()

    }

    private fun setYearsOld() {
        txYearsOld.text = currentYearsOld.toString()
    }

    private fun setWeight() {
        txWeight.text = currentWeight.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }


    private fun setGenderColor() {
        //si el boton esta seleccionado devuelve el color de background
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))


    }


    //Funcion que Obtiene el color de Background R busca colores si esta o no seleccionado
    //returna ContextCompact.getColor que es el contexto + el colorReference
    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent)
            R.color.background_component_selected
        else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUi() {
        setGenderColor()
        setWeight()
        setYearsOld()
    }

    fun navigateToResultImcActivity(result: Double) {
        val intent = Intent(this, ResultImcActivity::class.java)
        intent.putExtra(IMCKEY, result)
        startActivity(intent)
    }


}
