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
        System.out.println("Вероятность образования очереди = " + ro_och);

        double ver_otkaza = ((Math.pow(ro,n)/getFactorial(n))*ro_0);
        System.out.println("Вероятность отказа в обслуживании = " + ver_otkaza);

        double prop_spos = 1 - ver_otkaza;
        System.out.println("Относительная пропускная способность = " + prop_spos);

        double abs_prop = 12*prop_spos;
        System.out.println("Абсолютная пропускная способность = " + abs_prop);

        double av_busy = ro*prop_spos;
        System.out.println("Среднее число занятых каналов = " + av_busy);

        double av_chan = n - av_busy;
        System.out.println("Среднее число простаивающих каналов = " + av_chan);

        double koef = av_busy/n;
        System.out.println("Коэффициент занятости = " + koef);

        double av_zay_och = ((Math.pow(ro, n+1))/(n*getFactorial(n)*((m*(m+1))/2)*ro_0));
        System.out.println("Среднее число заявок в очереди = " + av_zay_och);

        double av_t = av_zay_och/n;
        System.out.println("Среднее время ожидания в очереди = " + av_t);

        double av_t_preb_zay = (av_zay_och/n)+(prop_spos/3);
        System.out.println("Среднее время пребывания заявки в системе = " + av_t_preb_zay);

        double nom_proiz = n/(20.0/60.0);
        System.out.println("Номинальная производительность = " + nom_proiz);


        double fact_proiz = abs_prop/nom_proiz;
        System.out.println("Фактическая производительность = " + fact_proiz*100);

        System.out.println(" _________________________ ");

        double day = 10;
        double otkaz_hour = 12*ver_otkaza;
        System.out.println("Число заявок, получивших отказ в течение часа = " + otkaz_hour);

        double otkaz_day = otkaz_hour*day;
        System.out.println("Число заявок, получивших отказ с 9 до 19 часов = " + otkaz_day);

        double clean_cost = 150;
        double lost_money = otkaz_day*clean_cost;
        System.out.println("Средняя величина потери выручки = " + lost_money);


    }

    public static int getFactorial(int f) {
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }
}