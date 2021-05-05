package com.udacity.shoestore.fragments.ui_controllers

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.SingleShoeElementBinding
import com.udacity.shoestore.fragments.viewmodels.GeneralViewModel
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {

    lateinit var viewModel : GeneralViewModel
    lateinit var binding : FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //DataBinding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        viewModel = GeneralViewModel()

        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateList(viewModel.shoeList.value!!)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun generateList(list : ArrayList<Shoe>){
        for(shoe in list){
            val shoeView : View = View.inflate(requireContext(),R.layout.single_shoe_element, null)
            val shoeBinding = DataBindingUtil.getBinding<SingleShoeElementBinding>(shoeView)!!
            shoeBinding.nameValue.text = shoe.name
            shoeBinding.companyValue.text = shoe.company
           binding.listShoesContainer.addView(shoeView)
        }
    }
}