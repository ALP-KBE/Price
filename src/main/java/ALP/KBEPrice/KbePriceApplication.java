package ALP.KBEPrice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ALP.KBEPrice.Controller.PriceController;
import ALP.KBEPrice.Model.Component;
import ALP.KBEPrice.Model.Product;

@SpringBootApplication
public class KbePriceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KbePriceApplication.class, args);
		// test();
	}

	public static void test(){
		Component q = new Component("Hals", "Ahornhals", "Ahorn", "Deutschland", "hellbraun", "Hausmarke", "D", "neu", "100.00", "bestellbar", "stark");
		
		Component w = new Component("Steg", "Rollensteg", "Glockenmessing", "Deutschland", "Chrom", "ABM", "V", "gebraucht", "90.20", "sofort lieferbar", "gering");
		Component e = new Component("Korpus", "Erlenkorpus", "Erle", "Deutschland", "schwarz", "MLFactory", "Stratocaster", "gebraucht", "120.00", "sofort lieferbar", "stark");
		Component r = new Component("Griffbrett", "Palisandergriffbrett", "Palisander", "Indien", "braun", "RallFactory", "LP", "neu", "47.70", "sofort lieferbar", "stark");
		Component t = new Component("Tonabnehmer", "Strat Pick up Set", "Alnico V Magnet", "USA", "weiss", "Fender", "Single Coil", "neu", "21.50", "sofort lieferbar", "stark");
		Component z = new Component("Schallloch", "FLoch", "NA", "NA", "NA", "Hausmarke", "f", "neu", "20.00", "sofort lieferbar", "gering");
		Component u = new Component("Kopf", "Mahagonikopfplatte", "Mahagoni", "Mexiko", "dunkelbraun", "MLFactory", "Rohling", "neu", "7.50", "sofort lieferbar", "gering");
		Component i = new Component("Wirbel", "Pegs Schluessel Wirbel", "Kunststoff", "China", "creme", "Yibui", "Bonbon", "neu", "12.90", "sofort lieferbar", "keine");
		Component o = new Component("Sattel", "Knochensattel", "Knochen", "USA", "creme", "RallFactory", "flach", "neu", "30.00", "bestellbar", "gering");
		Component p = new Component("Decke", "Ahornfunier", "Ahorn", "Deutschland", "hellbraun", "ABM", "Funier", "neu", "90.90", "bestellbar", "gering");

		Product product = new Product("test", q, w, e, r, t, z, u, i, o, p);
		System.out.println(PriceController.getPrice(product));
	}
}
