import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        необходимо определить тип СМО, а затем найти требуемые параметры

        System.out.println("Введите количество рабочих мест: ");

        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();//количество каналов

        int n = 3;
        int m = 6; //мест в очереди

        if (n != 1){
            System.out.println("Тип СМО - многоканальная");
        } else {
            System.out.println("Тип СМО - одноканальная");
        }

        double intensity = 12;
        double time = 60;
        double time_of_clean = 20;

        double mu = time/time_of_clean;
        System.out.println("Интенсивность потока обслуживания = " + mu);

        double ro = intensity*time_of_clean/time;
        System.out.println("Интенсивность нагрузки = " + ro);

        double sum = 0;

//        System.out.println(getFactorial(n));

        for (int i = 1; i <= n; i++){
            sum += Math.pow(ro, i)/getFactorial(i);
        }
        double ro_0 = Math.pow(sum+1, -1);
        double ro_0_pr = ro_0*100;

        String ro_0_f = new DecimalFormat("#0.00").format(ro_0_pr);
        System.out.println("Вероятность простоя системы = " + ro_0_f + "%");

//        Вероятность того, что в системе массового обслуживания
//        находится какое-либо количество заявок

        double t_pr = 60*ro_0;
        String t_pr_f = new DecimalFormat("#0.00").format(t_pr);
        System.out.println("Время простоя = " + t_pr_f + " минуты");

        double ro_each;
        for (int i = 1; i<=n; i++){
            ro_each = (Math.pow(ro,i)/getFactorial(i))*ro_0;
            System.out.println("Вероятность того, что заняты " + i + " канала = " + ro_each);
        }

        double ro_och = (Math.pow(ro,n)/getFactorial(n))*((1-Math.pow(ro/n,m))/(1-(ro/n))*ro_0);
        System.out.println("Вероятность образования очереди = " + ro_och*100);


    }

    public static int getFactorial(int f) {
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }
}