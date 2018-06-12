package Lab14AiSD2018;


public class Main {


    public static void main(String[] args){

        ListGraf lg = new ListGraf();

        String a="A",b="B",c="C",d="D",e="E";

        lg.addNode(a);
        lg.addNode(b);
        lg.addNode(c);
        lg.addNode(d);
        lg.addNode(e);

        lg.addPath(a,b,1);
        lg.addPath(b,c,1);
        lg.addPath(c,d,1);
        lg.addPath(d,e,5);
        lg.addPath(a,d,8);
        lg.addPath(a,c,5);
        lg.addPath(e,b,3);
        lg.display();

        lg.Kruskal();

        lg.Dijkstra(a,d);



    }
}
