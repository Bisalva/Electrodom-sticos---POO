enum Corriente {ON,OFF}; 
public abstract class Controlador
{
    private boolean interruptor; //interruptor de los aparatos si esta accionado o no
    private Corriente Energizado=Corriente.OFF; // Aparato energizado o no ON/OFF
    Interruptor_General Coneccion; //Representa la coneccion con el IG
    public int Potencia; //Potencia de los aparatos

    public Controlador(int potenciaAparatoUnidad)//constructor con el ingreso de Potencia desde las clases hijas
    {
        interruptor=false;
        Potencia=potenciaAparatoUnidad;
    }
    
    public void conectarAparato()//coneccion de los aparatos a IG
    {
        if(Coneccion==null)//Si no hay coneccion la genera a IG
        {
            int flag=1; // Variable encargada de la creacion o no de un objeto 
            this.Coneccion=Interruptor_General.objIG();
            Coneccion.unRegAparato(this);
            Coneccion.numConecciones(flag);
            
             if(Coneccion.getEstado()==true && interruptor==true)
            {
                Energizado=Corriente.ON;
                Coneccion.Watts(Potencia,flag);
            }else 
            {
                Energizado=Corriente.OFF;
            }
        }else
        {
            
            Energizado=Corriente.OFF;   
            int flag=0;
            Coneccion.Watts(Potencia,flag);
            Coneccion.unRegAparato(this);
            Coneccion.numConecciones(flag);
            this.Coneccion = null;
        }
    }
    
    public void cambiarInterruptorAparato()//cambia el interruptor de los aparatos
    {
        if(Coneccion!=null)
        {
            if(interruptor==false)
            {
                interruptor=true;
            }else
            {
                interruptor=false;
            }  
            if(Coneccion.getEstado()==true && interruptor==true)
            {
                Energizado=Corriente.ON;
                Coneccion.Watts(Potencia,1);
            }else
            {
                Energizado=Corriente.OFF;
                Coneccion.Watts(Potencia,0);
            }
        }
    }
    
    public void checkInCorriente(boolean EstadoGeneral) //revisa la corriente de los aparatos 
    {
        if(EstadoGeneral==true && interruptor==true && Energizado==Corriente.OFF)
        {
            Energizado=Corriente.ON;
            Coneccion.Watts(Potencia,1);
        }else if(EstadoGeneral==false && Energizado==Corriente.ON || Coneccion.getEstado()==false)
        {
            Energizado=Corriente.OFF;
            Potencia = 0;
            Coneccion.Watts(Potencia,0);
        }
    }
    
    
}
