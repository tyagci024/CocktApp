package com.example.warmupappp.view

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warmupappp.adapter.IngredientAdapter
import com.example.warmupappp.R
import com.example.warmupappp.ViewModel.DetailCocktailViewModel
import com.example.warmupappp.databinding.FragmentDetailBinding
import com.example.warmupappp.util.downloadFromURL
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var ingredientAdapter: IngredientAdapter

    private val viewModel: DetailCocktailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ingredientAdapter = IngredientAdapter(args.cocktail.ingredients)
        binding.recyclerViewIngredients.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewIngredients.adapter = ingredientAdapter
        with(binding) {
            textViewName.text = args.cocktail.name ?: "N/A"
            textViewHowTo.text =args.cocktail.howto?.toString() ?: "N/A"
            textViewNutrition.text=extractNumbers(args.cocktail.nutrition)
            binding.imageViewCocktail.downloadFromURL(args.cocktail.image)


            viewModel.isCockDatabase(args.cocktail.name).observe(viewLifecycleOwner) { iscokDB ->
                if (iscokDB) {
                    imageViewFavorite.setImageResource(R.drawable.yellowstar)
                    imageViewFavorite.setOnClickListener {
                        viewModel.delete(args.cocktail.name)

                    }
                } else {
                    imageViewFavorite.setImageResource(R.drawable.greystar)
                    imageViewFavorite.setOnClickListener {
                        viewModel.insert(args.cocktail)
                    }
                }
            }
        }

    }
    private fun extractNumbers(input: String?): String {
        return input?.replace("[^\\d]".toRegex(), "") ?: "0"
    }

}

