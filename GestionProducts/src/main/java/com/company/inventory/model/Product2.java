package com.company.inventory.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;

@Data
@Entity
//@Table(name="producto")
public class Product2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7461389651533509262L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_producto;
	
	private String name;
	
	private int prec_producto;
	
	private int cod_almacen ;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonIgnoreProperties ( {"hibernateLazyInitializer", "handler"})
	private Category category;
	
	
	private String url_imagen1 ;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column( name ="url_imagen2", columnDefinition = "longblob")
	private byte[] url_imagen2 ;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column( name ="url_imagen3", columnDefinition = "longblob")
	private byte[] url_imagen3 ;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column( name ="url_imagen4", columnDefinition = "longblob")
	private byte[] url_imagen4 ;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column( name ="url_imagen5", columnDefinition = "longblob")
	private byte[] url_imagen5 ;
	
	private String url_imagen6 ;
	
	private int pag_producto ;
	private int indice1_producto  ;
	private int indice2_producto  ;
	
	private String  link_producto;
	

}
