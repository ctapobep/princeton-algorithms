package princeton.union

interface UnionFind {

    UnionFind union(int p, int q)

    boolean connected(int p, int q)

    int size()
}