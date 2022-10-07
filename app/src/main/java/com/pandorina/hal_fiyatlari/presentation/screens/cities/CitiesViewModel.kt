package com.pandorina.hal_fiyatlari.presentation.screens.cities

import com.pandorina.hal_fiyatlari.core.BaseViewModel
import com.pandorina.hal_fiyatlari.data.local.entity.CityEntity
import com.pandorina.hal_fiyatlari.domain.model.city.City
import com.pandorina.hal_fiyatlari.domain.repository.CitiesRepository
import com.pandorina.hal_fiyatlari.domain.repository.FavoriteCitiesRepository
import com.pandorina.hal_fiyatlari.util.InterstitialAdManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository,
    private val favoriteCitiesRepository: FavoriteCitiesRepository,
): BaseViewModel<CitiesUiState>(CitiesUiState()){

    init {
        launchViewModelScope {
            _uiState.value = CitiesUiState(isLoading = true)
            citiesRepository.getCities().collectLatest { result ->
                result.onSuccess {
                    reorderByFavorites(it)
                }.onFailure {
                    _uiState.value = _uiState.value.copy(isLoading = false, error = it.localizedMessage)
                }
            }
        }
    }

    private fun reorderByFavorites(list: List<City>) {
        launchViewModelScope {
            favoriteCitiesRepository.getFavoriteCities().collectLatest {
                val reorderedList = mutableListOf<City>()
                reorderedList.addAll(it.map { city -> city.toCity() })
                list.forEach { city ->
                    val item = reorderedList.find { it.id == city.id }
                    if (item == null) reorderedList.add(city)
                }
                _uiState.value = _uiState.value.copy(isLoading = false, cities = reorderedList)
            }
        }
    }

    fun insertFavoriteCity(cityEntity: CityEntity){
        launchViewModelScope {
            favoriteCitiesRepository.insertFavoriteCity(cityEntity)
        }
    }

    fun deleteFavoriteCity(cityId: String){
        launchViewModelScope {
            favoriteCitiesRepository.deleteFavoriteCity(cityId)
        }
    }
}