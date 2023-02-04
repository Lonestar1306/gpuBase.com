package com.gpuBase.model;

import java.util.ArrayList;
import java.util.List;

public class Carrello {


		private List<VenditaBean> products;
		
		public Carrello() {
			products = new ArrayList<VenditaBean>();
		}
		
		public void addProduct(VenditaBean custom) {
			products.add(custom);
		}
		
		public void deleteProduct(VenditaBean custom) {
			for(VenditaBean prod : products) {
				if(prod.getIdVendita() == custom.getIdVendita()) {
					products.remove(prod);
					break;
				}
			}
	 	}
		
		public List<VenditaBean> getProducts() {
			return  products;
		}
		
		
		/*ritorna un singolo prodotto del carrello*/
		public VenditaBean getProduct(int id) {
			for(VenditaBean prod : products) {
				if(prod.getIdVendita() == id) {
					return prod;
				}
				
			}
			return null;
		}
		
		
		/*aggiorna pezzi di un item nel carrello*/
		public void updatePz(VenditaBean custom, int pezzi)
		{
			for(VenditaBean prod : products) {
				if(prod.getIdVendita() == custom.getIdVendita()) {
					prod.setPezzi(pezzi);
					break;
				}
			}
		}
		
		/*calcola il prezzo totale dovuto*/
		
		public double getTotale() {
			
			double totale=0;
			for(VenditaBean beancart: products)
			{
				totale+=beancart.getTotale();
			}
			return totale;
		}
}

	
