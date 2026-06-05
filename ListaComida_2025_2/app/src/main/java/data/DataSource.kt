package data

import com.example.listacomida_2025_2.R
import model.Platillo

class DataSource {
    fun LoadPlatillos(): List<Platillo>{
        return listOf<Platillo>(
            Platillo(R.string.desayuno, R.drawable.desayuno, R.string.desayuno_precio, R.string.desayuno_oferta),
            Platillo(R.string.hamburgesa, R.drawable.hamburgesa, R.string.hamburgesa_precio, R.string.hamburgesa_oferta),
            Platillo(R.string.pizza, R.drawable.pizza, R.string.pizza_precio, R.string.pizza_oferta),
            Platillo(R.string.postre, R.drawable.postre,R.string.postre_precio, R.string.postre_oferta),
            Platillo(R.string.pozole, R.drawable.pozole, R.string.pozole_precio, R.string.pozole_oferta),
            Platillo(R.string.tacos, R.drawable.tacos, R.string.tacos_precio, R.string.tacos_oferta),
        )
    }
}