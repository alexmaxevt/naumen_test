package m.evtukhov;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Ввод карты (допустимые символы: '#' - преграда, '.' - дорога, '@' - начальная точка, 'X' - конечная точка):");
        Way map = new Way(getWay());
        System.out.println("Произвожу вычисления...");
        System.out.println("Ваш кратчайший маршрут отмечен символами '+' :");
        Write(map.GetWay());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        System.exit(0);
    }

    private static char[][] getWay() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int K;
        int L;
        do {
            System.out.println("Введите количество строк на карте K<=1:");
            K = Integer.parseInt(bufferedReader.readLine());
        } while (K <= 1);
        do {
            System.out.println("Введите количество столбцов на карте L<=10000:");
            L = Integer.parseInt(bufferedReader.readLine());
        } while (L >= 10000);
        char[][] way = new char[K][L];
        for (int i = 0; i < K; i++){
            String str;
            String badSigns;
            do{
                System.out.println("Введите " + K + " символов " + (i+1) + "-й строки карты последовательно и нажмите Enter:");
                str = bufferedReader.readLine();
                badSigns = str.replaceAll("[@X#.]", "");
                if(badSigns.length()>0){
                    System.out.println("Ваша строка содержала недопустимые символы: '" + badSigns + "'. Повторите ввод (допустимые символы: '#' - стена, '.' - дорога, '@' - старт, 'X' - финиш)");
                }
                else if(str.length()<L || str.length()>L){
                    System.out.println("Вы ввели недопустимое количество символов, повторите ввод.");
                }
            } while (badSigns.length()>0 || str.length()<L || str.length()>L);

            char[] string = str.toCharArray();
            System.arraycopy(string, 0, way[i], 0, L);
        }
        System.out.println("Исходный путь путь:");
        Write(way);
        return way;
    }

    private static void Write(char[][] way) {
        if(way!=null) {
            StringBuilder s;
            for (int i = 0; i <= way.length - 1; i++) {
                s = new StringBuilder();
                for (int j = 0; j <= way[0].length - 1; j++) {
                    s.append(way[i][j]).append(" ");
                }
                System.out.println(s);
            }
        }
        else System.out.println("null");
    }
}
