package Lab14AiSD2018;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;




public class ListGraf {

    ArrayList<ArrayList<Path>> array;
    ArrayList<String> NodeNames;
    ArrayList<Path> paths;



    public ListGraf(){

        array = new ArrayList<ArrayList<Path>>();
        NodeNames = new ArrayList<>();
        paths = new ArrayList<>();
    }

    public void addNode(String node){

        NodeNames.add(node);
        array.add(new ArrayList<Path>());

    }

    public void addPath(String nodeA, String nodeB, int weight){

        int A = NodeNames.indexOf(nodeA);
        int B = NodeNames.indexOf(nodeB);

        Path path = new Path(A,B,weight);

        paths.add(path);
        array.get(A).add(path);
        array.get(B).add(path);
    }

    public String NN(int i){
        return NodeNames.get(i);
    }

    public void display(){

        for(ArrayList<Path> a: array ){

            int var = array.indexOf(a);
            int val;

            System.out.print("Node: "+ NodeNames.get( var ) +" is connected with: ");

            for(Path in: a){

                if( in.A == var){
                    val = in.B;
                }
                else{
                    val = in.A;
                }


                System.out.print(NodeNames.get(val) + ", ");
            }
            System.out.println();
        }
    }

    boolean[] visited;
    boolean flag = false;
    int node = 0;

    public boolean DFS(int nod, int start,ArrayList<ArrayList<Path>> array){

        visited = new boolean[array.size()+1];

        for(int k = 0; k < array.size()+1; k++){
            visited[k] = false;
        }

        flag = false;
        node = nod;
        DFS_(start, array);

        return flag;
    }



    public void DFS_(int v,ArrayList<ArrayList<Path>> array){

        visited[v] = true;

        if(node == v){
            flag = true;
        }

        //System.out.print("\n --> " + NN(v)  );

        ArrayList<Path> tab = array.get(v);
        Iterator<Path> iter = tab.iterator();


        while(iter.hasNext()){

            int var = iter.next().node(v);

            if( !visited[var]  ){

                DFS_(var,array);
            }

        }

    }



    public void Kruskal(){

        System.out.println("Min drzewo rozp. :");

        ArrayList<Path> npaths = paths;
        Collections.sort(npaths);

        ArrayList<Path> kruskal = new ArrayList<>();
        ArrayList<ArrayList<Path>> array = new ArrayList<ArrayList<Path>>();

        int s = this.array.size();

        for(int i = 0; i < this.array.size(); i++){
            array.add(new ArrayList<Path>());
        }


        for(Path path: npaths){


            boolean flg = DFS(path.A, path.B,array);

            if( !flg ){

                kruskal.add(path);
                array.get(path.A).add(path);
                array.get(path.B).add(path);

            }
        }

        int sum = 0;


        for(Path path: kruskal){

            System.out.println(" path: "+ NN(path.A) + " --- "+ path.WEIGHT + " --- "+ NN(path.B));

            sum += path.WEIGHT;

        }

        System.out.println("SUM OF WIEGHT: "+ sum);
    }


    public void Dijkstra(String A, String B){

        int a = NodeNames.indexOf(A), b = NodeNames.indexOf(B);

        _Dijkstra(a,b);




    }



    public void _Dijkstra(int v, int x){

        boolean QS[] = new boolean[array.size()];  // if true then node is in S, false -> in Q
        int d[] = new int[array.size()];
        int p[] = new int[array.size()];

        for(int i = 0; i < array.size(); i++){

            QS[i] = false;
            d[i] = Integer.MAX_VALUE - 10;
            p[i] = -1;

        }

        d[v] = 0;

        int u, w;

        while ( !IsEmpty(QS) ){

            u = FindMinNode(d, QS);

            QS[u] = true;

            for( Path path: array.get(u)){

                w = path.node(u);

                if( QS[w] ){
                    continue;
                }

                if( d[w] > ( d[u] + path.WEIGHT ) ){

                    d[w] =  d[u] + path.WEIGHT;
                    p[w] = u;

                }

            }

        }


        int  current = x;

        System.out.println("Shortest way from: "+ NN(x) + " to: " + NN(v) + " is going thru: \nstart ->" + NN(current)  );


        while(true){

            System.out.println("-->"+ NN(p[current]));

            if( p[current] == v){
                break;
            }

            current = p[current];
        }


    }

    public int FindMinNode(int d[], boolean QS[]){

        int min = Integer.MAX_VALUE;
        int index = 0;

        for(int i = 0; i < QS.length; i++){

            if( !QS[i] ){

                if( d[i] < min){
                    min = d[i];
                    index = i;
                }
            }
        }

        return index;
    }

    public boolean IsEmpty(boolean tab[]){

        for( int i = 0; i < tab.length; i++){

            if( !tab[i] ){
                return false;
            }
        }
        return true;
    }

}
