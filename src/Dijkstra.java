import java.io.*;
import java.util.Arrays;

class Dijkstra {

    private final Integer matrixCost[][]=new Integer[][]{
            //0   //1  //2  //3  //4  //5  //6  //7  //8
           /* {null,null,null,   0,null,null,null,null,null},  //0
            {   5,null,   3,null,null,null,null,null,null},  //1
            {null,null,null,null,null,   2,null,null,null},  //2
            {null,null,null,null,  20,null,   2,null,null},  //3
            {null,  12,null,null,null,   6,null,   0,null},  //4
            {null,null,null,null,null,null,null,null,   1},  //5
            {null,null,null,null,null,null,null,   4,null},  //6
            {null,null,null,null,null,null,null,null,   8},  //7
            {null,null,null,null,null,null,null,null,null},  //8
            */
            {null,7   ,9   ,null,null,14  },
            {7   ,null,10  ,15  ,null,null},
            {9   ,10  ,null,11  ,null,2   },
            {null,15  ,11  ,null,6   ,null},
            {null,null,null,6   ,null,9   },
            {14  ,null,2   ,null,9   ,null},

    };
    private static final Integer INF = 1000*1000*1000;//1000*1000*1000;
    private int vertexCount;
    private int[] pred;
    private int[] distance;



    private void Solve() throws IOException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

            // Считываем число вершин и дуг графа
        System.out.println("ввести число вершин");
        vertexCount = Integer.parseInt(buffer.readLine());
        System.out.println("ввести номер стартовой вершины");
        int start =Integer.parseInt(buffer.readLine());
        buffer.close();

            distance = new int[vertexCount];
            pred = new int[vertexCount];
            boolean[] isVisited = new boolean[vertexCount];

            // До начала работы алгоритма все расстояния,
            // кроме 0-го, равны бесконечности (условной)
            Arrays.fill(distance, 1000);
            Arrays.fill(pred, -1);
            Arrays.fill(isVisited,false);

            // 0-ое расстояние, очевидно равно нулю,
            // так как расстояние от 0-ой вершины
            // до самой себя равно нулю
            distance[start] = 0; //кратчайшее расстояние до стартовой вершины равно 0
            pred[start] = 0;
            for (int iter = 0; iter < vertexCount; ++iter) {
              int distV = INF;
              int v = -1;
              //поиск минимальной дистанции которую ее не выбрали
              for (int i = 0; i < vertexCount; ++i) {
                    if (isVisited[i]) {
                        continue;
                    }
                    if (distV <distance[i]){
                        continue;
                    }
                    v = i;
                    distV = distance[i];
              }
              //поиск дуг из этой вершины
                for (int i = 0; i < matrixCost[v].length; ++i) {
                   if (matrixCost[v][i]!=null) {
                       int weight = matrixCost[v][i];

                       if (distance[i] > distance[v] + weight) {
                           distance[i] = distance[v] + weight;
                           pred[i] = v;
                       }
                   }
                }
              //отмечаем вершину как просмотренную
                isVisited[v] = true;
            }
    }

    private void printWay(int v) {
        if (v == -1) {
            return;
        }

        printWay(pred[v]);
        System.out.println((v+1) + " ");
    }
    private void Print(){
        for (int v = 0; v < vertexCount; ++v) {
            if (distance[v] != INF) {
                System.out.println(v+": "+distance[v]);
            } else {
                System.out.println(v+": "+"-1 ");
            }
        }
        /*System.out.println();
        for (int v = 0; v < vertexCount; ++v) {
            System.out.println((v + 1) + ": ");
            if (distance[v] != INF) {
                printWay(v);
            }
            System.out.println();
        }*/
    }

    public static void main(String[] args) throws IOException {

        Dijkstra sol = new Dijkstra();

            sol.Solve();
            sol.Print();

    }
}
