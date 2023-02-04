package com.gpuBase.model;


public class MyCollection {
	
	private VenditaCustomSchedaBean[] myCol;
	
	
	private int size;
	private final static int maxSize=6;
	
	public MyCollection()
	{
			this.myCol=new VenditaCustomSchedaBean[maxSize];
			for(int i=0;i<maxSize;i++) {myCol[i]=null;}
			this.size=0;
	}
	
	
	public boolean contains(int idCustom)
	{
		for(int i=0;i<maxSize;i++) {
			if(myCol[i]!=null && myCol[i].getIdCustom()==idCustom)
				return true;
		}
		return false;
	}
	
	public void addProduct(VenditaCustomSchedaBean bean) {
	
		if(size<maxSize && !contains(bean.getIdCustom())) {
			for(int i=0;i<maxSize;i++) {
				if(myCol[i]==null) {
					myCol[i]=bean;
					size++;
					return;
				}
			}
		}
	}
	
	public void deleteProduct(VenditaCustomSchedaBean bean) {
		if(size>0 && contains(bean.getIdCustom())) {
			for(int i=0;i<maxSize;i++) {
				if(myCol[i]!=null && bean.equals(myCol[i])) {
					myCol[i]=null;
					size--;
				}
			}
		}
 	}
	
	public  VenditaCustomSchedaBean[] getProducts() {
		return  myCol;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	

	public VenditaCustomSchedaBean getProduct(int idCustom) {
		if(size>0) {
			for(int i=0;i<maxSize;i++) {
				if(myCol[i]!=null && idCustom==myCol[i].getIdCustom()) {
					return myCol[i];
				}
			}
		}
		return null;
	}
	
	public VenditaCustomSchedaBean getProductIndex(int index) {

		if(index>=0 && index<=maxSize) {
			return myCol[index];	
		}
		return null;
	}

	

	
	public String toString() {
		String out="MyCollection [myCol=";
		for(int i=0;i<maxSize;i++) {
				out+="\n["+myCol[i]+"]";
		}
		return out + "\n, size=" + size + "]";
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyCollection other = (MyCollection) obj;
		
		
		for(int i=0;i<maxSize;i++) {
			if(myCol[i]!=other.myCol[i])
				return false;
		}
		
		if (size != other.size)
			return false;
		return true;
	}

	
	
	
	
	

}
