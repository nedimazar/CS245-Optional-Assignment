//Reader used to implement disjoint set
public class DisjointSet {
    int parents[];



    public DisjointSet(int size){
        this.parents = new int[size];

        for (int i = 0; i < size; i++){
            //-1 means it is its own set
            parents[i] = -1;
        }
    }

    int find(int elem){
        if (parents[elem] < 0){
            return elem;
        } else {
            parents[elem] = find(parents[elem]);
            return parents[elem];
        }
    }

    void union(int elem1, int elem2){
        int set1 = find(elem1);
        int set2 = find(elem2);

        if(parents[set1] < parents[set2]){
            parents[set1] = parents[set1] + parents[set2];
            parents[set2] = set1;
        } else {
            parents[set2] = parents[set1] + parents[set2];
            parents[set1] = set2;
        }
    }


    public static void main(String args[]){
        DisjointSet d = new DisjointSet(10);
        d.union(1, 2);
        d.union(5,6);
        d.union(6,7);
        d.union(2,7);
        System.out.println(d.find(7));
    }

}
