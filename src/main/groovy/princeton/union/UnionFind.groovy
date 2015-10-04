package princeton.union

class UnionFind {
    final List<Integer> array

    UnionFind(int size) {
        this.array = (0..size).collect { it }
    }

    void union(int p, int q) {
        int pValue = array[p]
        int qValue = array[q]
        if (pValue == qValue) {
            return
        }
        for (int i = 0; i < array.size(); i++) {
            if (array[i] == qValue) {
                array[i] = pValue
            }
        }
    }

    boolean connected(int p, int q) {
        return this.array[p] == this.array[q]
    }

    int size() { this.array.size() }
}
