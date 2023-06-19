package com.example.sudhashree.ui.main

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sudhashree.R
import com.example.sudhashree.databinding.FragmentSelectEditOptionBinding
import com.example.sudhashree.databinding.FragmentSelectUpdateOptionBinding
import com.example.sudhashree.db.SudhaShreeDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentSelectEditOptionBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var database: SudhaShreeDB

    private var _bindingupdate: FragmentSelectUpdateOptionBinding? = null
    private val bindingupdate get() = _bindingupdate!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

      _binding = FragmentSelectEditOptionBinding.inflate(inflater, container, false)
      val root = binding.root

        database= SudhaShreeDB.getDBInstance(requireActivity())

        val position = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        val ModelName = arguments?.getString(ARG_PARAMETER_ModelName)
        val ModelPrice = arguments?.getString(ARG_PARAMETER_ModelPrice)
        val Quantity_Black= arguments?.getString(ARG_PARAMETER_Quantity_BLACK)
        val Quantity_WHITE = arguments?.getString(ARG_PARAMETER_Quantity_WHITE)
        val ModelId = arguments?.getInt(ARG_PARAMETER_ModelId)

        // Bind different layouts based on the position
        when (position) {
            1 -> {
                binding.head.setText("BUY")
                val ed_modelName: TextView = binding.edMobile
                ed_modelName.setText(ModelName)
                val ed_modelPrice: TextView = binding.edPrice
                ed_modelPrice.setText(ModelPrice)
                val ed_Quantity: EditText= binding.edQuantity

                binding.confirmButton.setOnClickListener(View.OnClickListener {

                    if(ed_Quantity.text.toString() != null && ed_Quantity.text.toString()!= ""&& binding.radioGroup.checkedRadioButtonId != -1) {
                        GlobalScope.launch {

                            if (ModelId != null) {
                                if (binding.RBBlack.isChecked) {

                                    var qty = Quantity_Black?.toInt()
                                    var intQuty = ed_Quantity.text.toString()

                                    var totalQuant = qty?.plus(intQuty.toInt())

                                    Log.d("test", totalQuant.toString())
                                    database.mobiledao().updateBlack(
                                        ed_modelPrice.text.toString(),
                                        ModelId, totalQuant.toString()
                                    )
                                    requireActivity().finish()
                                } else {
                                    var qty = Quantity_WHITE?.toInt()
                                    var intQuty = ed_Quantity.text.toString()

                                    var totalQuant = qty?.plus(intQuty.toInt())

                                    Log.d("test", totalQuant.toString())
                                    database.mobiledao().updateWhite(
                                        ed_modelPrice.text.toString(),
                                        ModelId, totalQuant.toString()
                                    )
                                    requireActivity().finish()

                                }
                            }
                        }
                    }else{
                        Toast.makeText(requireContext(),"Provide correct quantity",Toast.LENGTH_LONG).show()
                    }
                })

            }
            2 -> {
                binding.head.setText("SELL")
                val ed_modelName: TextView = binding.edMobile
                ed_modelName.setText(ModelName)
                val ed_modelPrice: TextView = binding.edPrice
                ed_modelPrice.setText(ModelPrice)
                val ed_Quantity: EditText= binding.edQuantity

                binding.confirmButton.setOnClickListener(View.OnClickListener {
                    if(ed_Quantity.text.toString() != null && ed_Quantity.text.toString()!= ""&& binding.radioGroup.checkedRadioButtonId != -1) {

                        GlobalScope.launch {
                            if (ModelId != null) {
                                if (binding.RBBlack.isChecked) {
                                    var qty = Quantity_Black?.toInt()
                                    var intQuty = ed_Quantity.text.toString()
                                    var totalQuant = qty?.minus(intQuty.toInt())

                                    Log.d("test", totalQuant.toString())
                                    database.mobiledao().updateBlack(
                                        ed_modelPrice.text.toString(),
                                        ModelId, totalQuant.toString()
                                    )
                                    requireActivity().finish()
                                } else {
                                    var qty = Quantity_WHITE?.toInt()
                                    var intQuty = ed_Quantity.text.toString()
                                    var totalQuant = qty?.minus(intQuty.toInt())

                                    database.mobiledao().updateWhite(
                                        ed_modelPrice.text.toString(),
                                        ModelId, totalQuant.toString()
                                    )
                                    requireActivity().finish()
                                }
                            }
                        }
                    }else{
                        Toast.makeText(requireContext(),"Provide correct quantity",Toast.LENGTH_LONG).show()
                        }
                })
            }
            3 -> {
                binding.edQuantity.visibility= View.GONE
                binding.RBBlack.visibility=View.GONE
                binding.RBWhite.visibility= View.GONE
                binding.tv1.visibility=View.GONE
                binding.textView3.visibility=View.GONE

                binding.textView2New.visibility= View.VISIBLE
                binding.edPriceNew.visibility=View.VISIBLE

                binding.head.setText("Update Price")
                val ed_modelName: TextView = binding.edMobile
                ed_modelName.setText(ModelName)

                binding.textView2.setText("Existing Price")
                val ed_modelPrice: TextView = binding.edPrice
                ed_modelPrice.setText(ModelPrice)


                binding.confirmButton.setOnClickListener(View.OnClickListener {
                    if(binding.edPriceNew.text.toString() != "") {
                        GlobalScope.launch {
                            if (ModelId != null) {

                                database.mobiledao().updatePrice(
                                    binding.edPriceNew.text.toString(),
                                    ModelId
                                )
                                requireActivity().finish()

                            }
                        }
                    }else{

                        Toast.makeText(requireContext(),"Provide price of product",Toast.LENGTH_LONG).show()
                    }

                })



            }
        }
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_PARAMETER_ModelName = "ARG_PARAMETER_ModelName"
        private const val ARG_PARAMETER_ModelPrice = "ARG_PARAMETER_ModelPrice"
        private const val ARG_PARAMETER_Quantity_BLACK = "ARG_PARAMETER_Quantity_BLACK"
        private const val ARG_PARAMETER_Quantity_WHITE = "ARG_PARAMETER_Quantity_WHITE"
        private const val ARG_PARAMETER_ModelId = "ARG_PARAMETER_ModelId"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, modelName: String?,
                        modelPrice: String?,
                        Quantity_black: String?,
                        Quantity_white: String?,
                        modelID: Int,): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(ARG_PARAMETER_ModelName, modelName)
                    putString(ARG_PARAMETER_ModelPrice, modelPrice)
                    putString(ARG_PARAMETER_Quantity_BLACK, Quantity_black)
                    putString(ARG_PARAMETER_Quantity_WHITE, Quantity_white)
                    putInt(ARG_PARAMETER_ModelId, modelID)
                }
            }
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}