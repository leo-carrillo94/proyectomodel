package entitis;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //get & set
@NoArgsConstructor //constructor vacio
@AllArgsConstructor //constructor completo


public class Carrera implements Serializable {
	
	private String codigo;
	private String nombre;
	private Integer creditos;
	private Integer semestre;

}
