
public class PlanchaElectrica extends Controlador
{
    
    PlanchaElectrica(int Potencia)
    {
        super(Potencia);
    }
    
    public void setConsumoPlancha(int PotenciaNueva)//Ingresa el consumo de la plancha con un limitante de negativos 0+
    {    
        if(PotenciaNueva>0 || PotenciaNueva <1000)
        {
            Interruptor_General.exPotencia(Potencia);
            Potencia = PotenciaNueva;
            Interruptor_General.sumPotencia(Potencia);
        }
        else
        {
            System.out.print("Consumo debe estar entre 0 a 1000");    
        }
    }
    
}
