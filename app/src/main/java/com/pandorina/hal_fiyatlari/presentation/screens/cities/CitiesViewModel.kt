package com.pandorina.hal_fiyatlari.presentation.screens.cities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandorina.hal_fiyatlari.data.local.entity.CityEntity
import com.pandorina.hal_fiyatlari.domain.model.city.City
import com.pandorina.hal_fiyatlari.domain.repository.FavoriteCitiesRepository
import com.pandorina.hal_fiyatlari.domain.repository.PricesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val pricesRepository: PricesRepository,
    private val favoriteCitiesRepository: FavoriteCitiesRepository,
): ViewModel(){
    private val _citiesUiState = mutableStateOf(CitiesUiState())
    val citiesUiState: State<CitiesUiState> = _citiesUiState

    init {
        viewModelScope.launch {
            _citiesUiState.value = CitiesUiState(isLoading = true)
            /*pricesRepository.getCities().onSuccess {
                reorderByFavorites(it.map { cityDto -> cityDto.toDomain() })
            }.onFailure {
                _citiesUiState.value = CitiesUiState(isLoading = false, error = it.localizedMessage)
            }*/
        }
    }

    private fun reorderByFavorites(list: List<City>) {
        viewModelScope.launch {
            favoriteCitiesRepository.getFavoriteCities().collectLatest {
                val reorderedList = mutableListOf<City>()
                reorderedList.addAll(it.map { city -> city.toCity() })
                list.forEach { city ->
                    val item = reorderedList.find { it.id == city.id }
                    if (item == null) reorderedList.add(city)
                }
                _citiesUiState.value = CitiesUiState(isLoading = false, cities = reorderedList)
            }
        }
    }

    fun insertFavoriteCity(cityEntity: CityEntity){
        viewModelScope.launch {
            favoriteCitiesRepository.insertFavoriteCity(cityEntity)
        }
    }

    fun deleteFavoriteCity(cityId: String){
        viewModelScope.launch {
            favoriteCitiesRepository.deleteFavoriteCity(cityId)
        }
    }
}