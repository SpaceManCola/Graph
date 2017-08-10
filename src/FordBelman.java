import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FordBelman {
        private static final Integer INF=1000*1000*1000;
        private final Integer matrixCost[][]=new Integer[][]{
                //0   //1  //2  //3  //4  //5  //6  //7  //8
             /*   {null,null,null,   0,null,null,null,null,null},  //0
                {  -5,null,  -3,null,null,null,null,null,null},  //1
                {null,null,null,null,null,   2,null,null,null},  //2
                {null,null,null,null, -20,null,   2,null,null},  //3
                {null,  12,null,null,null,   6,null,   0,null},  //4
                {null,null,null,null,null,null,null,null,   1},  //5
                {null,null,null,null,null,null,null,   4,null},  //6
                {null,null,null,null,null,null,null,null,   8},  //7
                {null,null,null,null,null,null,null,null,null},  //8*/

            /*    {null,null,-1  ,null,-1  ,null},
                {null,null,null,-1  ,null,1  },
                {-1,null,null,-1,null,null},
                {null,-1,-1,null,null,null},
                {-1,null,null,null,null,1},
                {null,1,null,null,1,null}
                */
                {null,null,-1,null,null,null},
                {null,null,null,null,null,1},
                {null,null,null,-1,null,null},
                {null,-1,null,null,null,null},
                {-1,null,null,null,null,null},
                {null,null,null,null,1,null}
        };

        public static void main(String[] args) {
            FordBelman solution = new FordBelman();
            solution.solve();
        }


        private void solve() {
            Scanner scanner = new Scanner(System.in);


            // Считываем число вершин и дуг графа
            System.out.println("ввести число вершин и дуг");
            int vertexCount = scanner.nextInt();
            int edgeCount = scanner.nextInt();

            // Дуги графа будем хранить массиве
            // экземпляров класса Edge
            Edge[] edges = new Edge[edgeCount];
            int count = 0;
            for (int i = 0; i < matrixCost.length; i++) {
                for (int j = 0; j < matrixCost[i].length; j++) {
                    if (matrixCost[i][j] != null) {
                        edges[count] = new Edge(i, j, matrixCost[i][j]);
                        count++;
                    }
                }
            }

            int[] distance = new int[vertexCount];
            int[] pred = new int[vertexCount];
            ArrayList ans = new ArrayList();

            // До начала работы алгоритма все расстояния,
            // кроме 0-го, равны бесконечности (условной)
            Arrays.fill(distance, INF);
            Arrays.fill(pred, -1);

            // 0-ое расстояние, очевидно равно нулю,
            // так как расстояние от 0-ой вершины
            // до самой себя равно нулю
            distance[0] = 0;

            // обновлять массив расстояний
            for (int i = 0; i < vertexCount - 1; i++) {
                for (int j = 0; j < edgeCount; j++) {
                    int from = edges[j].from;
                    int to = edges[j].to;
                    int weight = edges[j].weight;

                    if (distance[to] > distance[from] + weight) {
                        distance[to] = distance[from] + weight;
                        pred[to] = from;
                    }
                }
            }

            for (int j = 0; j < edgeCount; j++) {
                int from = edges[j].from;
                int to = edges[j].to;
                int weight = edges[j].weight;
                if (distance[to] > distance[from] + weight) {
                    for (int i = 0; i < vertexCount - 1; i++)
                        to = pred[to];
                    from = to;
                    while (from != pred[to]) {
                        ans.add(to);
                        to = pred[to];
                    }

                    break;


                }
            }
            for (Object i : ans) {
                System.out.println(i.toString());
            }

            scanner.close();


        }
 }


