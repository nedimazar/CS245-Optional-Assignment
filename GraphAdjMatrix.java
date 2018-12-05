import java.util.ArrayList;


public class GraphAdjMatrix implements Graph {

    public class Edge implements Comparable{
        //Structure of the edge [from, to, weight]
        int arr[];

        public Edge(int from, int to, int weight){
            int arr[] = {from, to, weight};
            this.arr = arr;
        }
        @Override
        public int compareTo(Object o) {
            Edge other = (Edge) o;
            return(this.arr[2] - ((Edge) o).arr[2]);
        }

        @Override
        public String toString(){
            return ("[" + arr[0] +", "+arr[1] + ", "+arr[2] +"]");
        }
    }

    int matrix[][];

    public GraphAdjMatrix(int size) {
        //everything is 0 so no weight = 0
        this.matrix = new int[size][size];
    }

    @Override
    public void addEdge(int v1, int v2) {
        matrix[v1][v2] = 1;
    }

    @Override
    public void addEdge(int v1, int v2, int weight) {
        matrix[v1][v2] = weight;

    }

    @Override
    public int getEdge(int v1, int v2) {
        return matrix[v1][v2];
    }

    @Override
    public int createSpanningTree() {
        //ArrayList will hold edges like this: [from, to, weight]
        int size = this.matrix.length;
        ArrayList<Edge> edges = new ArrayList<>(size * size); //in case every single node is interconnected

        //this loop fills the ArrayList with all the edge info
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.matrix[i][j] != 0) {
                    int arr[] = {i, j, this.matrix[i][j]};
                    edges.add(new Edge(i, j, this.matrix[i][j]));
                }
            }
        }

        //Here we have our edges arrayList sorted by weight with Java magic..
        edges.sort(Edge::compareTo);


        //edges to be used in the MST will be added here
        ArrayList<Edge> MST = new ArrayList<>();
        int weight = 0; //this will be updated accordingly

        DisjointSet d = new DisjointSet(size);

        //Do you even Kruskal ?
        for (Edge edge : edges ){
            if (d.find(edge.arr[0]) != d.find(edge.arr[1])){
                d.union(edge.arr[0], edge.arr[1]);
                MST.add(edge);
                weight += edge.arr[2];
            }
        }

        this.matrix = new int[size][size]; //wipe matrix
        //populate the matrix with the spanning tree
        for (Edge edge : MST){
            matrix[edge.arr[0]][edge.arr[1]] = edge.arr[2];
        }


       // System.out.println(edges);
        return weight;
    }

    @Override
    public void topologicalSort() {
        return;
    }

    public static void main(String args[]){
        GraphAdjMatrix g = new GraphAdjMatrix(10);
        g.addEdge(1, 2);
        g.addEdge(5, 4, 16);
        g.addEdge(3, 2,3);
        g.addEdge(5, 5, 4);
        g.addEdge(1, 0);
        g.addEdge(1, 7, 90);
        g.addEdge(3, 7, 4);



        System.out.println(g.createSpanningTree());
    }
}
