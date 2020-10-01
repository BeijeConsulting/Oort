package it.beije.oort.franceschi.biblioteca.controller;

import it.beije.oort.franceschi.biblioteca.model.Libro;

public class TestMethodsHere {
//    public static boolean merge(int idOldObj, IBibliotecaModel updatedObj){
//        IBibliotecaModel oldObj = ProgramBasicOperations.em.find(updatedObj.getClass(), idOldObj);
//        String classeObj = oldObj.getClass().getSimpleName();
//        switch (classeObj){
//            case "Libro":
//                mergeBooks(idOldObj, (Libro)updatedObj);
//                break;
//            default:
//                System.out.println("Errore nell'update.");
//                return false;
//        }
//        return true;
//    }

    // todo sistemare i nullpointerexception
    public static void mergeBooks(int oldID){
        Libro old = ProgramBasicOperations.em.find(Libro.class, oldID);
        System.out.println("Dati del Libro in modifica:");
        System.out.println(old);
        System.out.println("Inserisci ora i nuovi dati. Lascia bianco per non modificare il campo.");
        Libro newLibro = ObjectCreator.creaLibroNuovo();
        if (!newLibro.getTitolo().equalsIgnoreCase("")) old.setTitolo(newLibro.getTitolo());
        if (!newLibro.getDescrizione().equalsIgnoreCase("")) old.setDescrizione(newLibro.getDescrizione());
        if (newLibro.getId_autore() > 0) old.setId_autore(newLibro.getId_autore());
        if (newLibro.getId_editore() > 0) old.setId_editore(newLibro.getId_editore());
        if (newLibro.getAnno_pubblicazione() != null) old.setAnno_pubblicazione(newLibro.getAnno_pubblicazione());
    }
}
