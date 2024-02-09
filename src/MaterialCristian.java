import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MaterialCristian implements Serializable {
    private String desc;
    private int ctdad; 
    private Date fecha; 
 

    public MaterialCristian() {
    }
    public MaterialCristian(String desc, int ctdad, Date fecha) {
    	this.desc=desc;
    	this.ctdad=ctdad;
    	this.fecha=fecha;
    }


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public int getCtdad() {
		return ctdad;
	}


	public void setCtdad(int ctdad) {
		this.ctdad = ctdad;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

 
}