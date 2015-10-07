package princeton.union

class QuickFind implements UnionFind {
    final int[] array

    QuickFind(int size) {
        this.array = (0..(size-1)).toArray() as int[]
    }
    private QuickFind(int[] array) {
        this.array = array
    }

    @Override
    QuickFind union(int p, int q) {
        assert p < this.array.length
        assert q < this.array.length
        int pValue = array[p]
        int qValue = array[q]
        if (pValue == qValue) {
            return this
        }
        for (int i = 0; i < array.size(); i++) {
            if (array[i] == pValue) {
                array[i] = qValue
            }
        }
        return new QuickFind(this.array)
    }

    @Override
    boolean connected(int p, int q) {
        return this.array[p] == this.array[q]
    }

    @Override
    int size() { this.array.length }
}
