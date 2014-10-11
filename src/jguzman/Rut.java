package jguzman;

import com.jguzman.rutform.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;





public class Rut extends Activity {

	//VARS
	private String nombre, apellido, rut;
	
	//GUI
	private EditText etNombre,etApellido, etRut;
	private Button btnEnviar;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		initialize();
		buttonEvent();
		
		
		
	}
	
	
	private void initialize(){

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.form);
		
		etNombre 	= (EditText) findViewById(R.id.formNombres);
		etApellido	= (EditText) findViewById(R.id.formApellidoPaterno);
		etRut		= (EditText) findViewById(R.id.formRut);	
		btnEnviar	= (Button) findViewById(R.id.enviar);
		
		etNombre.setNextFocusDownId(R.id.formApellidoPaterno);
		etApellido.setNextFocusDownId(R.id.formRut);
			
	}
	
	private void buttonEvent(){
		
		btnEnviar.setOnClickListener(				
				new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				if(  validateForm() ){
					Log.e( "FORM RUT", " EXITO " );					
					//YOUR CODE HERE OR SOME INTENT TO GO
					Toast.makeText(getApplicationContext(), "EXITO", 2000).show();
					
				}else{
					//SOME ERROR FOR THE FORM
					Toast.makeText(getApplicationContext(), "Revisa tu formulario", 2000).show();
				}
			}}
				
		);
	}
	
	
	private boolean validateForm(){
		nombre 	 = ( (EditText) findViewById(R.id.formNombres) ).getText().toString();
		apellido = ( (EditText) findViewById(R.id.formApellidoPaterno) ).getText().toString();
		rut		 = ( (EditText) findViewById(R.id.formRut) ).getText().toString();
		
		
		if( nombre.length() > 0 && apellido.length()>0 && validateRut().length() !=0 ) return true;
		else return false;
		

	}
	
	
	private String validateRut() {
		rut		 = ( (EditText) findViewById(R.id.formRut) ).getText().toString();
		
		String msj="";
		
		if( rut.length() > 0 ){
		    String[] rutseparado = rut.split("-");
		    if(rutseparado.length<2){
		    	msj= "\nError en el campo Rut - Falta gui—n";
		    }
		    
		    try{
		        int M=0,S=1,T=Integer.parseInt(rutseparado[0]);for(;T!=0;T/=10)S=(S+T%10*(9-M++%6))%11;
		        String verificador=""+(char)(S!=0?S+47:75);
		        if( rutseparado.length==2 && rutseparado[1].toUpperCase().equals(verificador) ){return "";
		        }else{
		        	msj = "\nError en el campo Rut";
		        	
		        }
		    }catch(NumberFormatException e){return "\nError en el campo Rut";} 
	    
		}
    	Toast.makeText(getApplicationContext(), msj,2000).show();
		return msj;
		
	}
	
	
	
	//TODO: Clear form button and event
	
	
}
