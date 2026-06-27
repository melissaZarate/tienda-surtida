
package com.tiendasurtida.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class FechaUtil {

    public static String obtenerDiaEnEspanol(LocalDate fecha) {
        DayOfWeek dia = fecha.getDayOfWeek();
        return dia.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
    }
    /**Verifica si es día de abastecimiento (miércoles o sábado)
     */
    public static boolean esDiaAbastecimiento(LocalDate fecha) {
        DayOfWeek dia = fecha.getDayOfWeek();

        return dia == DayOfWeek.WEDNESDAY ||
                dia == DayOfWeek.SATURDAY;
    }

    /**Devuelve mensaje listo para dashboard (opcional)
     */
    public static String mensajeAbastecimiento(LocalDate fecha) {

        if (esDiaAbastecimiento(fecha)) {
            return "Hoy es " + obtenerDiaEnEspanol(fecha) + ". Se recomienda realizar abastecimiento.";
        }

        return "Hoy no es día de abastecimiento.";
    }
}