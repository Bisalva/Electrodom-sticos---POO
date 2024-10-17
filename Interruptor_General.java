import java.util.ArrayList;

public class Interruptor_General
{
    private static ArrayList<Controlador> Aparatos = new ArrayList<Controlador>(); //Lista para registrar los aparatos
    private static Interruptor_General IG = new Interruptor_General(); //IG Obj
    private static int regAparatos; //Contador de aparatos
    private static int potenciaTotal; //Potencia total del sistema
    private static boolean Estado; //Varaibale que guarda el Estado del interruptor ON/OFF


    public Interruptor_General()
    {
        regAparatos = 0;
        potenciaTotal=0;
        Estado = false;     
        
    }

    public void cambioEstadoIG()//Cambio de estado del IG
    {
        int i=0;
        if(Estado==false)
            {
                Estado=true;
            }else
            {
                Estado=false;
                potenciaTotal = 0;
            }
        if(regAparatos!=0)//revisa los estados de los aparatos conectados
            {
                for(i=0;i<Aparatos.size();i++)
                {
                    Aparatos.get(i).checkInCorriente(Estado);
                }
            }
    }
    
    public static void Watts(int Potencia, int bin)//Registra la energia/potencia usada por los aparatos
    {
        if(bin==1)
        {
            potenciaTotal=potenciaTotal+Potencia;
        }else if(potenciaTotal>0)
        {
            potenciaTotal=potenciaTotal-Potencia;
        }
    }
    
    public static Interruptor_General objIG() //regresa el objeto de InterruptorGeneral
    {
        return IG;
    }
    
    public int WattsIG()//consumo total en IG
    {
        return potenciaTotal;
    }
    public void RegAparatos(Controlador Aparato)//registro de los aparatos
    {
        Aparatos.add(Aparato);
    }

    public void unRegAparato(Controlador Aparato)//elimina los aparatos
    {
        Aparatos.remove(Aparato);
    }
     
    public boolean getEstado()//devuelve el estado de IG
    {
        return Estado;
    }

    public void numConecciones(int flag)//Cuantas conecciones hay en el sistema
    {
        if(flag == 1)
        {
             regAparatos++;   
        }
        else
        {
            regAparatos--;
        }
    }
    
    public static void exPotencia(int PotenciaIngresada)
    {
        potenciaTotal = potenciaTotal - PotenciaIngresada;
    }
    
    public static void sumPotencia(int PotenciaIngresada)
    {
        potenciaTotal = potenciaTotal + PotenciaIngresada;
    }
}
