package ar.edu.itba.instruII.helpers

import android.app.Application
import ar.edu.itba.instruII.models.Alimentos
import ar.edu.itba.instruII.models.MotivoDosis
import ar.edu.itba.instruII.services.AlimentosServicio
import ar.edu.itba.instruII.services.MotivoDosisServicio
import ar.edu.itba.instruII.services.PacienteServicio

public class PreparadorBD {
    companion object{
        // Arrey con los alimentos. Instancio un objeto Alimentos() por cada posición de mi arreglo tengo un objeto alimento.
        //private val alimentos = arrayOf<Alimentos>(Alimentos(0,"Mili no sabe tipos", 10, 50.3),
        //    Alimentos(0, "Mili no sabe tipos 2", 40, 0.0))
        private val alimentos = arrayOf<Alimentos>(Alimentos(0,"Flan (unidad)", 125, 25.0),
            Alimentos(0, "Helado de crema (bocha)", 100, 20.0),
        Alimentos(0, "Helado de agua (unidad)", 100, 20.0),
        Alimentos(0, "Leche descremada (vaso o taza)", 206, 10.0),
        Alimentos(0, "Leche entera (vaso o taza)", 206, 10.0),
        Alimentos(0, "Crema de leche (brick)", 203, 7.0),
        Alimentos(0, "Queso fresco", 70, 3.0),
        Alimentos(0, "Yogurt entero (unidad)", 130, 15.0),
        Alimentos(0, "Yogurt descremado (unidad)", 130, 10.0),
        Alimentos(0, "Yogurt líquido (unidad)", 207, 30.0),
        Alimentos(0, "Arroz (plato mediano)", 150, 40.0),
        Alimentos(0, "Arroz (plato grande)", 230, 60.0),
        Alimentos(0, "Arroz (guarnición)", 75, 20.0),
        Alimentos(0, "Arroz integral (plato mediano)", 160, 40.0),
        Alimentos(0, "Arroz integral (plato grande)", 240, 60.0),
        Alimentos(0, "Arroz integral (guarnición)", 80, 20.0),
        Alimentos(0, "Batata", 160, 32.0),
        Alimentos(0, "Galleta (unidad)", 7, 4.0),
        Alimentos(0, "Guisantes (plato mediano)", 400, 40.0),
        Alimentos(0, "Guisantes (plato grande)", 600, 60.0),
        Alimentos(0, "Guisantes (guarnición)", 200, 20.0),
        Alimentos(0, "Harina de trigo/maíz (cucharada sopera)", 14, 10.0),
        Alimentos(0, "Lentejas (plato mediano)", 200, 40.0),
        Alimentos(0, "Lentejas (plato grande)", 300, 60.0),
        Alimentos(0, "Lentejas (guarnición)", 100, 20.0),
        Alimentos(0, "Pan blanco (rebanada)", 25, 12.0),
        Alimentos(0, "Papa hervida (plato mediano)", 200, 40.0),
        Alimentos(0, "Papa hervida (plato grande)", 300, 60.0),
        Alimentos(0, "Papa hervida (guarnición)", 100, 20.0),
        Alimentos(0, "Papas fritas (guarnición)", 60, 40.0),
        Alimentos(0, "Sushi", 20, 5.0),
        Alimentos(0, "Palta", 0, 0.0),
        Alimentos(0, "Albaricoque", 50, 3.0),
        Alimentos(0, "Arándano (puñado)", 20, 2.5),
        Alimentos(0, "Castaña (unidad)", 10, 3.0),
        Alimentos(0, "Cereza (12 unidades)", 100, 10.0),
        Alimentos(0, "Ciruela", 50, 5.0),
        Alimentos(0, "Coco (tajada)", 35, 2.0),
        Alimentos(0, "Dátil", 12, 7.0),
        Alimentos(0, "Frambuesa (puñado)", 20, 2.0),
        Alimentos(0, "Higos", 50, 5.0),
        Alimentos(0, "Kiwi", 100, 10.0),
        Alimentos(0, "Limón", 0, 0.0),
        Alimentos(0, "Mandarina", 100, 10.0),
        Alimentos(0, "Mango", 200, 20.0),
        Alimentos(0, "Manzana", 200, 20.0),
        Alimentos(0, "Durazno", 200, 20.0),
        Alimentos(0, "Durazno en conserva (una mitad)", 50, 10.0),
        Alimentos(0, "Melón (tajada)", 200, 10.0),
        Alimentos(0, "Membrillo", 350, 25.0),
        Alimentos(0, "Moras (puñado)", 20, 1.5),
        Alimentos(0, "Naranja", 200, 20.0),
        Alimentos(0, "Pera", 200, 20.0),
        Alimentos(0, "Papaya (tajada)", 250, 20.0),
        Alimentos(0, "Ananá (2 rodajas)", 100, 10.0),
        Alimentos(0, "Banana", 100, 20.0),
        Alimentos(0, "Sandía (tajada)", 200, 10.0),
        Alimentos(0, "Uva (12 unidades)", 100, 20.0),
        Alimentos(0, "Acelga", 250, 8.0),
        Alimentos(0, "Ajo (3 dientes)", 10, 2.5),
        Alimentos(0, "Alcachofa", 150, 5.0),
        Alimentos(0, "Berenjena", 125, 4.0),
        Alimentos(0, "Berro", 0, 0.0),
        Alimentos(0, "Brócoli", 300, 10.0),
        Alimentos(0, "Calabacín", 300, 10.0),
        Alimentos(0, "Cebolla", 150, 10.0),
        Alimentos(0, "Champiñón", 0, 0.0),
        Alimentos(0, "Coliflor", 300, 10.0),
        Alimentos(0, "Escarola", 0, 0.0),
        Alimentos(0, "Espárrago", 0, 0.0),
        Alimentos(0, "Espinaca", 0, 0.0),
        Alimentos(0, "Lechuga", 70, 2.5),
        Alimentos(0, "Nabo", 100, 3.0),
        Alimentos(0, "Palmitos (unidad)", 25, 1.0),
        Alimentos(0, "Pepino", 150, 5.0),
        Alimentos(0, "Morrón rojo/verde", 150, 5.0),
        Alimentos(0, "Puerro", 75, 2.5),
        Alimentos(0, "Rábano", 100, 3.0),
        Alimentos(0, "Repollo", 125, 4.0),
        Alimentos(0, "Tomate", 150, 5.0),
        Alimentos(0, "Zanahoria cruda", 70, 3.0),
        Alimentos(0, "Zanahoria cocida", 70, 5.0),
        Alimentos(0, "Aceituna (12 unidades)", 30, 0.0),
        Alimentos(0, "Almendra (puñado)", 20, 1.0),
        Alimentos(0, "Avellana (puñado)", 20, 1.0),
        Alimentos(0, "Maní (puñado)", 20, 2.0),
        Alimentos(0, "Pasas de uva (puñado)", 20, 12.5),
        Alimentos(0, "Nuez (puñado)", 20, 2.0),
        Alimentos(0, "Pipas (puñado)", 15, 2.0),
        Alimentos(0, "Pistacho (puñado)", 20, 2.0),
        Alimentos(0, "Gaseosa (vaso)", 22, 20.0),
        Alimentos(0, "Gaseosa light (vaso)", 0, 0.0),
        Alimentos(0, "Chocolatada (vaso)", 206, 20.0),
        Alimentos(0, "Bebida energética (lata)", 200, 30.0),
        Alimentos(0, "Cerveza (vaso)", 200, 8.0),
        Alimentos(0, "Sidra (copa)", 100, 5.0),
        Alimentos(0, "Vermut (copa)", 100, 13.0),
        Alimentos(0, "Jugo de frutas (vaso)", 150, 8.0),
        Alimentos(0, "Vino (copa)", 0, 0.0),
        Alimentos(0, "Azúcar blanca (cucharada de postre)", 8, 8.0),
        Alimentos(0, "Azúcar negra (cucharada de postre)", 8, 8.0),
        Alimentos(0, "Barra de cereales", 25, 12.5),
        Alimentos(0, "Croissant (unidad)", 60, 30.0),
        Alimentos(0, "Cacao en polvo (cucharada de postre)", 8, 7.0),
        Alimentos(0, "Canelones (3 unidades)", 250, 25.0),
        Alimentos(0, "Caramelo (unidad)", 5, 4.0),
        Alimentos(0, "Chocolate (tableta)", 30, 17.0),
        Alimentos(0, "Empanada", 40, 8.0),
        Alimentos(0, "Edulcorante (cucharada sopera)", 20, 20.0),
        Alimentos(0, "Gelatina", 125, 20.0),
        Alimentos(0, "Ketchup (sobre)", 9, 2.0),
        Alimentos(0, "Lasaña", 260, 27.0),
        Alimentos(0, "Magdalena", 50, 20.0),
        Alimentos(0, "Mermelada (cucharada sopera)", 25, 12.5),
        Alimentos(0, "Mermelada light (cucharada sopera)", 0, 0.0),
        Alimentos(0, "Miel (cucharada sopera)", 18, 13.0),
        Alimentos(0, "Mostaza", 0, 0.0),
        Alimentos(0, "Pizza (porción)", 100, 25.0),
        Alimentos(0, "Salsa barbacoa", 50, 5.0),
        Alimentos(0, "Salsa blanca", 50, 5.0),
        Alimentos(0, "Salsa bolognesa", 75, 5.0),
        Alimentos(0, "Salsa de tomate", 50, 5.0),
        Alimentos(0, "Vinagre", 0, 0.0),
        Alimentos(0, "Fideos (plato mediano)", 200, 40.0),
        Alimentos(0, "Fideos (plato grande)", 300, 60.0),
        Alimentos(0, "Fideos (guarnición)", 100, 20.0),
        Alimentos(0, "Torta de bizcochuelo (porción)", 100, 28.0))

        private val motivoDosis = arrayOf<MotivoDosis>(MotivoDosis(0,"Extra"),
            MotivoDosis(0,"Ingesta")
        )
        // Esta es una función que se va a llamar al principio apenas te descargas la aplicación para que cuando el usuario no haya puesto
        // todavía sus datos se llenen nuestras tablas fijas.
        fun prepararBD(application: Application){
            // Planteo mi if para cuando el paciente haya cargado sus datos o no.
                val SizeTabla = PacienteServicio().existenciaPaciente(application)
            for (element: Alimentos in alimentos){
                AlimentosServicio().insertAlimento(element,application)}
                if (SizeTabla == 0){
                    // For que me mete todos los valores dentro de mi tabla alimentos.
                    for (element: Alimentos in alimentos){
                        AlimentosServicio().insertAlimento(element,application)
                    }
                    // For que me mete todos mis datos en la tabla motivo
                    for (element: MotivoDosis in motivoDosis){
                        MotivoDosisServicio().insertMotivoDosis(element,application)
                    }
                }
        }
    }
}