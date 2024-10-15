package com.example.warmupappp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.warmupappp.R
import com.example.warmupappp.ViewModel.CocktailViewModel
import com.example.warmupappp.databinding.FragmentListBinding
import com.example.warmupappp.adapter.Adapter
import com.example.warmupappp.model.Cocktail
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
@AndroidEntryPoint

class ListFragment : Fragment() {

    private val viewModel: CocktailViewModel by viewModels()
    private lateinit var adapter: Adapter
    private lateinit var binding: FragmentListBinding
    private lateinit var originalList: List<Cocktail>
    private var lastSelectedCard: CardView? = null  // Son tıklanan kartı saklamak için değişken

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize adapter and set it to RecyclerView
        adapter = Adapter(emptyList())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        // Observe LiveData from ViewModel
        viewModel.cocktails.observe(viewLifecycleOwner, { cocktails ->
            cocktails?.let {
                // Update adapter with the new list
                adapter.updateList(it)
                // Save the original list for filtering
                originalList = it
                adapter.onItemClickListener = { cocktail ->
                    findNavController().navigate(
                        ListFragmentDirections.actionListFragmentToDetailFragment(
                            cocktail
                        )
                    )
                }
                for (cocktail in it) {
                    Log.d("ListFragment", "Cocktail: ${cocktail.name}")
                }
            }
        })

        // Set up search bar
        setSearchBar()
        setUpCategoryFilters()
        binding.randomCardview.setOnClickListener {
            navigateToRandomCocktail()
        }
    }

    private fun setSearchBar() {
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                val searchText = s.toString().lowercase(Locale.getDefault())
                if (::originalList.isInitialized) {
                    val filteredList = originalList.filter {
                        it.name.lowercase(Locale.getDefault()).contains(searchText)
                    }
                    Log.d("ListFragment", "Search Text: $searchText")
                    Log.d("ListFragment", "Filtered List Size: ${filteredList.size}")
                    adapter.updateList(filteredList)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun setUpCategoryFilters() {
        setUpCategoryCard(binding.cardChampeigne, "Champagne")
        setUpCategoryCard(binding.cardFruity, "Fruity")
        setUpCategoryCard(binding.cardSour, "Sours (citrus)")
        setUpCategoryCard(binding.cardCitrusy, "Citrusy")
        setUpCategoryCard(binding.cardClassic, "Classic/vintage")
        setUpCategoryCard(binding.cardMartini, "Martini-style")
        setUpCategoryCard(binding.cardBitter, "Bittersweet")
        setUpCategoryCard(binding.cardHerbal, "Herbal")
        setUpCategoryCard(binding.cardLongDrinks, "Long drinks & highballs")
        setUpCategoryCard(binding.cardSavoury, "Savoury")
        setUpCategoryCard(binding.cardSpiritForward, "Spirit-forward")
        setUpCategoryCard(binding.cardFrozen, "Frozen (blended)")
        setUpCategoryCard(binding.cardCreamy, "Creamy")
        setUpCategoryCard(binding.cardAperitivo, "Aperitivo / aperitif")
        setUpCategoryCard(binding.cardHallOfFame, "Hall of Fame & must know/try")
        setUpCategoryCard(binding.cardTedSaucier, "Ted Saucier")
        setUpCategoryCard(binding.cardTiki, "Tiki/tropical")
        setUpCategoryCard(binding.cardBarbeque, "Barbeque")
        setUpCategoryCard(binding.cardSummer, "Summer")
        setUpCategoryCard(binding.cardAutumn, "Autumn/fall")
        setUpCategoryCard(binding.cardNightcap, "Nightcap / sipping")
        setUpCategoryCard(binding.cardShortAndStirred, "Short & stirred")
        setUpCategoryCard(binding.cardAlbertStevensCrockett, "Albert Stevens Crockett")
        setUpCategoryCard(binding.cardHotDrinks, "Hot Drinks")
        setUpCategoryCard(binding.cardAfterDinner, "After dinner / digestif")
        setUpCategoryCard(binding.cardElevenses, "Elevenses / afternoon")
    }

    private fun setUpCategoryCard(card: CardView, categoryName: String) {
        card.setOnClickListener {
            Log.d("ListFragment", "$categoryName card clicked")

            // Eğer tıklanan kart zaten seçilmişse, filtrelemeyi kaldır ve arka plan rengini eski haline getir
            if (lastSelectedCard == card) {
                // Seçili kartın arka plan rengini eski haline getir
                card.setCardBackgroundColor(resources.getColor(android.R.color.white, null))
                // Son tıklanan kartı sıfırla
                lastSelectedCard = null
                // Orijinal listeyi güncelle
                adapter.updateList(originalList)
                return@setOnClickListener
            }

            // Önceki tıklanan kartın arka plan rengini sıfırla
            lastSelectedCard?.setCardBackgroundColor(resources.getColor(android.R.color.white, null))

            // Yeni tıklanan kartın arka plan rengini değiştir
            card.setCardBackgroundColor(resources.getColor(R.color.light_pink, null))

            // Son tıklanan kartı güncelle
            lastSelectedCard = card

            filterByGlass(categoryName)
        }
    }


    private fun filterByGlass(glassType: String) {
        if (::originalList.isInitialized && originalList.isNotEmpty()) {
            val filteredList = originalList.filter { cocktail ->
                cocktail.flavours?.any { it.flavour.equals(glassType, ignoreCase = true) } == true
            }
            Log.d("ListFragment", "Filtered List Size: ${filteredList.size}")
            adapter.updateList(filteredList)
        } else {
            adapter.updateList(viewModel.cocktails.value!!)
            Log.d("ListFragment", "Original list is not initialized or is empty.")
        }
    }

    private fun navigateToRandomCocktail() {
        if (::originalList.isInitialized && originalList.isNotEmpty()) {
            val randomCocktail = originalList.random()
            findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(randomCocktail))
        } else {
            Log.d("ListFragment", "Original list is not initialized or is empty.")
        }
    }
}