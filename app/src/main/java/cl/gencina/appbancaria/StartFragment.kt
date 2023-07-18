package cl.gencina.appbancaria

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cl.gencina.appbancaria.databinding.FragmentStartBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StartFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentStartBinding
    private var monto = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)

        binding.btnClose.setOnClickListener {
            when(binding.rgOpciones.checkedRadioButtonId){
                R.id.rb_ver_saldo -> {
                    binding.etvMonto.setText(monto.toString())
                }
                R.id.rb_ingresar_saldo -> {
                    if(binding.etvMonto.text.toString() != "" && binding.etvMonto.text.toString().toInt() >0){
                        monto += binding.etvMonto.text.toString().toInt()
                        binding.etvMonto.text.clear()

                    }else{
                        Toast.makeText(context, "Debe ingresar un valor mayor a 0", Toast.LENGTH_LONG).show()
                    }
                }
                R.id.rb_sacar_saldo ->{
                    if(binding.etvMonto.text.toString() != "" && binding.etvMonto.text.toString().toInt() >0 ){
                        if(binding.etvMonto.text.toString().toInt() <= monto){
                            monto -= binding.etvMonto.text.toString().toInt()
                            binding.etvMonto.text.clear()
                        }else{
                            Toast.makeText(context, "Debe retirar un monto menor al que tiene.", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(context, "Debe ingresar un valor mayor a 0.", Toast.LENGTH_LONG).show()
                    }
                }
                R.id.rb_salir -> {
                    requireParentFragment().activity?.finish()
                }
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}