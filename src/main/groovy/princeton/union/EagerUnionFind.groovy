package princeton.union

class EagerUnionFind implements UnionFind {
    final int[] array

    EagerUnionFind(int size) {
        this.array = (0..size).collect { it }
    }
    private EagerUnionFind(int[] array) {
        this.array = array
    }

    @Override
    EagerUnionFind union(int p, int q) {
        int pValue = array[p]
        int qValue = array[q]
        if (pValue == qValue) {
            return this
        }
        for (int i = 0; i < array.size(); i++) {
            if (array[i] == qValue) {
                array[i] = pValue
            }
        }
        return new EagerUnionFind(this.array)
    }

    @Override
    boolean connected(int p, int q) {
        return this.array[p] == this.array[q]
    }

    @Override
    int size() { this.array.size() }
}
