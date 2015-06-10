package pap.ass03.haskell.shape;

import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.comparingInt;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Edoardo Rosa
 */
public class Utils {
    
    /**
     * Data una lista di figure e un vettore dv, trasla ogni figura del vettore specificato
     * @param list Lista delle figure in input
     * @param dv Vettore di traslazione
     * @return Lista delle figure traslate di "dv"
     */
    public static List<Shape> moveShapes(List<Shape> list, V2d dv) {
        list.stream().forEach(s -> s.move(dv));
        return list;
    }
    
    /**
     * Data una lista di figure e un bounding box, computa la lista delle figure contenute nel bounding box p0 p1
     * @param list Lista delle figure in input
     * @param box Bounding Box
     * @return Lista delle figure contenute nel bounding box "box"
     */
    public static List<Shape> inBBox(List<Shape> list, BBox box) {
        return list.stream().filter(s -> s.isInside(box)).collect(toList());
    }
    
    /**
     * Data una lista di figure, determina il perimetro maggiore
     * @param list Lista delle figure in input
     * @return Perimetro maggiore
     */
    public static double maxPerim(List<Shape> list) {
        Optional<Shape> periMax = list.stream().max(comparingDouble(s -> s.getPerim()));
        if (periMax.isPresent()) {
            return periMax.get().getPerim();
        } else {
            return 0;
        }
    }
    
    /**
     * Data una lista di figure, determina la figura con perimetro maggiore
     * @param list Lista delle figure in input
     * @return Shape con perimetro maggiore
     */
    public static Shape shapeWithMaxPerim(List<Shape> list) {
        return list.stream().max(comparingDouble(s -> s.getPerim())).get();
    }
    
    /**
     * Data una lista di figure, le ordina lungo l'asse x
     * @param list Lista delle figure in input
     * @return Lista delle figure ordinate lungo l'asse x del piano
     */
    public static List<Shape> sortShapeByX(List<Shape> list) {
        return list.stream().sorted(comparingInt(s -> s.getBBox().getUpperLeft().getX())).collect(toList());
    }

    /**
     * Data una lista di figure e un punto, verifica se esiste una figura che contiene il punto 
     * @param list Lista delle figure in input
     * @param p Punto da verificare
     * @return Se esiste una figura che contiene p
     */
    public static boolean contains(List<Shape> list, P2d p) {
        return list.stream().map(s -> s.contains(p)).reduce((x,y) -> x || y).get();
    }
    
    /**
     * Data una lista di figure e un punto p, computa la lista delle figure che contengono il punto
     * @param list Lista delle figure in input
     * @param p Punto da verificare
     * @return Lista delle figure che includono il punto
     */
    public static List<Shape> getContaining(List<Shape> list, P2d p) {
        return  list.stream().filter(s -> s.contains(p)).collect(toList());
    }
    
    /**
     * Data una lista di figure, le stampa in uscita 
     * @param list Lista delle figure in input
     */
    public static void logAll(List<Shape> list) {
       list.stream().forEach(s -> System.out.println(s.printShape()));
    }
}
