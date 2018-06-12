package Lab14AiSD2018;


public class Path implements Comparable<Path>{

    public int WEIGHT,A,B;

    Path(int A, int B, int WEIGHT){

        this.A = A;
        this.B = B;
        this.WEIGHT =WEIGHT;
    }

    public int node(int A){

        if(A == this.A){
            return this.B;
        }

        else{
            return this.A;
        }
    }

    @Override
    public int compareTo(Path o) {
        return this.WEIGHT - o.WEIGHT;
    }
}
