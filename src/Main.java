//Nombre: Pablo Torres Doria
//Matricula: 3003910
//Actividad: 5
//Materia: Computación en Java

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Declaramos un escaner para leer telcado.
        Scanner lectura = new Scanner(System.in);

        //Declaramos las variables del problema.
        double pagoHora = 0, horas = 0, totalHoras = 0, totalHorasExtra = 0, totalExtraPagado = 0, totalPagado = 0;
        int empleados = 0;

        //Indicamos la función del sistema.
        System.out.println("""
                         -----------------------------------------------------------
                         |                   SISTEMA DE NÓMINA                     |
                         -----------------------------------------------------------
                         | Este sistema lleva registro de los pagos de nómina de   |
                         | acuerdo con las horas trabajadas, si son extras o no    |
                         | y el pago corresopndiente al día.                       |
                         -----------------------------------------------------------""");

        //Solicitamos el monto que se paga por hora de trabajo.
        System.out.print("-Indica el pago por hora de hoy: ");
        pagoHora = lectura.nextDouble();
        System.out.println("-----------------------------------------------------------");

        //Preguntamos la cantidad de empleados que asistieron.
        System.out.print("-Ingresa el número de empleados que trabajó hoy: ");
        empleados = lectura.nextInt();
        System.out.println("-----------------------------------------------------------");

        //Creamos un arreglo para cada tipo de pago y registro de horas.
        double[] nomina = new double[empleados];
        double[] nominaExtra = new double[empleados];
        double[] nominaTriple = new double[empleados];
        double[] registroHoras = new double[empleados];

        //Con un for-loop recorremos todos los arreglos agregando los pagos y horas correspondientes.
        for(int i = 0; i < empleados; i++){
            System.out.print("Horas trabajadas por el empleado " + (i+1) + ": ");
            horas = lectura.nextDouble();

            if(horas < 0 || horas > 24){
                System.out.println("Cantidad de horas inválidas, intente de nuevo.");
                i--;
                continue;
            }

            registroHoras[i] = horas;
            totalHoras += horas;

            if(horas <= 12){
                nomina[i] = horas * pagoHora;
                nominaExtra[i] = 0;
                nominaTriple[i] = 0;

                totalPagado += horas * pagoHora;

            } else if(horas <= 18){
                nomina[i] = 12 * pagoHora;
                nominaExtra[i] = (horas - 12) * pagoHora * 2;
                nominaTriple[i] = 0;

                totalPagado += (12 * pagoHora + ((horas - 12) * pagoHora * 2));
                totalHorasExtra += horas - 12;
                totalExtraPagado += (horas - 12) * pagoHora * 2;
            } else {
                nomina[i] = 12 * pagoHora;
                nominaExtra[i] = 6 * pagoHora * 2;
                nominaTriple[i] = (horas - 18) * pagoHora * 3;

                totalPagado += (12 * pagoHora + (6 * pagoHora * 2) + ((horas - 18) * pagoHora * 3));
                totalHorasExtra += horas - 12;
                totalExtraPagado += (6 * pagoHora * 2) + ((horas - 18) * pagoHora * 3);
            }
        }

        //Imprimirmos un encabezado para la tabla.
        System.out.println("\n-------------------------------------------------------------------------------\n" +
                "|  #  | Horas |   Pago   | Horas dobles |   Pago   | Horas triples |   Pago   |" +
                "\n-------------------------------------------------------------------------------");

        //Imprimimos todos los datos.
        for(int i = 0; i < empleados; i++){
            System.out.printf("| %3s | %5s | %8.2f | %12.1f | %8.2f | %13.1f | %8.2f |\n",
                    (i + 1), // Empleado
                    registroHoras[i], // Registro de horas
                    nomina[i], // Nómina
                    nominaExtra[i] / (pagoHora * 2), // Nómina extra (por 2 horas)
                    nominaExtra[i], // Nómina extra
                    nominaTriple[i] / (pagoHora * 3), // Nómina triple (por 3 horas)
                    nominaTriple[i] // Nómina triple
            );
        }

        //Respondemos las incógnitas de la actividad.
        System.out.println("-------------------------------------------------------------------------------" +
                "\n-Número total de horas trabajadas: " + totalHoras +
                "\n-Número de horas extra trabajadas: " + totalHorasExtra +
                "\n-Total de pago en nómina: " + totalPagado +
                "\n-Total pagado de horas extra: " + totalExtraPagado +
                "\n-------------------------------------------------------------------------------");

    }
}